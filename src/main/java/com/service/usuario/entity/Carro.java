package com.service.usuario.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Carro {
	
	private String brand;
	
	private String model;
	
	private int userId;
}
