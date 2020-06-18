package com.carchedi.cursoSpringBoot.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carchedi.cursoSpringBoot.domain.Cliente; 

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	//@Transactional(readOnly = true)
	Cliente findByEmail(String email);
	
}