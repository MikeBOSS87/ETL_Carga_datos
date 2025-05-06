/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carga.repositorio;

import java.util.List;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.Types;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.Logger ;
import org.apache.logging.log4j.LogManager ;

import com.carga.modelo.ModeloProceso;
import com.carga.modelo.TTResumenCripto;

/**
 *
 * @author chief
 */
public abstract class ModeloDatosTriker implements ModeloProceso {
   
   private DataSource Nexus = null ;
   private Connection C = null ;
   private static final Logger loger = LogManager.getLogger( ModeloDatosTriker.class ) ;   
   
   public ModeloDatosTriker(){
      try {
         GeneraConexion();
      } catch ( SQLException ex ) {
         loger.info( "No se genero la conexion JDBC " + ex );
      }
   }
   
   private void GeneraConexion() throws SQLException{
      DataSourceBuilder dsb = DataSourceBuilder.create();
      dsb.driverClassName(  "oracle.jdbc.OracleDriver" );
      dsb.url( "jdbc:oracle:thin:@localhost:1521:ATLBD" );
      dsb.username( "USRPUENTE" );
      dsb.password("Puente");
      
      Nexus = dsb.build();
      C = Nexus.getConnection() ;
   }
   
   @Override
   public void GuardaTodo( List< TTResumenCripto > Entidades ) throws Exception{
      SimpleJdbcInsert tabla = new SimpleJdbcInsert( Nexus )
               .withSchemaName( "MDRG" )
               .withTableName( "TTRESUMENCRIPTO" )
               .usingColumns( 
                  "ASK",
                  "BID",
                  "BOOK",
                  "CHANGE_24",
                  "CREATED_AT",
                  "HIGH",
                  "LAST",
                  "LOW",
                  "VOLUME",
                  "VWAP" ) ;
      int[] registrosinsertados = tabla.executeBatch( SqlParameterSourceUtils.createBatch( Entidades.toArray() ) );
      loger.info( "registros insertados: " + registrosinsertados.length ) ;
   }

   @Override
   public String BorraTabla(  ) throws Exception {
      SimpleJdbcCall SP = new SimpleJdbcCall( Nexus )
					.withSchemaName( "MDRG" )
					.withCatalogName( "UTILERIAS" )
					.withProcedureName( "SPBORRARTABLA" )
					.declareParameters( new SqlParameter[]{
						new SqlParameter( "NombreTabla", Types.VARCHAR ),
					} ) ;
      
      MapSqlParameterSource mapeoParametros = new MapSqlParameterSource() ;
			mapeoParametros.addValue( "NombreTabla", "TTRESUMENCRIPTO" ) ;
			SP.execute( mapeoParametros );
			
      return SP.getCallString() ;
   }
   
   @Override
   public List< TTResumenCripto > ConsultaInfo( Integer id_parametria ){
      List< TTResumenCripto > Trx = null ;
      TTResumenCripto RegistroTrx = null ;
      try {
         CallableStatement Consulta = C.prepareCall( "" , ResultSet.CONCUR_READ_ONLY,  ResultSet.TYPE_SCROLL_INSENSITIVE ) ;
         ResultSet CursorConsulta = Consulta.executeQuery();
         
         CursorConsulta.beforeFirst();
         while( CursorConsulta.next() ) {
            RegistroTrx.setAsk( CursorConsulta.getString( "" ) );
            
            Trx.add( RegistroTrx ) ;
         }
         
         C.close();
      } catch( SQLException ex ) {
         loger.info( "No se genero la consulta " + ex );
         System.exit( 1 ) ;
      }
      return Trx ;
   }
}
