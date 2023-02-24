package com.sqlvalidator.views;

import com.sqlvalidator.controller.MainController;
import com.sqlvalidator.exception.InvalidSqlStatementException;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageInputI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean. Use the @PWA
 * annotation make the application installable on phones, tablets and some
 * desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 */
@Route
@PWA(name = "Vaadin Application", shortName = "Vaadin App", description = "This is an example Vaadin application.", enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@PageTitle("SQLValidator | Home")
public class MainView extends VerticalLayout {

	final MainController mainController = new MainController();

	private final MessageInput messageInput;
	private final H1 pageHeader;
	private final H2 nameHeader;

	public MainView() {

		messageInput = createMessageInput();
		pageHeader = createMainHeader();
		nameHeader = createNameHeader();

		setCssProperties();

		add(pageHeader, nameHeader, messageInput);

	}

	private MessageInput createMessageInput() {
		final MessageInput messageInput = new MessageInput();
		messageInput.getStyle().set("height", "30%");
		messageInput.getStyle().set("width", "80%");

		final MessageInputI18n i18n = new MessageInputI18n().setMessage("Enter your SQL-Statement").setSend("Send");
		messageInput.setI18n(i18n);

		messageInput.getChildren().forEach(component -> {
			Notification.show(component.getElement().getText());
		});

		messageInput.addSubmitListener(listener -> {
			final String sqlStatement = listener.getValue();
			try {
				mainController.analyseStatement(sqlStatement);
				Notification.show("Das SQL-Statement ist syntaktisch korrekt");
			} catch (InvalidSqlStatementException invalidsqlstatementexception) {
				Notification.show(invalidsqlstatementexception.getMessage());
			}
		});

		return messageInput;
	}

	private H1 createMainHeader() {
		final H1 header = new H1("SQL-Validator");
		header.getStyle().set("font-size", "150px");
		header.getStyle().set("font-family", "Yeon Sung, cursive");
		header.getStyle().set("margin-top", "20px");
		header.getStyle().set("margin-bottom", "20px");
		return header;
	}

	private H2 createNameHeader() {
		final H2 nameHeader = new H2("Burak Goekgoez & Mohamed Elmi");
		nameHeader.getStyle().set("font-family", "Yeon Sung, cursive");
		nameHeader.getStyle().set("margin-top", "20px");
		nameHeader.getStyle().set("margin-bottom", "20px");

		return nameHeader;
	}

	public void setCssProperties() {
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);
	}

}
