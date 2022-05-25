package com.ceiba.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.biblioteca.model.Prestamo;

@Repository
public interface PrestamoRepository  extends JpaRepository<Prestamo, Long>{
	
	Prestamo findByIdentificacionUsuario(String identificacionUsuario);
	
}