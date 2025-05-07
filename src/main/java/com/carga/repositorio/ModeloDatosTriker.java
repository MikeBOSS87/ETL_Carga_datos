/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carga.repositorio;

import java.util.List;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
   
   private OrigenesDatos Nexus = null ;
   private static final Logger loger = LogManager.getLogger( ModeloDatosTriker.class ) ;   

   public ModeloDatosTriker() {
      Nexus = new OrigenesDatos() ;
   }
   
   @Override
   public void GuardaTodo( List< TTResumenCripto > Entidades ) throws Exception{
      SimpleJdbcInsert tabla = new SimpleJdbcInsert( Nexus.DS() )
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
      SimpleJdbcCall SP = new SimpleJdbcCall( Nexus.DS() )
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
         CallableStatement Consulta = Nexus.OldJDBC().prepareCall( "" , ResultSet.CONCUR_READ_ONLY,  ResultSet.TYPE_SCROLL_INSENSITIVE ) ;
         ResultSet CursorConsulta = Consulta.executeQuery();
         
         CursorConsulta.beforeFirst();
         while( CursorConsulta.next() ) {
            RegistroTrx.setAsk( CursorConsulta.getString( "" ) );
            RegistroTrx.setBid( CursorConsulta.getString( 1 ) );
            RegistroTrx.setBook(CursorConsulta.getString( 2 ) );
            RegistroTrx.setChange_24(CursorConsulta.getString( 3 ) );
            Trx.add( RegistroTrx ) ;
         }
         
         Nexus.OldJDBC().close();
      } catch( SQLException ex ) {
         loger.info( "No se genero la consulta " + ex );
         System.exit( 1 ) ;
      }
      return Trx ;
   }
}
