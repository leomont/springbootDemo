package com.ceiba.biblioteca.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(computefechaMaximaDevolucion(1));
	}
	
	public static String computefechaMaximaDevolucion(int tipoUsuario) {
		SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        
	    Calendar fechaDevolucionCalendario = computeFechaMaximaDevolucionCalendar(tipoUsuario);
		
        Date fechaDevolucionDate = fechaDevolucionCalendario.getTime();
        
		return dtf.format(fechaDevolucionDate);
	}

	
	public static Calendar computeFechaMaximaDevolucionCalendar(int tipoUsuario) {

		Calendar fecha = Calendar.getInstance();

		int maximoDias = 0;

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
			if(fecha.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && fecha.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) 
			{
				fecha.add(Calendar.DATE, 1);
			}            
		}

		return fecha;
	}

}
