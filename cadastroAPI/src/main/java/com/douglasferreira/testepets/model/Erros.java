package com.douglasferreira.testepets.model;

public class Erros {

	private String titulo;
	
	private Long status;

	private String msg;
	
	private Long timestamp;

	public String getTitulo() {
		return titulo;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
