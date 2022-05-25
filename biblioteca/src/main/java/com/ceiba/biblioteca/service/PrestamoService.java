package com.ceiba.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.biblioteca.model.Prestamo;
import com.ceiba.biblioteca.repository.PrestamoRepository;

@Service
public interface PrestamoService {

	public Prestamo findByIdentificacionUsuario(String identificacionUsuario);
	
	public String computefechaMaximaDevolucion(int tipoUsuario);
}
