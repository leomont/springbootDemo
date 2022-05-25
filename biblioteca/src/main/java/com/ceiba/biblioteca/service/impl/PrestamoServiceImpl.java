package com.ceiba.biblioteca.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import com.ceiba.biblioteca.model.Prestamo;
import com.ceiba.biblioteca.repository.PrestamoRepository;
import com.ceiba.biblioteca.service.PrestamoService;

public class PrestamoServiceImpl implements PrestamoService{
	
	private PrestamoRepository repo;
 
	@Override
	public Prestamo findByIdentificacionUsuario(String identificacionUsuario) {
		Prestamo prestamo = repo.findByIdentificacionUsuario(identificacionUsuario);
		return prestamo;
	}

	@Override
	public String computefechaMaximaDevolucion(int tipoUsuario) {
		SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        
	    Calendar fechaDevolucionCalendario = computeFechaMaximaDevolucionCalendar(tipoUsuario);
		
        Date fechaDevolucionDate = fechaDevolucionCalendario.getTime();
            
		return dtf.format(fechaDevolucionDate);
	}

	
	public Calendar computeFechaMaximaDevolucionCalendar(int tipoUsuario) {
		
		Calendar FechaActual = Calendar.getInstance();
		
		Calendar fechaFinal = Calendar.getInstance();
		
		int maximoDias = 0;
		int diaspasados = 0;

		switch (tipoUsuario) {
		case 1:
			maximoDias = 10;
			break;
		case 2:
			maximoDias = 8;
			break;
		case 3:
			maximoDias = 7;
			break;
		default:
			break;
		}

		for (int numeroDeDiasPrestamo = 1; numeroDeDiasPrestamo < maximoDias; numeroDeDiasPrestamo++) 
		{
			if(FechaActual.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && FechaActual.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) 
			{
				fechaFinal.add(Calendar.DATE, 1);
			}            
		}
		
		return fechaFinal;
	}
}
