package fr.trxyy.launcher.fxutil;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TALert {

	public TALert(String text, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle("Erreur");
		alert.setHeaderText("Argh! Une erreur est survenue!");
		alert.setContentText(text);
		alert.show();
	}
}
