package fr.trxyy.launcher.fxutil;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class TButton extends Button {

	public TButton(Pane root) {
		setUnHover(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setOpacity(0.80);
			}
		});
		setHover(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setOpacity(1.0);
			}
		});
		root.getChildren().add(this);
	}

	public void setSize(int width_, int height_) {
		setPrefSize(width_, height_);
	}
	
	public void setInvisible() {
		setBackground(null);
	}

	public void setPosition(int posX, int posY) {
		setLayoutX(posX);
		setLayoutY(posY);
	}
	
	public void setAction(EventHandler<? super MouseEvent> value) {
		onMouseClickedProperty().set(value);
	}
	
    public final void setHover(EventHandler<? super MouseEvent> value) {
        onMouseEnteredProperty().set(value);
    }
    
    public final void setUnHover(EventHandler<? super MouseEvent> value) {
        onMouseExitedProperty().set(value);
    }
}
