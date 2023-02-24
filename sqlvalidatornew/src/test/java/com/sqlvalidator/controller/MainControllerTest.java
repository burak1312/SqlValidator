package com.sqlvalidator.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sqlvalidator.exception.InvalidSqlStatementException;

class MainControllerTest {

	private MainController mainController;

	@BeforeEach
	void setUp() {
		mainController = new MainController();
	}

	@Test
	void testAnalyseStatementThrowsInvalidSqlStatementException_WhenNullGiven() {
		try {
			mainController.analyseStatement(null);
			fail("Dieser Test soll eine InvalidSqlStatementException triggern");
		} catch (InvalidSqlStatementException e) {
			assertEquals("SQL-Statement ist null. Es kann nicht validiert werden", e.getMessage());
		}
	}

}
