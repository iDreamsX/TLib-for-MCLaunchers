package fr.trxyy.launcher.update;

import java.util.HashMap;

import fr.trxyy.launcher.fxutil.TProgressBar;
import fr.trxyy.launcher.util.Wrapper;

public class Updater {
	
	public static HashMap<String, LauncherFile> files = new HashMap();
	public DownloadTask downloadTask;
	public Thread updateThread;
	public boolean isUpdating = false;
	public static TProgressBar bar;
	
	public void checkForUpdate(final String username_, final String password, final XMLParser parser, final Updater updater, TProgressBar suppBar) {
		
		if (suppBar != null) {
			bar = suppBar;
		}
		else {
			bar = new TProgressBar();
		}
		
		updateThread = new Thread() {
			public void run() {
					parser.getFilesToDownload();
					if (!files.isEmpty()) {
						downloadTask = new DownloadTask(files, updateThread);
						isUpdating = true;
						downloadTask.startDownloading();
					} else {
						downloadTask = new DownloadTask(files, updateThread);
						isUpdating = false;
						// LAUNCHGAME
						Wrapper.log("No Update needed. Launching game...");
					}
			}
		};
		updateThread.start();
	}
	
	public DownloadTask getDownloader() {
		return this.downloadTask;
	}

	public TProgressBar getProgressBar() {
		return this.bar;
	}
}
