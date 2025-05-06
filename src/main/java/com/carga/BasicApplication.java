package com.carga;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.Logger ;
import org.apache.logging.log4j.LogManager ;

import com.carga.modelo.TTResumenCripto;
import com.carga.consumidor.SW ;
import com.carga.repositorio.ModeloDatosTriker;

public class BasicApplication{
   
   private static final Logger loger = LogManager.getLogger( BasicApplication.class ) ;   

   public static void main(String[] args) {
      SW sw = new SW() ;
      ModeloDatosTriker repo = new ModeloDatosTriker() {} ;
      try{
         Long horaInicio = System.currentTimeMillis() ;
         loger.info( "Comienza en: " + ( new Date().toString() ) );
         
         String BorraTabla = repo.BorraTabla(  );
         loger.info( "borrado de la tabla ejecutando " + BorraTabla );
         
         loger.info( "Genera consulta" );
         repo.ConsultaInfo( 34 ) ;
         
         loger.info( "consumo de SW" );
         List<TTResumenCripto> Trx = sw.Trx();

         loger.info( "Termina el consumo del api y comienza el guardado en Oracle" );
         repo.GuardaTodo( Trx );
         
         Long horaTermina = System.currentTimeMillis() ;
         loger.info( "Termina a las: " + ( new Date().toString() ) + " En: " + ( horaTermina - horaInicio )/1000 + " seg." );
         System.exit( 0 );
      }catch( Exception msj ){
         loger.info( "Error: " + msj.toString() ) ;
      }
   }
}