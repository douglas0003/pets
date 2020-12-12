package com.douglasferreira.testepets.controle;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douglasferreira.testepets.model.Historico;
import com.douglasferreira.testepets.model.Pet;
import com.douglasferreira.testepets.services.PetsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pets")
@Api(value="API - Pets")
@CrossOrigin(origins = "*")
public class PetsControle {
	
	@Autowired
	private PetsService petsService;
	
	@RequestMapping(method = RequestMethod.GET )
	@ApiOperation(value = "Esse método retorna uma lista de pets cadastrados - Listar")
	public ResponseEntity <List<Pet>> listar() {	
		return ResponseEntity.status(HttpStatus.OK).body(petsService.listar());
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET )
	@ApiOperation(value = "Esse método retorna um pet cadastrado por (ID) - Listar")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Pet pet = petsService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(pet);
	}
	
	@RequestMapping(method = RequestMethod.POST )
	@ApiOperation(value = "Esse método (Salva/Cadastra) Pets - Salvar")
	public ResponseEntity<Void> salvar(@Valid @RequestBody Pet pet) {
		pet = petsService.salvar(pet);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pet.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.DELETE )
	@ApiOperation(value = "Esse método (Apaga/Deleta) um pet por (ID) - Deletar")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {	
		petsService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.PUT )
	@ApiOperation(value = "Esse método Atualiza um pet por (ID) - Atualizar")
	public ResponseEntity<Void> atualizar(@RequestBody Pet pet , @PathVariable("id") Long id) {
		pet.setId(id);
		petsService.atualizar(pet);	
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/historico", method = RequestMethod.POST)
	@ApiOperation(value = "Esse método (Salva/Cadastra) um (Comentário/Histórico) relacionado ao Pet - Salvar")
	public ResponseEntity<Void> adicionarHistorico(@PathVariable("id") Long petId, @RequestBody Historico historico) {
		
		petsService.salvarHistorico(petId, historico);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}/historico", method = RequestMethod.GET)
	@ApiOperation(value = "Esse método retorna um histórico cadastrado por (ID) - Listar")
	public ResponseEntity<List<Historico>> listarHistoricos(@PathVariable("id") Long petId){
		List<Historico> historicos = petsService.listarHistoricos(petId);
		
		return ResponseEntity.status(HttpStatus.OK).body(historicos);
	}
	
}
