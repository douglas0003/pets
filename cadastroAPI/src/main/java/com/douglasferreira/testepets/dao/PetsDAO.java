package com.douglasferreira.testepets.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglasferreira.testepets.model.Pet;

public interface PetsDAO extends JpaRepository<Pet, Long> {
	
}
