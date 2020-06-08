package com.carchedi.cursoSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carchedi.cursoSpringBoot.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> { 
}
