/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carga.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 *
 * @author MIGUEL DARIO RESENDIZ GUTIERREZ
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TTResumenCripto{

   private Integer unique ;
   private String ask ;
	private String bid ;
   private String book ;
   private String change_24 ;
	private String created_at ;
   private String high ;
   private String last ;
	private String low ;
	private String volume ;
   private String vwap ;
}