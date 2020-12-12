package com.douglasferreira.testepets.services.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = -1701913952699931719L;

	public ClienteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ClienteNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
