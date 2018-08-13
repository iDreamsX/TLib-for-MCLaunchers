package fr.trxyy.launcher.fxutil;

import fr.trxyy.launcher.util.Wrapper;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.MediaPlayer;

public class MusicLoader {

	public static MediaPlayer player;

	public ResourceLocation resourceLocation = new ResourceLocation();

	public MusicLoader(String f) {
        Wrapper.log("Loading Music...");
		try {
			player = new MediaPlayer(resourceLocation.getMedia(f));
			player.setAutoPlay(true);
			player.setCycleCount(-1);
			player.setVolume(0.4);
			player.play();
		} catch (Exception e) {
			new TALert(e.toString(), AlertType.ERROR);
		}
	}
}
