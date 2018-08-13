package fr.trxyy.launcher.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import fr.trxyy.launcher.constants.TConstants;

public class TConfig {
	
	public File usernameFile;
	public static File logsFile;
	public static File ramFile;
	public static File logsFolder;
	public static File filePath;
	private Random rand = new Random();
	
	public TConfig(File path_) {
		filePath = path_;
		usernameFile = new File(filePath + "/lastlogin.dat");
		if (!usernameFile.exists()){
			try {
				usernameFile.createNewFile();
			} catch (IOException e) {}
		}

		ramFile = new File(filePath + "/configuration.dat");
		if (!ramFile.exists()){
			try {
				ramFile.createNewFile();
			} catch (IOException e) {}
		}
		
		logsFolder = new File(filePath + "/logs");
		if (!logsFolder.exists()){
			logsFolder.mkdirs();
		}
		
		logsFile = new File(logsFolder + "/launcherLog.log");
		if (!logsFile.exists()){
			try {
				logsFile.createNewFile();
			} catch (IOException e) {}
		}
		
	}
	
	public static void generateLog(String txt_) {
		try {
			FileWriter fw = new FileWriter(logsFile);
			fw.write("------------------------------ LOGS ------------------------------");
			fw.write(txt_);
			fw.write("\n\n\n/!\\ This Log was generated automatically. If vous have a problem with this launcher, please send it to @Trxyy /!\\");
			fw.close();
			Wrapper.log("Logs Generated.");
		} catch (IOException e) {}
	}
	
	public void setUsername(String username) {
		try {
			FileWriter fw = new FileWriter(usernameFile);
			fw.write(username);
			fw.close();
			Wrapper.log("Username saved. (" + username + ")");
		} catch (IOException e) {
			Wrapper.err(e.toString());
		}
	}
	
	public void saveRam(String s) {
		try {
			FileWriter fw = new FileWriter(ramFile);
			fw.write(s);
			fw.close();
			Wrapper.log("RAM saved. (" + s + ")");
		} catch (IOException e) {
			Wrapper.err(e.toString());
		}
	}
	
	public String getUsername(String def) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(usernameFile));
			String username = br.readLine();
			br.close();
			Wrapper.log("Username loaded. (" + username + ")");
			return username;
		} catch (IOException e) {
			Wrapper.err(e.toString());
		}
		return def;
	}

	public String getRam(String def) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(ramFile));
			String ram = br.readLine();
			br.close();
			Wrapper.log("RAM loaded. (" + ram + ")");
			return ram;
		} catch (IOException e) {
			Wrapper.err(e.toString());
		}
		return def;
	}
	
	public static void onExit() {
//    	if (Account.getUsername().length() > 1) {
//		Configuration.CONFIG_DATA.setUsername(Account.getUsername());
//    	}
    	Wrapper.log("Shutting down.");
    	TConfig.generateLog(TConstants.LOGS);
	}
	
}
