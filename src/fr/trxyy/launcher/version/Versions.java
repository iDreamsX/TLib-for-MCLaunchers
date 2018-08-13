package fr.trxyy.launcher.version;

public enum Versions {
	
	V1_7_2("1.7.2", "legacy", "net.minecraft.client.main.Main", "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --uuid ${auth_uuid} --accessToken ${auth_access_token}"),
	V1_7_2_FORGE("1.7.2", "legacy", "net.minecraft.launchwrapper.Launch", "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --uuid ${auth_uuid} --accessToken ${auth_access_token} --tweakClass cpw.mods.fml.common.launcher.FMLTweaker"),
	V1_7_10("1.7.10", "1.7.10", "net.minecraft.client.main.Main", "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid ${auth_uuid} --accessToken ${auth_access_token} --userProperties ${user_properties} --userType ${user_type}"),
	V1_7_10_FORGE("1.7.10", "1.7.10", "net.minecraft.launchwrapper.Launch", "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid ${auth_uuid} --accessToken ${auth_access_token} --userProperties ${user_properties} --userType ${user_type} --tweakClass cpw.mods.fml.common.launcher.FMLTweaker"),
	V1_8("1.8", "1.8", "net.minecraft.client.main.Main", "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid ${auth_uuid} --accessToken ${auth_access_token} --userProperties ${user_properties} --userType ${user_type}"),
	V1_8_FORGE("1.8", "1.8", "net.minecraft.launchwrapper.Launch", "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid ${auth_uuid} --accessToken ${auth_access_token} --userProperties ${user_properties} --userType ${user_type} --tweakClass net.minecraftforge.fml.common.launcher.FMLTweaker"),
	V1_9("1.9", "1.9", "net.minecraft.client.main.Main", "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid ${auth_uuid} --accessToken ${auth_access_token} --userType ${user_type} --versionType ${version_type}"),
	V1_9_FORGE("1.9", "1.9", "net.minecraft.launchwrapper.Launch", "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid ${auth_uuid} --accessToken ${auth_access_token} --userType ${user_type} --versionType ${version_type} --tweakClass net.minecraftforge.fml.common.launcher.FMLTweaker"),
	V1_10("1.10", "1.10", "net.minecraft.client.main.Main", "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid ${auth_uuid} --accessToken ${auth_access_token} --userType ${user_type} --versionType ${version_type}"),
	V1_10_FORGE("1.10", "1.10", "net.minecraft.launchwrapper.Launch", "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid ${auth_uuid} --accessToken ${auth_access_token} --userType ${user_type} --versionType ${version_type} --tweakClass net.minecraftforge.fml.common.launcher.FMLTweaker"),
	V1_11("1.11", "1.11", "net.minecraft.client.main.Main", "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid ${auth_uuid} --accessToken ${auth_access_token} --userType ${user_type} --versionType ${version_type}"),
	V1_11_FORGE("1.11", "1.11", "net.minecraft.launchwrapper.Launch", "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid ${auth_uuid} --accessToken ${auth_access_token} --userType ${user_type} --versionType ${version_type} --tweakClass net.minecraftforge.fml.common.launcher.FMLTweaker"),
	V1_12("1.12", "1.12", "net.minecraft.client.main.Main", "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid ${auth_uuid} --accessToken ${auth_access_token} --userType ${user_type} --versionType ${version_type}"),
	V1_12_FORGE("1.12", "1.12", "net.minecraft.launchwrapper.Launch", "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid ${auth_uuid} --accessToken ${auth_access_token} --userType ${user_type} --versionType ${version_type} --tweakClass net.minecraftforge.fml.common.launcher.FMLTweaker"),
	V1_13("1.13", "1.13", "net.minecraft.client.main.Main", "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid ${auth_uuid} --accessToken ${auth_access_token} --userType ${user_type} --versionType ${version_type}"),
	CUSTOM("?.?.?", "?.?.?", "?.?.?", "?.?.?");
	
    private final String version;
    private final String assetsIndex;
    private final String mainClass;
    private final String minecraftArguments;
	
	private Versions(String v, String assts, String main, String args) {
		this.version = v;
		this.assetsIndex = assts;
		this.mainClass = main;
		this.minecraftArguments = args;
	}
	
    public String getVersion()
    {
        return version;
    }
    
    public String getAssetsIndex()
    {
        return assetsIndex;
    }
    
    public String getMainClass()
    {
        return mainClass;
    }
    
    public String getArguments()
    {
        return minecraftArguments;
    }
}
