package com.douglasferreira.testepets.services.exceptions;

public class ClienteExistenteException extends RuntimeException {

	private static final long serialVersionUID = -1701913952699931719L;

	public ClienteExistenteException(String mensagem) {
		super(mensagem);
	}

	public ClienteExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
