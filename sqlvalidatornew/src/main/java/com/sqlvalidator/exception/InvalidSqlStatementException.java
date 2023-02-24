package com.sqlvalidator.exception;

public class InvalidSqlStatementException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidSqlStatementException(String msg) {
		super(msg);
	}

}
