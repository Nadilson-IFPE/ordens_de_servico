package com.nadilson.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Tecnico extends Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "tecnico")
	private List<OS> lista = new ArrayList<>();
	
	public Tecnico() {
		super();
	}

	public Tecnico(Integer id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
	}

	public List<OS> getLista() {
		return lista;
	}

	public void setLista(List<OS> lista) {
		this.lista = lista;
	}
	
	

}
