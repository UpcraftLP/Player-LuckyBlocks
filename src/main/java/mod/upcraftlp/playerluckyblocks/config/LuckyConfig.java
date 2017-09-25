package mod.upcraftlp.playerluckyblocks.config;

import com.google.common.collect.Lists;
import core.upcraftlp.craftdev.api.util.ModHelper;
import mod.upcraftlp.playerluckyblocks.Reference;
import net.minecraftforge.common.config.*;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static mod.upcraftlp.playerluckyblocks.Reference.MODID;

public class LuckyConfig { //TODO use annotation-based system

    public static final List<UUID> players = Lists.newArrayList();
    public static final List<String> affectedSaves = Lists.newArrayList();
    
	public static Configuration config;
	private static final String CATEGORY_OTHER = "other";
	
	/**Config values**/
	public static boolean enableDeathSound;
	public static boolean pussyMode;
    public static boolean redstoneSlimes;

    public static void syncConfig() {
		
		/** Configuration Start **/
		enableDeathSound = config.getBoolean("enableDeathSound", Configuration.CATEGORY_GENERAL, true, "en/disable custom death sound");
		pussyMode = config.getBoolean("pussyMode", CATEGORY_OTHER, false, "are you serious?");
		redstoneSlimes = config.getBoolean("redstoneSlimes", CATEGORY_OTHER, true, "allow slimeblocks to turn back into slimes when supplied with a redstone signal");
		/** Configuration End **/
		
		config.setCategoryRequiresMcRestart(CATEGORY_OTHER, true);
		if(config.hasChanged()) config.save();
	}

	public static void init(FMLPreInitializationEvent event) {
		config = ModHelper.getConfigFile(event, MODID);
		config.load();
		syncConfig();
	}
	
	public static synchronized boolean isDebugMode() {
		return ModHelper.isDebugMode();
	}
	
	public static List<IConfigElement> getEntries() {
		List<IConfigElement> entries = new ArrayList<IConfigElement>();
		Set<String> categories = config.getCategoryNames();
		for (String categoryName : categories) {
			ConfigCategory category = config.getCategory(categoryName);
			entries.addAll(new ConfigElement(category).getChildElements());
		}
		return entries;
	}

	@EventBusSubscriber(modid = MODID)
	public static class ConfigWatcher {

		@SubscribeEvent
		public static void configChanged(OnConfigChangedEvent event) {
			if(event.getModID().equals(MODID)) {
				syncConfig();
				ConfigManager.sync(MODID, Config.Type.INSTANCE);
				if(config.hasChanged()) config.save();
			}
		}
	}
	

	
}
