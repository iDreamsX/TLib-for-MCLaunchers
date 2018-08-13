package fr.trxyy.launcher.profile;

import java.util.UUID;

import fr.trxyy.launcher.version.Versions;

public class Account {
	
	public static String username = "";
	public static String password = "";
	public static String uuId = "";
//	public static String ram = "";
	public static String accessToken = "0a1b2c3d4e5f6g4h89egzr";
	public static String clientToken = "1a2b3c5g9t5a4d1c2v3f6d5";
	private static String userType = "legacy";
	private static Versions version;
	
	public Account(String user, String uuid, String acctoken, String cToken) {
		this.username = user;
		this.uuId = uuid;
		this.accessToken = acctoken;
		this.clientToken = cToken;
	}
	
	public static String getClientToken() {
		return clientToken;
	}

	public static void setClientToken(String clientToken_) {
		clientToken = clientToken_;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username_) {
		username = username_;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password_) {
		password = password_;
	}

	public static String getUuID() {
		return uuId;
	}

	public static void setUuID(String uuID_) {
		uuId = uuID_;
	}

	public static String getAccessToken() {
		return accessToken;
	}

	public static void setAccessToken(String accessToken_) {
		accessToken = accessToken_;
	}

	public static String getUserType() {
		return userType;
	}

	public static void setUserType(String userType_) {
		userType = userType_;
	}

	public static Versions getVersion() {
		return version;
	}
	
	public static void setVersion(Versions vrsion) {
		version = vrsion;
	}
	
//	public static String getRam() {
//		return ram;
//	}
//
//	public static void setRam(String rrr) {
//		ram = rrr;
//	}

}
