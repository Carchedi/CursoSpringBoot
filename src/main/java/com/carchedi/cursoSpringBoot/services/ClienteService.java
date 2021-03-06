package com.carchedi.cursoSpringBoot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.carchedi.cursoSpringBoot.domain.Cidade;
import com.carchedi.cursoSpringBoot.domain.Cliente;
import com.carchedi.cursoSpringBoot.domain.Endereco;
import com.carchedi.cursoSpringBoot.domain.enums.TipoCliente;
import com.carchedi.cursoSpringBoot.dto.ClienteDTO;
import com.carchedi.cursoSpringBoot.dto.ClienteNewDTO;
import com.carchedi.cursoSpringBoot.repositories.CidadeRepository;
import com.carchedi.cursoSpringBoot.repositories.ClienteRepository;
import com.carchedi.cursoSpringBoot.repositories.EnderecoRepository;
import com.carchedi.cursoSpringBoot.services.exceptions.DataIntegrityException;
import com.carchedi.cursoSpringBoot.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository repoCidade;
	
	@Autowired
	private EnderecoRepository repoEndereco;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id +", Tipo: "+
																  Cliente.class.getName()));
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		repoEndereco.saveAll(obj.getEnderecos());
		return obj;
	}

	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);		
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas.");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page,  linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO obj) {
		return new Cliente(obj.getId(),obj.getNome(),obj.getEmail(),null,null);
	}
	
	public Cliente fromDTO(ClienteNewDTO obj) {
		Cliente cli = new Cliente(null, obj.getNome(), obj.getEmail(), obj.getCpfOuCnpj(), TipoCliente.toEnum(obj.getTipo()));
		Optional<Cidade> cid = repoCidade.findById(obj.getCidadeId());
		Cidade cid1 = new Cidade();
		if(cid != null) {
			cid1 = cid.get();
		}
		Endereco end = new Endereco(null, obj.getLogradouro(), obj.getNumero(), obj.getComplemento(), obj.getBairro(), obj.getCep(), cid1, cli);
		cli.getEnderecos().add(end);
		cli.getTelefone().add(obj.getTelefone1());
		if(obj.getTelefone2() != null) {
			cli.getTelefone().add(obj.getTelefone2());
		}
		if(obj.getTelefone3() != null) {
			cli.getTelefone().add(obj.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
