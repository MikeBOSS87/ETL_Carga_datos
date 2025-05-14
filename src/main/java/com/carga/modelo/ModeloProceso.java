package com.carga.modelo;

import java.util.List;
import java.util.Map;

/**
 *
 * @author MIGUEL DARIO RESENDIZ GUTIERREZ
 */
public interface ModeloProceso {
   
   public List< TTResumenCripto > ConsultaInfo( Integer id_parametria ) ;
   
   public String BorraTabla(  ) throws Exception ;
   
   public void GuardaTodo( List< TTResumenCripto > Entidades ) throws Exception ;
   
   public List< GridComVts > ConsultaInfoComVta( Map<String, Object> filtroQuery )throws Exception ;
   
}
