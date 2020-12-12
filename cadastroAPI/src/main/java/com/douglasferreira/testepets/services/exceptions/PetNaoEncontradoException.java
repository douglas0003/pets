package com.douglasferreira.testepets.services.exceptions;

public class PetNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = -1701913952699931719L;

	public PetNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public PetNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
