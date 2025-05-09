package com.carga.repositorio;

import java.util.List ;
import java.util.Map ;
import java.util.Collections ;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate ;

import com.carga.modelo.GridComVts;
import java.util.HashMap;

/**
 *
 * @author chief
 **/
public class Parametrisaciones {

   private OrigenesDatos OD ;
   
   public Parametrisaciones() {
      OD = new OrigenesDatos() ;
   }
   
   public List< GridComVts > ObtenTrxCompraVenta( Map<String, Object> filtroQuery )throws Exception {
      NamedParameterJdbcTemplate npjdbct = new NamedParameterJdbcTemplate( OD.DS() ) ;
      
      List< GridComVts > trx = npjdbct.query(   "SELECT T.TRXID\n" +
                                                "   , T.INTERCAMBIO\n" +
                                                "   , T.MONTO\n" +
                                                "   , T.TIPOTRX\n" +
                                                "   , T.PRECIO\n" +
                                                "   , TO_CHAR( T.FECHATRXTZ, 'DD-MM-YYYY HH12:MI:SS PM' ) FECHALOCAL\n" +
                                                "   , TO_CHAR( T.FECHAALTA, 'DD-MM-YYYY HH12:MI:SS PM' ) FECHAINSERCION\n" +
                                                "FROM MDRG.TATRXBITSO T \n" +
                                                "WHERE TRUNC( T.FECHATRXTZ ) = TO_DATE( :Fecha, 'DD-MM-YYYY' )\n" +
                                                "   AND T.INTERCAMBIO IN( :IdMercados )\n" +
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