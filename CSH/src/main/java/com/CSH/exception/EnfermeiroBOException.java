package com.CSH.exception;

public class EnfermeiroBOException extends Exception{


	public EnfermeiroBOException() {
		super("ID informado ou coren com valor 0.");
	}	
	
	public EnfermeiroBOException(String msg) {
		super(msg);
	}
}
