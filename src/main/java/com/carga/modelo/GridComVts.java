/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carga.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

/**
 *
 * @author MIGUEL DARIO RESENDIZ GUTIERREZ
 */
@Getter
@Setter
@AllArgsConstructor
public class GridComVts{
   
   private Integer Trx ;
   private String TipoIntercambio ;
	private Double MontoCripto ;
	private String TipoTrx ;
	private String ValorCripto ;
	private String FechaLocal ;
	private String FechaAlta ;
}