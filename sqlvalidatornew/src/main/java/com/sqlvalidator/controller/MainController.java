package com.sqlvalidator.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import com.sqlvalidator.database.DatabaseConnection;
import com.vaadin.flow.component.notification.Notification;

import net.sf.jsqlparser.JSQLParserException;

public class MainController {

	public void analyseStatement(String sqlStatement) throws JSQLParserException {
		Objects.requireNonNull(sqlStatement);

		final Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				final PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
				preparedStatement.execute();
				Notification.show("Das SQL Statement ist gültig!");
			} catch (SQLException e) {

				final String fehlerMessage = e.getMessage();

				if (fehlerMessage.contains("doesn't exist")) {
					Notification.show("SQL-Statement ist gültig");
					return;
				} else {
					final String fehlerZeile = fehlerMessage.substring(fehlerMessage.length() - 1);

					final int erstesVorkommenVomApostroph = fehlerMessage.indexOf("'");
					final int letztesVorkommenVomApostroph = fehlerMessage.lastIndexOf("'");

					final String fehlerVeursachenderInput = fehlerMessage.substring(erstesVorkommenVomApostroph,
							letztesVorkommenVomApostroph);
					final String message = "Das SQL-Statement ist syntaktisch nicht korrekt. Bitte überprüfe in Zeile "
							+ fehlerZeile + ": " + fehlerVeursachenderInput;
					Notification.show(message);

				}

			}

		}
	}
}