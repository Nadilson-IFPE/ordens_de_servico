package com.nadilson.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nadilson.os.domain.Cliente;
import com.nadilson.os.domain.OS;
import com.nadilson.os.domain.Tecnico;
import com.nadilson.os.domain.enums.Prioridade;
import com.nadilson.os.domain.enums.Status;
import com.nadilson.os.repositories.ClienteRepository;
import com.nadilson.os.repositories.OSRepository;
import com.nadilson.os.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private OSRepository osRepository;
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Nadilson", "045.002.320-60", "(88) 98888-8888");
		Cliente c1 = new Cliente(null, "Silvânia", "961.110.310-74", "(88) 98887-7777");
		OS os1 = new OS(null, Prioridade.ALTA, "Trocar a fonte do notebook", Status.ANDAMENTO, t1, c1);
		
		t1.getLista().add(os1);
		c1.getLista().add(os1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}
}
