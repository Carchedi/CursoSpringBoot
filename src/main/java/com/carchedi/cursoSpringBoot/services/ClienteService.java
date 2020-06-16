package com.carchedi.cursoSpringBoot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.carchedi.cursoSpringBoot.domain.Cliente;
import com.carchedi.cursoSpringBoot.repositories.ClienteRepository;
import com.carchedi.cursoSpringBoot.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id +", Tipo: "+
																  Cliente.class.getName()));
	}
}
