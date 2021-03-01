package com.horlando.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);// reaproveitando a estrutura de exceções da ling. Java
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);// reaproveitando a estrutura de exceções da ling. Java
	}
}
