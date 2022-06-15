package com.nadilson.os.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadilson.os.domain.Tecnico;
import com.nadilson.os.dtos.TecnicoDTO;
import com.nadilson.os.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@GetMapping(value = "/{id}")
	// Retornar a Entidade para o usuário é falha de segurança:
	//public ResponseEntity<Tecnico> findById(@PathVariable Integer id) {
	// Código acima trocado por:
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		Tecnico obj = tecnicoService.findById(id);
		
		// Resolvendo falha de segurança descrita acima
		// return ResponseEntity.ok().body(obj);
		
		// Conversão para evitar a falha de segurança:
		TecnicoDTO tecnicoDTO = new TecnicoDTO(obj);
		return ResponseEntity.ok().body(tecnicoDTO);
	}
	
}
