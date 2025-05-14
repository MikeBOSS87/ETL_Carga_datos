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
 * @author MIGUEL DARIO RESENDIZ GUTIERREZ
 */
@Getter
@Setter
public class Ticker {
   private Boolean success ;
	private List< TTResumenCripto > payload ;
}
