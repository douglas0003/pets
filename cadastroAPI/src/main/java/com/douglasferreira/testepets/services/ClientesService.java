package com.douglasferreira.testepets.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.douglasferreira.testepets.dao.ClienteDAO;
import com.douglasferreira.testepets.model.Cliente;
import com.douglasferreira.testepets.services.exceptions.ClienteExistenteException;
import com.douglasferreira.testepets.services.exceptions.ClienteNaoEncontradoException;
import com.douglasferreira.testepets.services.exceptions.PetNaoEncontradoException;

@Service
public class ClientesService {
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	public List<Cliente> listar() {	
		return clienteDAO.findAll();	
	}
	
	public Cliente salvar(Cliente cliente) {
		if(cliente.getId() != null) {
			Cliente c = clienteDAO.findById(cliente.getId()).orElse(null);
			if(c != null) {
				throw new ClienteExistenteException("O cliente já existe.");
			}
		}
		
		return clienteDAO.save(cliente);
	}
	
	public Cliente buscar(Long id) {
		Cliente cliente = clienteDAO.findById(id).orElse(null);
		
		if(cliente == null) {
			throw new ClienteNaoEncontradoException("O Cliente não pode ser encontrado.");
		}
		return cliente;
	}
	
	// Método de deletar
		public void deletar(Long id) {
			try {
				clienteDAO.deleteById(id);
			} catch (EmptyResultDataAccessException e) {
				throw new PetNaoEncontradoException("O pet não pode ser encontrado");
			}
		}
		
		// Método de atualizar
		public void atualizar(Cliente cliente) {
			checarExistencia(cliente);
			clienteDAO.save(cliente);
		}
	
		//Checa se existe um pet com determinado id
		public void checarExistencia(Cliente cliente) {
			buscar(cliente.getId());
		}
	
	
}
