package fr.trxyy.launcher.constants;

import java.io.File;

import fr.trxyy.launcher.util.McDir;
import fr.trxyy.launcher.version.Versions;

public class TConstants {
	
	public static String LAUNCHER_NAME;
	public static String DIRECTORY_NAME;
	public static int WIDTH = 880;
	public static int HEIGHT = 520;
	public static String RESOURCE_PKG_LOCATION = "/resources/";
	public static String DOWNLOAD_URL = "http://localhost/";
	public static Versions VERSION;
	public static String PREFERENCES_ABREV = "ab";
	
	public static void setParameters(String title, String dir, int w, int h, String resLoc, String dlUrl, Versions v, String abrv) {
		LAUNCHER_NAME = title;
		DIRECTORY_NAME = dir;
		WIDTH = w;
		HEIGHT = h;
		RESOURCE_PKG_LOCATION = resLoc;
		DOWNLOAD_URL = dlUrl;
		VERSION = v;
		PREFERENCES_ABREV = abrv;
	}

	/** CONSTANTS FOR LAUNCHING GAME */
	public static File DIRECTORY = McDir.getDirectory();
	public static String GAMEDIR = new File(DIRECTORY, "game").getAbsolutePath();
	public static String JARDIR = new File(GAMEDIR, "game.jar").getAbsolutePath();
	public static String NATIVESDIR = new File(GAMEDIR, "natives").getAbsolutePath();
	public static String ASSETSDIR = new File(DIRECTORY, "assets").getAbsolutePath();
	public static String LIBRARIESDIR = new File(DIRECTORY, "libraries").getAbsolutePath();
	/** OTHERS */
	public static String LOGS = "";
	public static String SKIN_URL = "https://minotar.net/avatar/";
	public static String MOJANG_AUTHENTICATE_URL = "https://authserver.mojang.com/authenticate";
	public static String MOJANG_REFRESH_URL = "https://authserver.mojang.com/refresh";
	public static String MOJANG_VALIDATE_URL = "https://authserver.mojang.com/validate";

	public static String getResourceLocation() {
		return RESOURCE_PKG_LOCATION;
	}
	
	public static String getLauncherName() {
		return LAUNCHER_NAME;
	}
	
	public static int getWidth() {
		return WIDTH;
	}
	
	public static int getHeight() {
		return HEIGHT;
	}

	public static String getDownloadUrl() {
		// TODO Auto-generated method stub
		return DOWNLOAD_URL;
	}
	
}
