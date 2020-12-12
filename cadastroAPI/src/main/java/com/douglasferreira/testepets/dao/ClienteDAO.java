package com.douglasferreira.testepets.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglasferreira.testepets.model.Cliente;

public interface ClienteDAO extends JpaRepository<Cliente, Long>{

}
