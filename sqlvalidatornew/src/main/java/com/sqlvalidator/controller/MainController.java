package com.sqlvalidator.controller;

import java.util.Objects;

import com.sqlvalidator.exception.InvalidSqlStatementException;

public class MainController {

	public void analyseStatement(String sqlStatement) throws InvalidSqlStatementException {
		if (Objects.isNull(sqlStatement)) {
			throw new InvalidSqlStatementException("SQL-Statement ist null. Es kann nicht validiert werden");
		}

//		final Connection connection = DatabaseConnection.getConnection();
//		if (connection != null) {
//			try {
//				final PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
//				preparedStatement.execute();
//			} catch (SQLException e) {
//
//				final String fehlerMessage = e.getMessage();
//
//				if (fehlerMessage.contains("doesn't exist")) {
//					//alles ok
//					return;
//				} else {
//					final String fehlerZeile = fehlerMessage.substring(fehlerMessage.length() - 1);
//
//					final int erstesVorkommenVomApostroph = fehlerMessage.indexOf("'");
//					final int letztesVorkommenVomApostroph = fehlerMessage.lastIndexOf("'");
//
//					final String fehlerVeursachenderInput = fehlerMessage.substring(erstesVorkommenVomApostroph,
//							letztesVorkommenVomApostroph);
//					final String message = "Das SQL-Statement ist syntaktisch nicht korrekt. Bitte überprüfe in Zeile "
//							+ fehlerZeile + ": " + fehlerVeursachenderInput;
//					Notification.show(message);
//
//				}
//
//			}
//
//		}

	}

}
