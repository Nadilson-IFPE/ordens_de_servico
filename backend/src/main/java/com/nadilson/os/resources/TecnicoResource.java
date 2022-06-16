package com.nadilson.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nadilson.os.domain.Tecnico;
import com.nadilson.os.dtos.TecnicoDTO;
import com.nadilson.os.services.TecnicoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

	@Autowired
	private TecnicoService tecnicoService;

	@GetMapping(value = "/{id}")
	// Retornar a Entidade para o usuário é falha de segurança:
	// public ResponseEntity<Tecnico> findById(@PathVariable Integer id) {
	// Código acima trocado por:
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		Tecnico obj = tecnicoService.findById(id);

		// Resolvendo falha de segurança descrita acima
		// return ResponseEntity.ok().body(obj);

		// Conversão para evitar a falha de segurança:
		TecnicoDTO tecnicoDTO = new TecnicoDTO(obj);
		return ResponseEntity.ok().body(tecnicoDTO);
	}

	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
//		List<Tecnico> lista = tecnicoService.findAll();	
//		List<TecnicoDTO> listaDTO = new ArrayList<>();
//		
//		for (Tecnico obj : lista) {
//			listaDTO.add(new TecnicoDTO(obj));
//		}
//		
//		lista.forEach(obj -> listaDTO.add(new TecnicoDTO()));

		// A linha abaixo resume em uma única linha todo o código comentado acima
		List<TecnicoDTO> listaDTO = tecnicoService.findAll().stream().map(obj -> new TecnicoDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listaDTO);
	}

	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO) {
		Tecnico newObj = tecnicoService.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	// Atualiza os dados de um técnico
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO) {
		TecnicoDTO newObj = new TecnicoDTO(tecnicoService.update(id, objDTO));

		return ResponseEntity.ok().body(newObj);
	}

	// Deleta um técnico
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		tecnicoService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
