package fr.trxyy.launcher.forgelauncher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.prefs.Preferences;

import fr.trxyy.launcher.constants.TConstants;
import fr.trxyy.launcher.profile.Account;
import fr.trxyy.launcher.util.McDir;
import fr.trxyy.launcher.util.Wrapper;
import fr.trxyy.launcher.util.McDir.OperatingSystem;
import fr.trxyy.launcher.version.Versions;

public class ForgeLauncher {

	public static File DIRECTORY = McDir.getDirectory();
	public static String GAMEDIR = new File(DIRECTORY, "game").getAbsolutePath();
	public static String JARDIR = new File(GAMEDIR, "game.jar").getAbsolutePath();
	public static String NATIVESDIR = new File(GAMEDIR, "natives").getAbsolutePath();
	public static String ASSETSDIR = new File(DIRECTORY, "assets").getAbsolutePath();
	public static String LIBRARIESDIR = new File(DIRECTORY, "libraries").getAbsolutePath();
	private static Versions VERSION = TConstants.VERSION;
	private static String MINECRAFTARGUMENTS = VERSION.getArguments();
	public static Preferences prefs = Preferences.userRoot();
	private static File LAUNCHFILE;
	
	public ForgeLauncher() {}

	public static void prepareGame() {
		prepareArgs();
		if (McDir.getOS() == McDir.OperatingSystem.windows) {
			Wrapper.log("Launching windows..");
			launchForWindows();
		} else if (McDir.getOS() == McDir.OperatingSystem.macos) {
			launchForMacOS();
		} else if (McDir.getOS() == McDir.OperatingSystem.linux) {
			launchForOthers();
		}
	}

	public static void launchForWindows() {
		ProcessBuilder pb = new ProcessBuilder(new String[0]);
		ArrayList<String> commands = new ArrayList();
		commands.add(getJavaDir());
//		commands.add(prefs.get(LauncherConstants.PREFERENCES_ABREV + "_ram", "-%CUSTOMRAM%1024M").replace("%CUSTOMRAM%", "-Xmx"));
		commands.add("-XX:HeapDumpPath=MojangTricksIntelDriversForPerformance_javaw.exe_minecraft.exe.heapdump");
		commands.add("-Dfml.ignoreInvalidMinecraftCertificates=true");
		commands.add("-Dfml.ignorePatchDiscrepancies=true");
//		commands.add(prefs.get(LauncherConstants.PREFERENCES_ABREV + "_ram", "-%CUSTOMRAM%1024M").replace("%CUSTOMRAM%", "-Xms"));
		commands.add("-Djava.library.path=" + new File(NATIVESDIR).getAbsolutePath());
		commands.add("-cp");
		commands.add(getClasspath());
		commands.add(VERSION.getMainClass());
		String[] args = MINECRAFTARGUMENTS.split(" ");
		commands.addAll(Arrays.asList(args));
		Wrapper.log(commands.toString());
		LAUNCHFILE = McDir.writeFile("offline-windows.bat", commands.toString());
		pb.command(commands);
		pb.inheritIO();
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	private static void launchForMacOS() {
		ArrayList<String> commands = new ArrayList();
		commands.add(getJavaDir());
		commands.add(prefs.get(TConstants.PREFERENCES_ABREV + "_ram", "-%CUSTOMRAM%1024M").replace("%CUSTOMRAM%", "-Xmx"));
		commands.add("-Xdock:name=" + "custom");
		commands.add("-XX:+UseConcMarkSweepGC");
		commands.add("-XX:+CMSIncrementalMode");
		commands.add("-XX:-UseAdaptiveSizePolicy");
		commands.add("-XX:HeapDumpPath=MojangTricksIntelDriversForPerformance_javaw.exe_minecraft.exe.heapdump");
		commands.add(prefs.get(TConstants.PREFERENCES_ABREV + "_ram", "-%CUSTOMRAM%1024M").replace("%CUSTOMRAM%", "-Xms"));
		commands.add("-Djava.library.path=" + new File(NATIVESDIR).getAbsolutePath());
		commands.add("-Dminecraft.client.jar=" + JARDIR);
		commands.add("-cp");
		commands.add(getClasspath());
		commands.add(VERSION.getMainClass());
		String[] args = MINECRAFTARGUMENTS.split(" ");
		commands.addAll(Arrays.asList(args));
		
		LAUNCHFILE = McDir.writeFile("offline-linuxOS.sh", commands.toString());
		LAUNCHFILE.setExecutable(true);
		LAUNCHFILE.setReadable(true);
		LAUNCHFILE.setWritable(true);
		
		ProcessBuilder pb = new ProcessBuilder(new String[] { LAUNCHFILE.getAbsolutePath() });
		Process proc  = null;
		try {
			proc = pb.redirectErrorStream(true).start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			proc.waitFor();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		pb.directory(DIRECTORY);
		Wrapper.log(commands.toString());
		pb.command(commands);
		pb.inheritIO();
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	private static void launchForOthers() {
		ArrayList<String> commands = new ArrayList();
		commands.add(getJavaDir());
		commands.add(prefs.get(TConstants.PREFERENCES_ABREV + "_ram", "-%CUSTOMRAM%1024M").replace("%CUSTOMRAM%", "-Xmx"));
		commands.add("-Xdock:name=" + "custom");
		commands.add("-XX:+UseConcMarkSweepGC");
		commands.add("-XX:+CMSIncrementalMode");
		commands.add("-XX:-UseAdaptiveSizePolicy");
		commands.add(prefs.get(TConstants.PREFERENCES_ABREV + "_ram", "-%CUSTOMRAM%1024M").replace("%CUSTOMRAM%", "-Xms"));
		commands.add("-Djava.library.path=" + new File(NATIVESDIR).getAbsolutePath());
		commands.add("-Dminecraft.client.jar=" + JARDIR);
		commands.add("-cp");
		commands.add(getClasspath());
		commands.add(VERSION.getMainClass());
		String[] args = MINECRAFTARGUMENTS.split(" ");
		commands.addAll(Arrays.asList(args));
		
		LAUNCHFILE = McDir.writeFile("offline-othersOS.sh", commands.toString());
		LAUNCHFILE.setExecutable(true);
		LAUNCHFILE.setReadable(true);
		LAUNCHFILE.setWritable(true);
		
		try {
			Runtime.getRuntime().exec("chmod u=rwx,g=r,o=- " + LAUNCHFILE.getAbsolutePath());
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		ProcessBuilder pb = new ProcessBuilder(new String[] { LAUNCHFILE.getAbsolutePath() });
		Process proc  = null;
		try {
			proc = pb.redirectErrorStream(true).start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			proc.waitFor();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		pb.directory(DIRECTORY);
		Wrapper.log(commands.toString());
		pb.command(commands);
		pb.inheritIO();
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

//	private static String getClasspath() {
//		File libFolder = new File(LIBRARIESDIR);
//		File[] libs = libFolder.listFiles();
//		String classpath = "";
//		if (System.getProperty("os.name").toLowerCase().contains("win")) {
//			for (int i = 0; i < libs.length - 1; i++) {
//				classpath = classpath + libs[i].getAbsolutePath() + ";";
//			}
//			classpath = classpath + JARDIR;
//		} else {
//			for (int i = 0; i < libs.length - 1; i++) {
//				classpath = classpath + libs[i].getAbsolutePath() + ":";
//			}
//			classpath = classpath + JARDIR;
//		}
//
//		return classpath;
//	}
	
	private static String getClasspath() {
        String classpath = "";
        ArrayList<File> libs = McDir.list(new File(LIBRARIESDIR));
        String separator = System.getProperty("path.separator");
        for(File lib : libs)
            classpath += lib.getAbsolutePath() + separator;

        classpath += JARDIR;
        return classpath;
    }

	public static String getJavaDir() {
		String separator = java.lang.System.getProperty("file.separator");
		String path = java.lang.System.getProperty("java.home") + separator + "bin" + separator;
		if ((McDir.getOS() == McDir.OperatingSystem.windows) && (new File(path + "javaw.exe").isFile())) {
			return path + "javaw.exe";
		}
		return path + "java";
	}
	
	private static void prepareArgs() {
		MINECRAFTARGUMENTS = MINECRAFTARGUMENTS.replace("${auth_player_name}", Account.getUsername());
		MINECRAFTARGUMENTS = MINECRAFTARGUMENTS.replace("${version_name}", VERSION.getVersion());
		MINECRAFTARGUMENTS = MINECRAFTARGUMENTS.replace("${game_directory}", GAMEDIR);
		
		if (VERSION.equals(Versions.V1_7_2)) {
			MINECRAFTARGUMENTS = MINECRAFTARGUMENTS.replace("${assets_root}", ASSETSDIR);
			Wrapper.log("Version 1.7.2 detected. assetDir set to " + ASSETSDIR);
		}
		else {
			MINECRAFTARGUMENTS = MINECRAFTARGUMENTS.replace("${assets_root}", ASSETSDIR);
			Wrapper.log("Version 1.7.10 or higher detected. assetDir set to " + ASSETSDIR);
			MINECRAFTARGUMENTS = MINECRAFTARGUMENTS.replace("${assets_index_name}", VERSION.getAssetsIndex());
		}
		
		MINECRAFTARGUMENTS = MINECRAFTARGUMENTS.replace("${auth_uuid}", Account.getUuID());
		MINECRAFTARGUMENTS = MINECRAFTARGUMENTS.replace("${auth_access_token}", Account.getAccessToken());
		
		if ((VERSION.equals(Versions.V1_7_10)) || (VERSION.equals(Versions.V1_8))) {
			Wrapper.log("Version 1.7.10 or 1.8 detected. Settings Arguments...");
			MINECRAFTARGUMENTS = MINECRAFTARGUMENTS.replace("${user_properties}", "{}");
			MINECRAFTARGUMENTS = MINECRAFTARGUMENTS.replace("${user_type}", "mojang");
		}
		if ((VERSION.equals(Versions.V1_9)) || (VERSION.equals(Versions.V1_10)) || (VERSION.equals(Versions.V1_11)) || (VERSION.equals(Versions.V1_12))) {
			Wrapper.log("Version " + VERSION.getVersion() + " is detected. Settings Arguments...");
			MINECRAFTARGUMENTS = MINECRAFTARGUMENTS.replace("${user_type}", "legacy");
			MINECRAFTARGUMENTS = MINECRAFTARGUMENTS.replace("${version_type}", "release");
		}

		System.out.println(MINECRAFTARGUMENTS);
	}
}
