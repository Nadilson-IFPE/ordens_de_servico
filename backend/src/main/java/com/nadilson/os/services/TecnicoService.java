package com.nadilson.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nadilson.os.domain.Tecnico;
import com.nadilson.os.dtos.TecnicoDTO;
import com.nadilson.os.repositories.TecnicoRepository;
import com.nadilson.os.services.exceptions.DataIntegrityViolationException;
import com.nadilson.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}
	
	public Tecnico create(TecnicoDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegrityViolationException("CPF já cadastado na base de dados!");
		}
		return tecnicoRepository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}
	
	private Tecnico findByCPF(TecnicoDTO objDTO) {
		Tecnico obj = tecnicoRepository.findByCPF(objDTO.getCpf());
		
		if (obj != null) {
			return obj;
		}
		
		return null;
	}

}
