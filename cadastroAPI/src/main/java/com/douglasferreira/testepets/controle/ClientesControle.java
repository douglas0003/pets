package com.douglasferreira.testepets.controle;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douglasferreira.testepets.model.Cliente;
import com.douglasferreira.testepets.services.ClientesService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clientes")
public class ClientesControle {
	
	@Autowired
	private ClientesService clientesService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Esse método retorna uma lista de clientes cadastrados - Listar")
	public ResponseEntity <List<Cliente>> Listar() {
		return ResponseEntity.status(HttpStatus.OK).body(clientesService.listar());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Esse método (Salva/Cadastra) Cliente - Salvar")
	public ResponseEntity<Void> salvar(@Valid @RequestBody Cliente cliente) {
		cliente = clientesService.salvar(cliente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(cliente.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	@ApiOperation(value = "Esse método retorna um cliente cadastrado por (ID) - Listar")
	public ResponseEntity<Cliente> buscar(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(clientesService.buscar(id));
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.DELETE )
	@ApiOperation(value = "Esse método (Apaga/Deleta) um cliente por (ID) - Deletar")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {	
		clientesService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.PUT )
	@ApiOperation(value = "Esse método Atualiza um cliente por (ID) - Atualizar")
	public ResponseEntity<Void> atualizar(@RequestBody Cliente cliente , @PathVariable("id") Long id) {
		cliente.setId(id);
		clientesService.atualizar(cliente);	
		return ResponseEntity.noContent().build();
	}
	
	
}