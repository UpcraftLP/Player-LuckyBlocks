package mod.upcraftlp.playerluckyblocks;

import java.time.Year;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.ForgeVersion;

public class Reference {

    public static final String MCVERSIONS = "1.11";
    public static final String VERSION = "0.3.1";

    /** TEAM **/
    public static final String[] authors = {"UpcraftLP"};
    public static final String MOD_DESCRIPTION = "Player Lucky Blocks" + ForgeVersion.mcVersion;
    public static final String CREDITS = TextFormatting.GOLD + "\u00A9" + Year.now().getValue() + " UpcraftLP";

    // DO NOT CHANGE!!!
    public static final String MODNAME = "Player Lucky Blocks";
    public static final String MODID = "playerluckyblocks";
    public static final String DEPENDENCIES = "required-after:craftdevcore";
    public static final String UPDATE_URL = "https://minecraft.curseforge.com/projects/player-lucky-blocks";
    public static final String UPDATE_JSON = "https://raw.githubusercontent.com/UpcraftLP/Player-LuckyBlocks-1.10/master/Version.json";
    public static final String CLIENTSIDE_PATH = "mod.upcraftlp." + MODID + ".proxy.ClientProxy";
    public static final String SERVERSIDE_PATH = "mod.upcraftlp." + MODID + ".proxy.ServerProxy";
    public static final String ID_PREFIX = MODID + ":";
    public static final String GUI_FACTORY = "mod.upcraftlp." + MODID + ".init.LuckyConfigGuiFactory";

}
