package com.ceiba.biblioteca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resultadoPrestamo")
public class ResultadoPrestamo {
	
	@Id
	private long id;
	
	@Column(name="fecha_maxima_devolucion")
	private String fechaMaximaDevolucion;
		 
    public ResultadoPrestamo() {
		super();
	}
	 
	public ResultadoPrestamo(int id, String fechaMaximaDevolucion) {
		super();
		this.id = id;
		this.fechaMaximaDevolucion = fechaMaximaDevolucion;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFechaMaximaDevolucion() {
		return fechaMaximaDevolucion;
	}
	public void setFechaMaximaDevolucion(String fechaMaximaDevolucion) {
		this.fechaMaximaDevolucion = fechaMaximaDevolucion;
	}
	 
	 
}
