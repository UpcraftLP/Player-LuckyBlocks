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
	private static final int PATCH = 1;
	
	//increase every time code is changed and pushed to GitHub;
	//never decrease
	private static final int BUILD = 2;
	
	//current version of CraftDev-Core codebase version when releasing this mod
	//must be equal or the coremod won't allow Forge to launch successfully!
	public static final int CODEBASE = 1;
	
	//current build of CraftDev-Core when releasing this mod
	//must be lower than or equal to the current build of the coremod!
	public static final int MINIMUM_BUILD = 2;
	
	/** TEAM **/
	
	public static final String[] authors = {"UpcraftLP"};
	
	public static final String MOD_DESCRIPTION = "Player Lucky Blocks" + " "+ MCVERSION;
	public static final String CREDITS = "UpcraftLP";
	
	//DO NOT CHANGE!!!
	public static final String MODNAME = "Player Lucky Blocks";
	public static final String MODID = "playerluckyblocks";
	public static final String DEPENDENCIES = "required-after:craftdev-core";
	public static final String UPDATE_URL = ""; //CurseForge page
	public static final String INTERNAL_UPDATE_URL = "https://raw.githubusercontent.com/UpcraftLP/Player-LuckyBlocks-1.10/master/Version.txt";
	public static final String CLIENTSIDE_PATH = "mod.upcraftlp." + MODID + ".proxy.ClientProxy";
	public static final String SERVERSIDE_PATH = "mod.upcraftlp." + MODID + ".proxy.ServerProxy";
	public static final String VERSION = MAJOR + "." + MINOR + "." + PATCH + "-b" + BUILD;
	public static final String ID_PREFIX = MODID + ":";

	

	
}
