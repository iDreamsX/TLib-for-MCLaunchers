package fr.trxyy.launcher.fxutil;

import fr.trxyy.launcher.constants.TConstants;
import fr.trxyy.launcher.util.Wrapper;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class TBackground {
	
	public ResourceLocation resourceLocation = new ResourceLocation();
    public int posX = 0;
    public int posY = 0;
    public static double opacity = 1.0;
    public static MediaPlayer player;
    public static MediaView viewer;
	
	public TBackground(Media f, Pane root) {
        Wrapper.log("Loading Media Background...");
		player = new MediaPlayer(f);
		viewer = new MediaView(player);
        viewer.setFitWidth(TConstants.WIDTH);
        viewer.setFitHeight(TConstants.HEIGHT);
        player.setAutoPlay(true);
        viewer.setPreserveRatio(false);
        viewer.setOpacity(opacity);
        player.setCycleCount(-1);
        player.play();
        
        root.getChildren().add(viewer);
	}
	
	public TBackground(Media f, double opa, Pane root) {
        Wrapper.log("Loading Media Background...");
		MediaPlayer player = new MediaPlayer(f);
		MediaView viewer = new MediaView(player);
        viewer.setFitWidth(TConstants.WIDTH);
        viewer.setFitHeight(TConstants.WIDTH);
        player.setAutoPlay(true);
        viewer.setPreserveRatio(false);
        viewer.setOpacity(opa);
        player.setCycleCount(-1);
        player.play();
        
        root.getChildren().add(viewer);
	}
	
	public TBackground(Media f, int posX, int posY, int sizeX, int sizeY, Pane root) {
        Wrapper.log("Loading Media Background...");
		MediaPlayer player = new MediaPlayer(f);
		MediaView viewer = new MediaView(player);
        viewer.setFitWidth(sizeX);
        viewer.setFitHeight(sizeY);
        viewer.setLayoutX(posX);
        viewer.setLayoutX(posY);
        player.setAutoPlay(true);
        viewer.setPreserveRatio(false);
        viewer.setOpacity(opacity);
        player.setCycleCount(-1);
        player.play();
        
        root.getChildren().add(viewer);
	}
	
	public TBackground(Media f, int posX, int posY, int sizeX, int sizeY, double opa, Pane root) {
        Wrapper.log("Loading Media Background...");
		MediaPlayer player = new MediaPlayer(f);
		MediaView viewer = new MediaView(player);
        viewer.setFitWidth(sizeX);
        viewer.setFitHeight(sizeY);
        viewer.setLayoutX(posX);
        viewer.setLayoutX(posY);
        player.setAutoPlay(true);
        viewer.setPreserveRatio(false);
        viewer.setOpacity(opa);
        player.setCycleCount(-1);
        player.play();
        
        root.getChildren().add(viewer);
	}
	
	public static void setOpacity(double opaci) {
		opacity = opaci;
	}
	
	public static MediaPlayer getPlayer() {
		return player;
	}
	
	public static MediaView getViewer() {
		return viewer;
	}
}
