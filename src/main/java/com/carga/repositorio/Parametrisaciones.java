package com.carga.repositorio;

import java.util.List ;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate ;

import com.carga.modelo.GridComVts;
import java.util.Collections;
/**
 *
 * @author chief
 */
public class Parametrisaciones {

   private OrigenesDatos OD ;
   
   public Parametrisaciones() {
      OD = new OrigenesDatos() ;
   }
   
   public List<GridComVts> ObtenTrxCompraVenta( List<String> ids ) {
      NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate( OD.DS() ) ;
      Map filtroQuery = Collections.singletonMap( "ids" ,  ids ) ;

      List<GridComVts> trx = namedJdbcTemplate.query( "SELECT T.TRXID\n" +
               "   , T.INTERCAMBIO\n" +
               "   , T.MONTO\n" +
               "   , T.TIPOTRX\n" +
               "   , T.PRECIO\n" +
               "   , TO_CHAR( T.FECHATRXTZ, 'DD-MM-YYYY HH12:MI:SS PM' ) FECHALOCAL\n" +
               "   , TO_CHAR( T.FECHAALTA, 'DD-MM-YYYY HH12:MI:SS PM' ) FECHAINSERCION\n" +
               "FROM MDRG.TATRXBITSO T \n" +
               "WHERE TRUNC( T.FECHATRXTZ ) = TRUNC( SYSDATE )\n" +
               "   AND T.INTERCAMBIO IN( :ids )\n" +
               "ORDER BY T.TRXID DESC, T.INTERCAMBIO ", 
         filtroQuery,
         ( rs, rowNum ) -> new GridComVts( rs.getInt( "TRXID" )
            , rs.getString("INTERCAMBIO" )
            , rs.getDouble("MONTO" )
            , rs.getString( "TIPOTRX" )
            , rs.getString( "PRECIO" )
            , rs.getString( "FECHALOCAL" )
            , rs.getString( "FECHAINSERCION" ) ) );

    return trx;
}
}
