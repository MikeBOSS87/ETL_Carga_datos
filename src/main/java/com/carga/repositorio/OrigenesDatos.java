/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carga.repositorio;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MIGUEL DARIO RESENDIZ GUTIERREZ
 */
@Repository
public class OrigenesDatos {
   
   private HikariDataSource DS ;
   private Connection C = null ;
   private static final Logger loger = LogManager.getLogger( ModeloDatosTriker.class ) ;   
   
   public OrigenesDatos(){
      try {
         GeneraConexion();
      } catch ( SQLException ex ) {
         loger.info( "No se genero la conexion JDBC " + ex );
      }
   }
   
   private void GeneraConexion() throws SQLException{
      DS = new HikariDataSource() ;
      DS.setDriverClassName( "oracle.jdbc.OracleDriver" );
      DS.setJdbcUrl( "jdbc:oracle:thin:@localhost:1521:ATLBD" );
      DS.setUsername( "USRPUENTE" );
      DS.setPassword( "Puente" );
      DS.setPoolName( "Nexus_DB" );
      DS.setConnectionTimeout( 8000 );
      DS.setLoginTimeout( 5 );
      DS.getConnection().close();
   }
   
   @Autowired
   public DataSource DS(){
      return DS ;
   }
   
   @Autowired
   public Connection OldJDBC(){
      return C ;
   }
}