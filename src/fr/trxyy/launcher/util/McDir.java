package fr.trxyy.launcher.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import fr.trxyy.launcher.constants.TConstants;

public class McDir
{
	public enum OperatingSystem
	{
		windows,
		macos,
		linux,
		unknown
	}
	
	public static OperatingSystem getOS()
	{
		String osName = System.getProperty("os.name").toLowerCase();
		
		if(osName.contains("win"))
			return OperatingSystem.windows;
		else if(osName.contains("mac"))
			return OperatingSystem.macos;
		else if(osName.contains("linux"))
			return OperatingSystem.linux;
		else if(osName.contains("unix"))
			return OperatingSystem.linux;
		else if(osName.contains("solaris"))
			return OperatingSystem.linux;
		else if(osName.contains("sunos"))
			return OperatingSystem.linux;
		else
			return OperatingSystem.unknown;
			
	}
	
	public static File getLocalStorage(String dir)
	{
		String userHome = System.getProperty("user.home");
		
		if(getOS() == OperatingSystem.windows)
			return new File(System.getenv("appdata"), "." + dir);
		else if(getOS() == OperatingSystem.macos)
			return new File(userHome, "Library/Application Support/" + dir);
		else
			return new File(userHome, dir);
	}
	
	public static File getDirectory()
	{
		return getLocalStorage(TConstants.DIRECTORY_NAME);
	}

	public static File writeFile(String filename, String toWrite) {
		try {
			if (!getDirectory().exists()) {
				getDirectory().mkdir();
			}
			File file = new File(getDirectory(), filename);
			PrintStream out = new PrintStream(file.getAbsolutePath());
			out.print(toWrite);
			out.close();
			return file;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	  public static ArrayList<File> list(File folder) {
	      ArrayList<File> files = new ArrayList<File>();
	      if(!folder.isDirectory())
	          return files;

	      File[] folderFiles = folder.listFiles();
	      if(folderFiles != null)
	          for(File f : folderFiles)
	              if(f.isDirectory())
	                  files.addAll(list(f));
	              else
	                  files.add(f);

	      return files;
	  }
}
