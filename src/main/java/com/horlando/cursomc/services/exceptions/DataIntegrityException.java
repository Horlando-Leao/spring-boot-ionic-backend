package com.horlando.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg) {
		super(msg);// reaproveitando a estrutura de exceções da ling. Java
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);// reaproveitando a estrutura de exceções da ling. Java
	}
}
