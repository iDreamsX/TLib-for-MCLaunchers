package fr.trxyy.launcher.fxutil;

import java.awt.Point;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fr.trxyy.launcher.util.TConfig;
import fr.trxyy.launcher.util.Wrapper;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class TApp {

	final Point dragDelta = new Point();

	public TApp(Stage stage, Scene scene, String title, int width, int height, StageStyle style) {
		Wrapper.log("Initializing Launcher...");
		
	    try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			new TALert(e.toString(), AlertType.ERROR);
			Wrapper.err(e.toString());
		}
		
		stage.initStyle(style);
		stage.setResizable(false);
		stage.setWidth(width);
		stage.setHeight(height);
		stage.setTitle(title);
    	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
            	TConfig.onExit();
                Platform.exit();
                System.exit(0);
            }
    	});
		
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				dragDelta.x = (int) (stage.getX() - mouseEvent.getScreenX());
				dragDelta.y = (int) (stage.getY() - mouseEvent.getScreenY());
			}
		});
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				stage.setX(mouseEvent.getScreenX() + dragDelta.x);
				stage.setY(mouseEvent.getScreenY() + dragDelta.y);
			}
		});
		
		stage.setScene(scene);
		Wrapper.log("Displaying Panel... (Trying)");
		stage.show();
		Wrapper.log("Displaying Panel... (Successfully)");
	}
	
	public static void setIconImage(Stage primaryStage, Image img) {
		primaryStage.getIcons().add(img);
	}

}
