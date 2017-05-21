package mod.upcraftlp.playerluckyblocks;

import java.time.Year;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.ForgeVersion;

public class Reference {

    public static final String MCVERSIONS = "[1.11,1.11.2]";
    public static final String VERSION = "@VERSION@";

    /** TEAM **/
    public static final String[] authors = {"UpcraftLP"};
    public static final String MOD_DESCRIPTION = "Player Lucky Blocks" + ForgeVersion.mcVersion;
    public static final String CREDITS = TextFormatting.GOLD + "\u00A9" + "2016-" + Year.now().getValue() + " UpcraftLP";

    // DO NOT CHANGE!!!
    public static final String MODNAME = "Player Lucky Blocks";
    public static final String MODID = "playerluckyblocks";
    public static final String DEPENDENCIES = "required-after:craftdevcore@[2.0.3,)";
    public static final String UPDATE_URL = "https://minecraft.curseforge.com/projects/player-lucky-blocks";
    public static final String UPDATE_JSON = "https://raw.githubusercontent.com/UpcraftLP/Player-LuckyBlocks-1.10/master/Version.json";
    public static final String CLIENTSIDE_PATH = "mod.upcraftlp." + MODID + ".proxy.ClientProxy";
    public static final String SERVERSIDE_PATH = "mod.upcraftlp." + MODID + ".proxy.ServerProxy";
    public static final String ID_PREFIX = MODID + ":";
    public static final String GUI_FACTORY = "mod.upcraftlp.playerluckyblocks.config.LuckyConfigGuiFactory";
    public static final ResourceLocation POTION_ICON = new ResourceLocation(MODID, "textures/gui/potions.png");
    
    /**
     * get the texture offsets for a 32x32 texture on a 256x256 texture sheet<br/>
     * <b>Potions:</b><br/>
     * Flight:      0<br/>
     * Dizzyness:   1<br/>
     * Giant:       2<br/>
     */
    public static int[] getTextureOffset(int index) {
        int rowOffset = Math.floorDiv(index, 8) * 32;
        int columnOffset = (index % 8) * 32;
        return new int[]{rowOffset, columnOffset};
    }
    
}
