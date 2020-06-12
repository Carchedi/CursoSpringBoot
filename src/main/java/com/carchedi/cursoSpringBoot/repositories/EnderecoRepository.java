package com.carchedi.cursoSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carchedi.cursoSpringBoot.domain.Endereco; 

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
}