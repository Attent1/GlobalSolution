package com.CSH.exception;

public class EquipeBOException extends Exception{

	public EquipeBOException() {
		super("Lenght do Array fora do esperado.");
	}	
	
	public EquipeBOException(String msg) {
		super(msg);
	}
	
	
}
