package mod.upcraftlp.playerluckyblocks.init;

import static core.upcraftlp.craftdev.API.util.ModHelper.getIsModLoaded;
import static mod.upcraftlp.playerluckyblocks.init.LuckyModCompat.ModIds.*;

/**
 * (c)2017 UpcraftLP
 */
public class LuckyModCompat { //TODO moar compat! ;D

    public static void init() { //TODO check modids
        if(getIsModLoaded(JEI)) {
            //TODO JEI compat
        }
        if(getIsModLoaded(LUCKY_BLOCKS)) {
            //TODO lucky block compat
        }
        if(getIsModLoaded(SPARKS_HAMMERS)) {
            //TODO hammer compat
            //TODO tinkers compat
            //TODO weaponcase loot compat
        }
        if(getIsModLoaded(SPECTRITE)) {
            //TODO spectrite compat
        }
        if(getIsModLoaded(SHULKER_ARMOR)) {
            //TODO shulker-armor compat
        }
        if(getIsModLoaded(RUBY_TOOLS)) {
            //TODO rubytools compat
        }
        if(getIsModLoaded(INFUSED_WEAPONRY)) {
            //TODO weaponry compat
        }
    }

    /**
     * holder class for {@code modid} constats
     */
    public static class ModIds {

        public static final String JEI = "jei";
        public static final String SPECTRITE = "spectritemod";
        public static final String SHULKER_ARMOR = "shulkerarmor";
        public static final String LUCKY_BLOCKS = "lucky";
        public static final String SPARKS_HAMMERS = "sparkshammers";
        public static final String RUBY_TOOLS = "art";
        public static final String INFUSED_WEAPONRY = "iweap";

    }
}
