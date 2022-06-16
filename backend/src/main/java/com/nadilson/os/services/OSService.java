package com.nadilson.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nadilson.os.domain.OS;
import com.nadilson.os.repositories.OSRepository;
import com.nadilson.os.services.exceptions.ObjectNotFoundException;

@Service
public class OSService {

	@Autowired
	private OSRepository osRepository;
	
	public OS findById(Integer id) {
		Optional<OS> obj = osRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " +id + ", Tipo: " + OS.class.getName()));
	}
	
	public List<OS> findAll() {
		return osRepository.findAll();
	}
}
