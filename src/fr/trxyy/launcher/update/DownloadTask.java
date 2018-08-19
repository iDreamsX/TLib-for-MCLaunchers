package fr.trxyy.launcher.update;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import fr.trxyy.launcher.constants.TConstants;
import fr.trxyy.launcher.util.McDir;
import fr.trxyy.launcher.util.Wrapper;
import javafx.application.Platform;

public class DownloadTask {
	public int downloadedFiles = 0;
	public int needToDownload = 0;
	public int totalSize = 0;
	public int downloadedSize;
	public double percentage = 0;

	public static HashMap<String, LauncherFile> files = new HashMap();
	public Thread updateThread;
	public Updater updater = new Updater();

	public DownloadTask(HashMap<String, LauncherFile> totalFiles, Thread thread) {
		this.files = totalFiles;
		this.updateThread = thread;
		this.needToDownload = totalFiles.size();
		this.totalSize = this.calcSize();
		Wrapper.log(this.needToDownload + " files need to be up to date.");
	}

	public void startDownloading() {
		for (String name : this.files.keySet()) {
			String fileDest = name.replaceAll(TConstants.DOWNLOAD_URL, "");
			String fileName = fileDest;
			int index = fileName.lastIndexOf("\\");
			String dirLocation = fileName.substring(index + 1);
			this.download(TConstants.getDownloadUrl() + name, McDir.getDirectory() + File.separator + dirLocation, dirLocation);
			if (this.downloadedFiles == this.needToDownload) {
				this.updateThread.interrupt();
				this.updater.isUpdating = false;
				Wrapper.log("Download finished.");
			}
		}
	}

	public void download(String fileUrl, String fileName, String flNm) {
		Wrapper.log("Downloading: " + flNm);
		updater.getProgressBar().setCurrentFile(flNm);
		try {
			URL url = new URL(fileUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			int filesize = connection.getContentLength();
			float totalDataRead = 0;

			File file = new File(fileName);
			file.getParentFile().mkdirs();
			file.createNewFile();

			BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
			FileOutputStream fos = new FileOutputStream(fileName);
			BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte[] data = new byte[1024];
			int read = 0;
			while ((read = in.read(data, 0, 1024)) >= 0) {
				this.downloadedSize += read;
				bout.write(data, 0, read);

				double total = this.totalSize;
				double actuel = this.downloadedSize;
				if (total == 0.0D) {
					total = 0.1D;
				}
				percentage = ((actuel / total * 100) / 10000.0D);
			}
			Platform.runLater(() -> updater.getProgressBar().setProgress(percentage));
			bout.close();
			in.close();
			this.downloadedFiles++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int calcSize() {
		double size = 0.0D;
		int i = 0;
		for (Map.Entry<String, LauncherFile> entry : files.entrySet()) {
			size += ((LauncherFile) entry.getValue()).getSize();
			i++;
		}
		Wrapper.log("Total size of needed files: " + size);
		return (int) (size / 100.0D);
	}
}
