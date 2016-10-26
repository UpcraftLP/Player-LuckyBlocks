package mod.upcraftlp.playerluckyblocks.init;

import core.upcraftlp.craftdev.API.common.ModRegistry;
import mod.upcraftlp.playerluckyblocks.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class LuckyConfig {

	private static boolean enableDebug;

	public static void init(FMLPreInitializationEvent event) {
		Configuration config = ModRegistry.getConfigFile(event, Reference.MODID);
		config.load();
		/** Configuration Start **/
			enableDebug = config.getBoolean("debugMode", Configuration.CATEGORY_GENERAL, true, "enable/disable additional cosole output for developers");
		
		/** Configuration End **/
		config.save();
	}
	
	public static boolean debugMode() {
		return enableDebug;
	}
	
}
