package mod.upcraftlp.playerluckyblocks.init;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import core.upcraftlp.craftdev.API.common.ModHelper;
import mod.upcraftlp.playerluckyblocks.Reference;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class LuckyConfig {

	public static Configuration config;
	private static final String CATEGORY_OTHER = "other";
	
	/**Config values**/
	public static boolean enableDeathSound;
	public static boolean pussyMode;
	
	public static void syncConfig() {
		
		/** Configuration Start **/
		enableDeathSound = config.getBoolean("enableDeathSound", Configuration.CATEGORY_GENERAL, true, "en/disable custom death sound");
		pussyMode = config.getBoolean("pussyMode", CATEGORY_OTHER, false, "are you serious?");
		/** Configuration End **/
		
		config.setCategoryRequiresMcRestart(CATEGORY_OTHER, true);
		if(config.hasChanged()) config.save();
	}

	public static void init(FMLPreInitializationEvent event) {
		config = ModHelper.getConfigFile(event, Reference.MODID);
		config.load();
		syncConfig();
	}
	
	public static boolean isDebugMode() {
		return ModHelper.isDebugMode();
	}
	
	public static List<IConfigElement> getEntries() {
		List<IConfigElement> entries = new ArrayList<IConfigElement>();
		Set<String> categories = config.getCategoryNames();
		Iterator<String> i = categories.iterator();
		while(i.hasNext()) {
			String categoryName = i.next();
			ConfigCategory category = config.getCategory(categoryName);
			entries.addAll(new ConfigElement(category).getChildElements());
		}
		return entries;
	}
	
	@SubscribeEvent
	public static void configChanged(OnConfigChangedEvent event) {
		if(event.getModID().equals(Reference.MODID)) {
			syncConfig();
		}
	}
	
}
