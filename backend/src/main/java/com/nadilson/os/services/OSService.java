package com.nadilson.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nadilson.os.domain.Cliente;
import com.nadilson.os.domain.OS;
import com.nadilson.os.domain.Tecnico;
import com.nadilson.os.domain.enums.Prioridade;
import com.nadilson.os.domain.enums.Status;
import com.nadilson.os.dtos.OSDTO;
import com.nadilson.os.repositories.OSRepository;
import com.nadilson.os.services.exceptions.ObjectNotFoundException;

@Service
public class OSService {

	@Autowired
	private OSRepository osRepository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	public OS findById(Integer id) {
		Optional<OS> obj = osRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OS.class.getName()));
	}

	public List<OS> findAll() {
		return osRepository.findAll();
	}

	public OS create(@Valid OSDTO obj) {
		return fromDTO(obj);
	}
	
	public OS update(@Valid OSDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);
	}

	private OS fromDTO(OSDTO obj) {
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacao());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));

		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());

		newObj.setTecnico(tecnico);
		newObj.setCliente(cliente);
		
		if (newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}

		return osRepository.save(newObj);

	}
}
