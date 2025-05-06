/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carga.modelo;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author chief
 */
@Getter
@Setter
public class Ticker {
   @Getter @Setter private Boolean success ;
	@Getter @Setter private List< TTResumenCripto > payload ;
}
