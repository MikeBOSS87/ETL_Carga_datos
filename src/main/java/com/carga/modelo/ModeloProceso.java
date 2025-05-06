package com.carga.modelo;

import java.util.List;

/**
 *
 * @author chief
 */
public interface ModeloProceso {
   
   public List< TTResumenCripto > ConsultaInfo( Integer id_parametria ) ;
   
   public String BorraTabla(  ) throws Exception ;
   
   public void GuardaTodo( List< TTResumenCripto > Entidades ) throws Exception ;
}
