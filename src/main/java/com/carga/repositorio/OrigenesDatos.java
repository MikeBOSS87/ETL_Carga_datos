/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carga.repositorio;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chief
 */
@Repository
public class OrigenesDatos {
   
   private DataSource Nexus = null ;
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
      DataSourceBuilder dsb = DataSourceBuilder.create();
      dsb.driverClassName(  "oracle.jdbc.OracleDriver" );
      dsb.url( "jdbc:oracle:thin:@localhost:1521:ATLBD" );
      dsb.username( "USRPUENTE" );
      dsb.password("Puente");
      
      Nexus = dsb.build();
      C = Nexus.getConnection() ;
   }
   
   @Autowired
   public DataSource DS(){
      return Nexus ;
   }
   
   @Autowired
   public Connection OldJDBC(){
      return C ;
   }
}