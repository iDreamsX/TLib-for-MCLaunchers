package fr.trxyy.launcher.update;

import java.util.HashMap;
import java.util.prefs.Preferences;

import fr.trxyy.launcher.forgelauncher.ForgeLauncher;
import fr.trxyy.launcher.fxutil.TLabel;

public class Updater {
	
	public HashMap<String, Integer> filesURL = new HashMap<String, Integer>();
	public static DownloadTask downloadTask;
	public Thread updateThread;
	public boolean isUpdating = false;
	  public TLabel labelDl;
	public Preferences savedData = Preferences.userRoot();
	
	public void checkForUpdate(final XMLParser parser,
			final Updater updater, TLabel custo) {
		
	    if (custo != null) {
	        this.labelDl = custo;
	      } else {
	        this.labelDl = new TLabel(null);
	      }
		
		updateThread = new Thread() {
			public void run() {
//				LauncherPanel.simplePanel.playButton.setDisable(true);
				
					parser.getFilesToDownload(updater);
					if (!filesURL.isEmpty()) {						
						downloadTask = new DownloadTask(filesURL, updateThread, Updater.this.labelDl);
						isUpdating = true;
						downloadTask.startDownloading();
					} else {
						downloadTask = new DownloadTask(filesURL, updateThread, Updater.this.labelDl);
//						Platform.runLater(() -> LauncherPanel.simplePanel.labelPercentage.setText(""));
						isUpdating = false;
						ForgeLauncher.prepareGame();
//						LauncherPanel.simplePanel.playButton.setDisable(false);
					}
			}
		};
		updateThread.start();
	}
}
