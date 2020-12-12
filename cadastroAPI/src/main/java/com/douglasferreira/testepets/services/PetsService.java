package com.douglasferreira.testepets.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.douglasferreira.testepets.dao.HistoricosDAO;
import com.douglasferreira.testepets.dao.PetsDAO;
import com.douglasferreira.testepets.model.Historico;
import com.douglasferreira.testepets.model.Pet;
import com.douglasferreira.testepets.services.exceptions.PetNaoEncontradoException;

@Service
public class PetsService {
	
	@Autowired
	private PetsDAO petsDAO;
	
	@Autowired
	private HistoricosDAO historicosDAO;
	
	// Método de Listar todos
	public List<Pet> listar(){
		return petsDAO.findAll();	
	}
	
	// Método de Busca por Id
	public Pet buscar(Long id) {
		Pet pet = petsDAO.findById(id).orElse(null);
		
		if(pet == null) {
			throw new PetNaoEncontradoException("O pet não pode ser encontrado");
		}
		return pet;
	}
	
	// Método de salvar
	public Pet salvar(Pet pet) {
		pet.setId(null); // Criado para garantir que vai ser salvo sempre um novo
		pet.setDtCadastro(new Date());
		return pet = petsDAO.save(pet);
	}
	
	// Método de deletar
	public void deletar(Long id) {
		try {
			petsDAO.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new PetNaoEncontradoException("O pet não pode ser encontrado");
		}
	}
	
	// Método de atualizar
	public void atualizar(Pet pet) {
		checarExistencia(pet);
		petsDAO.save(pet);
	}
	
	//Checa se existe um pet com determinado id
	public void checarExistencia(Pet pet) {
		buscar(pet.getId());
	}
	
	public Historico salvarHistorico(Long petId, Historico historico) {
		Pet pet = buscar(petId);
		
		historico.setPet(pet);
		historico.setData(new Date());
		
		return historicosDAO.save(historico);
	}
	
	public List<Historico> listarHistoricos(Long petId) {
		Pet pet = buscar(petId);
		
		return pet.getHistoricos();
	}

}
