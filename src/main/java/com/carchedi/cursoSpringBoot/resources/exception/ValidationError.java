package com.carchedi.cursoSpringBoot.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp); 
	}

	public List<FieldMessage> getList() {
		return errors;
	}

	public void addError(String fieldName, String menssagem) {
		errors.add(new FieldMessage(fieldName, menssagem));
	}
}
