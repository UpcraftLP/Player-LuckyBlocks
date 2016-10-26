package mod.upcraftlp.playerluckyblocks;

import net.minecraftforge.common.ForgeVersion;

public class Reference {

	//TODO: version range!
	public static final String MCVERSION = ForgeVersion.mcVersion;
	
	//increase when milestone is reached (currently: first RELEASE version);
	//never decrease
	private static final int MAJOR = 0;
	
	//increase when adding new features
	//set to 0 when MAJOR version increases
	private static final int MINOR = 1;
	
	//increase when fixing bugs;
	//set to 0 when MINOR version increases
	private static final int PATCH = 0;
	
	//increase every time code is changed and pushed to GitHub;
	//never decrease
	private static final int BUILD = 1;
	
	/** TEAM **/
	
	public static final String[] authors = {"UpcraftLP"};
	
	public static final String MOD_DESCRIPTION = "Player Lucky Blocks" + " "+ MCVERSION;
	public static final String CREDITS = "UpcraftLP";
	
	//DO NOT CHANGE!!!
	public static final String MODNAME = "Player Lucky Blocks";
	public static final String MODID = "playerluckyblocks";
	public static final String DEPENDENCIES = "";
	public static final String UPDATE_URL = ""; //CurseForge page
	public static final String INTERNAL_UPDATE_URL = ""; //GitHub link to the Version.txt file
	public static final String CLIENTSIDE_PATH = "mod.upcraftlp." + MODID + ".proxy.ClientProxy";
	public static final String SERVERSIDE_PATH = "mod.upcraftlp." + MODID + ".proxy.ServerProxy";
	public static final String VERSION = MAJOR + "." + MINOR + "." + PATCH + "-b" + BUILD;
	public static final String ID_PREFIX = MODID + ":";
}
