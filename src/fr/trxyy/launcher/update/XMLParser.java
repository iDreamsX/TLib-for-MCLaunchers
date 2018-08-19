package fr.trxyy.launcher.update;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.security.MessageDigest;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.trxyy.launcher.constants.TConstants;
import fr.trxyy.launcher.util.McDir;
import fr.trxyy.launcher.util.Wrapper;

public class XMLParser {

	  public static void getFilesToDownload() {
			Proxy proxy = Proxy.NO_PROXY;
	      try {
	          final URL resourceUrl = new URL(TConstants.DOWNLOAD_URL);
	          final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	          final DocumentBuilder db = dbf.newDocumentBuilder();
	          final Document doc = db.parse(resourceUrl.openConnection(proxy).getInputStream());
	          final NodeList nodeLst = doc.getElementsByTagName("Contents");
	          
	          final long start = System.nanoTime();
	          for(int i = 0; i < nodeLst.getLength(); i++) {
	              final Node node = nodeLst.item(i);
	              if(node.getNodeType() == 1) {
	                  final Element element = (Element) node;
	                  final String key = element.getElementsByTagName("Key").item(0).getChildNodes().item(0).getNodeValue();
	                  String etag = element.getElementsByTagName("ETag") != null ? element.getElementsByTagName("ETag").item(0).getChildNodes().item(0).getNodeValue() : "-";
	                  final long size = Long.parseLong(element.getElementsByTagName("Size").item(0).getChildNodes().item(0).getNodeValue());

						File localFile = new File(McDir.getDirectory(), key);
						if (!localFile.isDirectory()) {
							
	                      if(etag.length() > 1) {
	                          etag = FileUtil.getEtag(etag);
	                          
	                          if (localFile.exists()) {
	                          	
	                          	 if(localFile.isFile() && localFile.length() == size) {
	                          		 
	                                 final String localMd5 = FileUtil.getMD5(localFile);
	                                 
	                                 if(localMd5.equals(etag)) {
//	                                	 Updater.files.put(key, new LauncherFile(size, TConstants.DOWNLOAD_URL + key, localFile.getAbsolutePath()));
	                                	 Wrapper.log("Fichier valide !");
	                                 }
	                                 else {
	                                	 Updater.files.put(key, new LauncherFile(size, TConstants.DOWNLOAD_URL + key, localFile.getAbsolutePath()));
	                                 }
	                                 
	                          	 }
	                          }
	                          else {
	                          	if (!(TConstants.DOWNLOAD_URL + key).endsWith("/")) {
	                          		Updater.files.put(key, new LauncherFile(size, TConstants.DOWNLOAD_URL + key, localFile.getAbsolutePath()));
	                          	}
	                          }
	                          
	                      }
						}
						else {
							localFile.mkdir();
							localFile.mkdirs();
						}
	              }
	          }
	          final long end = System.nanoTime();
	          final long delta = end - start;
	          Wrapper.log("Delta time to compare resources: " + delta / 1000000L + " ms ");
	      }
	      catch(final Exception ex) {
	      	Wrapper.log("Couldn't download resources (" + ex + ")");
	      }
	  }
	
	public static String getHash(File file) throws Exception {
		MessageDigest md5Digest = MessageDigest.getInstance("MD5");
		String checksum = getFileChecksum(md5Digest, file);
		return checksum;
	}

	private static String getFileChecksum(MessageDigest digest, File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		byte[] byteArray = new byte[1024];
		int bytesCount = 0;
		while ((bytesCount = fis.read(byteArray)) != -1) {
			digest.update(byteArray, 0, bytesCount);
		}
		;
		fis.close();

		byte[] bytes = digest.digest();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

}
