
package com.carga.consumidor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate ;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.apache.logging.log4j.Logger ;
import org.apache.logging.log4j.LogManager ;
import java.lang.reflect.Type;
import java.util.List;
import com.carga.modelo.TTResumenCripto;
import com.carga.modelo.Ticker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientException;

/**
 *
 * @author chief
 */
public class SW {
   
   private List<TTResumenCripto> Grid ;
   private static final Logger loger = LogManager.getLogger( SW.class ) ;
   
   @Autowired( required = true )
   public List<TTResumenCripto> Trx(){
      Consumidor( "Todos" );
      return Grid ;
   }
   
   private void Consumidor( String Mercado ) {
      RestTemplate template = new RestTemplate();
		String url = "" ;
		try{
         if( "Todos".equals( Mercado ) ) {
            url = "https://stage.bitso.com/api/v3/ticker";
         }
         else if( "BTC-MXN".equals( Mercado ) ) {
            url = "https://api.bitso.com/v3/trades/?book=btc_mxn";
         }
         loger.info( "va a consumir: " + Mercado ) ;
         ResponseEntity<Ticker> WS = template.exchange( url, HttpMethod.GET, HttpEntity.EMPTY,
              new ParameterizedTypeReference< Ticker >()
              {
                @Override
                public Type getType()
                {
                  return super.getType();
                }
              } );
         Ticker obj = WS.getBody() ;
         Grid = obj.getPayload() ;
      }
      catch( RestClientException msj ){
         loger.info( "Error en: " + msj ) ;
      }
   }
}