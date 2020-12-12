package com.douglasferreira.testepets.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.douglasferreira.testepets.model.Erros;
import com.douglasferreira.testepets.services.exceptions.ClienteExistenteException;
import com.douglasferreira.testepets.services.exceptions.ClienteNaoEncontradoException;
import com.douglasferreira.testepets.services.exceptions.PetNaoEncontradoException;

@ControllerAdvice
public class ControleExceptionHandler {

	@ExceptionHandler(PetNaoEncontradoException.class)
	public ResponseEntity<Erros> handlePetNaoEncontradoException(PetNaoEncontradoException e, HttpServletRequest request){
		
		Erros erroApresentado = new Erros();
		erroApresentado.setStatus(404l);
		erroApresentado.setTitulo("O Pet não pode ser encontrado");
		erroApresentado.setTimestamp(System.currentTimeMillis());
		erroApresentado.setMsg("Maiores Detalhes em : http://erros.testepets.com/informaçãoAPI-404");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroApresentado);	
	}
	
	@ExceptionHandler(ClienteExistenteException.class)
	public ResponseEntity<Erros> handleClienteNaoEncontradoException(ClienteExistenteException e, HttpServletRequest request){
		
		Erros erroApresentado = new Erros();
		erroApresentado.setStatus(409l);
		erroApresentado.setTitulo("O Cliente já existe");
		erroApresentado.setTimestamp(System.currentTimeMillis());
		erroApresentado.setMsg("Maiores Detalhes em : http://erros.testepets.com/informaçãoAPI-409");
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erroApresentado);	
	}
	
	@ExceptionHandler(ClienteNaoEncontradoException.class)
	public ResponseEntity<Erros> handleClienteNaoEncontradoException(ClienteNaoEncontradoException e, HttpServletRequest request){
		
		Erros erroApresentado = new Erros();
		erroApresentado.setStatus(409l);
		erroApresentado.setTitulo("O Cliente não pode ser encontrado");
		erroApresentado.setTimestamp(System.currentTimeMillis());
		erroApresentado.setMsg("Maiores Detalhes em : http://erros.testepets.com/informaçãoAPI-404");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroApresentado);	
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Erros> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request){
		
		Erros erroApresentado = new Erros();
		erroApresentado.setStatus(400l);
		erroApresentado.setTitulo("Requisição Inválida");
		erroApresentado.setTimestamp(System.currentTimeMillis());
		erroApresentado.setMsg("Maiores Detalhes em : http://erros.testepets.com/informaçãoAPI-404");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroApresentado);	
	}
	
	//TODO: Uma observação que queria deixar aqui é que poderia ter feito generico, 
	//		 não fiz porque decidi mostrar que estava tratando individualmente cada um de forma mais clara.
	
	
}
