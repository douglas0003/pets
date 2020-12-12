package com.douglasferreira.testepets.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglasferreira.testepets.model.Historico;

public interface HistoricosDAO extends JpaRepository<Historico, Long> {

}
