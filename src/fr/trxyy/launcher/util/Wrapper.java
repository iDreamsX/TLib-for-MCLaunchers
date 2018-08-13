package fr.trxyy.launcher.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.trxyy.launcher.constants.TConstants;

public class Wrapper {
	
	public static String LAUNCHER_NAME = TConstants.LAUNCHER_NAME;
	
	public static void log(String txt) {
		System.out.println("[" + LAUNCHER_NAME + "] " + txt);
	}
	
	public static void err(String txt_) {
		System.err.println("[" + LAUNCHER_NAME + "] [/!\\] " + txt_);
	}

	public static void displayIntro() {
		log("______________________");
		log("|_____Using_TLib_____|");
		
		log("");
		log(System.getProperty("os.name") + " v" + System.getProperty("os.version") + " - " + System.getProperty("sun.arch.data.model") + "bits.");
		log("Java " + System.getProperty("java.version") + " - " + System.getProperty("java.vendor"));
	}
}
