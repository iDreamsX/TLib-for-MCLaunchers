package fr.trxyy.launcher.update;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import fr.trxyy.launcher.constants.TConstants;
import fr.trxyy.launcher.forgelauncher.ForgeLauncher;
import fr.trxyy.launcher.fxutil.TLabel;
import fr.trxyy.launcher.util.McDir;
import fr.trxyy.launcher.util.Wrapper;
import javafx.application.Platform;

public class DownloadTask {
	// folderSize((OSUtil.getDirectory() + "/assets/indexes")) != 88096
	
    public TLabel labelDownload;
	private int downloadedFiles = 0;
	private int filesToDownload = 0;
	private HashMap<String, Integer> fileLinks = new HashMap<String, Integer>();
	private Thread updateThread;
	private Updater updater = new Updater();
	long toSend;
	public static String state = "";
	
//	public static SimpleProgressBar bar_;
	
	public DownloadTask(HashMap<String, Integer> links, Thread thread, final TLabel custom) {
		labelDownload = custom;
		fileLinks = links;
		updateThread = thread;
		filesToDownload = links.size();
		Wrapper.log(filesToDownload + " files need to be up to date.");
	}

	public void startDownloading() {
		for (String name : fileLinks.keySet()) {
			String fileDest = name.replaceAll(TConstants.DOWNLOAD_URL, "");
			String fileName = fileDest;
			int index = fileName.lastIndexOf("\\");
			String fileName2 = fileName.substring(index + 1);
			downloadFile(name, McDir.getDirectory() + File.separator + fileDest, fileName2, fileLinks.get(name));
			if (downloadedFiles == filesToDownload) {
				updateThread.interrupt();
//				LauncherPanel.simplePanel.playButton.setDisable(false);
				updater.isUpdating = false;
				Platform.runLater(() -> this.labelDownload.setText("Launching..."));
				ForgeLauncher.prepareGame();
//				Platform.runLater(() -> LauncherPanel.simplePanel.labelPercentage.setText(""));
			}
		}
	}

	private void downloadFile(String urlS, String dest, String fileName, int fileLength) {
		try {
			byte[] buffer = new byte[4096];
			URL url = new URL(urlS);
			URLConnection urlCo = url.openConnection();
			int size = urlCo.getContentLength();
			InputStream is = urlCo.getInputStream();
			File file = new File(dest);
			file.getParentFile().mkdirs();
			file.createNewFile();
			FileOutputStream outs = new FileOutputStream(file);
			toSend = 0L;
			int read;

	          long downloadStartTime = System.currentTimeMillis();
	          int downloadedAmount = 0;
	          String downloadSpeedMessage = "";
			
			while ((read = is.read(buffer)) > 0) {
				outs.write(buffer, 0, read);
				toSend += read;
//				Platform.runLater(() -> LauncherPanel.simplePanel.labelPercentage.setText("Telechargement de " + fileName + " (" + fileLength / 1024L + "Ko)"));
//				Platform.runLater(() -> state = "Telechargement de " + fileName + " (" + fileLength / 1024L + "Ko)");
                Platform.runLater(() -> this.labelDownload.setText(new StringBuilder("Telechargement de " + fileName + " (" + fileLength / 1024L + "Ko)").toString()));
//                Platform.runLater(() -> this.labelDownload.setText(new StringBuilder(String.valueOf(this.filesToDownload - this.downloadedFiles)).toString()));
				
			}
//			bar_.setProgress(downloadedFiles);
			outs.close();
			is.close();
			downloadedFiles++;
//			Wrapper.log(downloadSpeedMessage);
			Wrapper.log("Downloading: " + dest + " | " + file.length());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long folderSize(String directory) {
		File curDir = new File(directory);
		long length = 0;
		for (File f : curDir.listFiles()) {
			if (f.isDirectory()) {
				for (File child : f.listFiles()) {
					length = length + child.length();
				}

			} else {
				length = f.length();
			}
//			length = 0;
		}
		return length;
	}
	
	public static int readSizeFromUrl(String url_) {
		int foo = 0;
		try {
			URL url = new URL(url_);
			
		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		    
		    String line;
		    while ((line = in.readLine()) != null) {
		    	System.out.println(line);
			    String txttt = line;
//			    Wrapper.log("txttt: " + txttt);
				foo = Integer.parseInt(txttt);
		    }
//		    String txttt = line;
//		    Wrapper.log("txttt: " + txttt);
//			foo = Integer.parseInt(txttt);
		    in.close();
		}
		catch (MalformedURLException e) {
			System.out.println("Malformed URL: " + e.getMessage());
		}
		catch (IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
		}
		return foo;
	}
	
    public TLabel getLabelDownload() {
        return this.labelDownload;
    }

}
