package com.ceiba.biblioteca.controller;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.biblioteca.exception.ResourceInternalServerErrorException;
import com.ceiba.biblioteca.exception.ResourceNotFoundException;
import com.ceiba.biblioteca.model.Prestamo;
import com.ceiba.biblioteca.model.ResultadoPrestamo;
import com.ceiba.biblioteca.repository.PrestamoRepository;
import com.ceiba.biblioteca.service.PrestamoService;

@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {

	@Autowired
	private PrestamoRepository prestamoRepository;
	
	private PrestamoService prestamoService;
	
	//1.Crear POST /prestamo
	//Si un usuario tiene mas de un libro devuelve http 400
	/*Si el usuario tiene mas de un libro
	 * {

  	"mensaje" : "El usuario con identificación xxxxxx ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo"

	}
	 * */

	/*
	 * Respuesta HTTP Status Code 200
	 * 
	 * {

     "id": 1,

    "fechaMaximaDevolucion" : "15/02/2021"

	} 

	 *
	 *id de la respuesta para consultar
	 *
	 * Devolver fecha en formato dd/MM/yyyy
	 *
	 */
	@PostMapping
	public ResultadoPrestamo createPrestamo(@Valid @RequestBody Prestamo prestamo) throws ResourceInternalServerErrorException{
		
			prestamoRepository.findByIdentificacionUsuario(prestamo.getIdentificacionUsuario());
			
			prestamo.setFechaMaximaDevolucion(this.computefechaMaximaDevolucion(prestamo.getTipoUsuario()));
			Prestamo savedPrestamo = prestamoRepository.save(prestamo);
			
			ResultadoPrestamo resultado  = new ResultadoPrestamo();
			resultado.setId(savedPrestamo.getId());
			resultado.setFechaMaximaDevolucion(savedPrestamo.getFechaMaximaDevolucion());
			
			return resultado;
	}
	
	/*3.Si tipo de usuario diferente a los valores permitidos
	 * 
	 * {

  	"mensaje" : "Tipo de usuario no permitido en la biblioteca"

	}
	 * 
	 * 
	 * */
	//fecha en formato dd/MM/yyyy
	public String computefechaMaximaDevolucion(int tipoUsuario) {
		SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        
	    Calendar fechaDevolucionCalendario = computeFechaMaximaDevolucionCalendar(tipoUsuario);
		
        Date fechaDevolucionDate = fechaDevolucionCalendario.getTime();
       
		return dtf.format(fechaDevolucionDate);
	}

	
	//2.Calcular fecha de devolucion para tipo de usuario
	//Tipos de usuario
	//1 afiliado: 10 dias sin sabados ni domingos
	//2 empleado: 8 dias sin sabados ni domingos
	//3 invitado: 7 dias sin sabados ni domingos
	public Calendar computeFechaMaximaDevolucionCalendar(int tipoUsuario) {
		
		Calendar fechaActual = Calendar.getInstance();
		
		Calendar fechaFinal = Calendar.getInstance();
		
		int maximoDias = 0;
		int numeroDiasPrestamo=1;
		
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
		
		while(numeroDiasPrestamo <= maximoDias) {			
			if(fechaFinal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && fechaFinal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)
			{
				fechaFinal.add(Calendar.DATE, 1);
				numeroDiasPrestamo++;
			}else
				fechaFinal.add(Calendar.DATE, 1);
			
		}
		
		return fechaFinal;
	}
	
	
	

	
	@GetMapping
	public List<Prestamo> getAllPrestamos(){
		return prestamoRepository.findAll();
	}
	
	
	
	//3. Devuelve informacion del prestamo con el Id
	@GetMapping("/{id}")
	public ResponseEntity<Prestamo> getPrestamoById(@PathVariable(value="id") long prestamoId) throws ResourceNotFoundException {
		Prestamo prestamo = prestamoRepository.findById(prestamoId)
				.orElseThrow(()-> new ResourceNotFoundException("Prestamo no encontrado para el id : " + prestamoId));
		return ResponseEntity.ok().body(prestamo);
	}
	

}

