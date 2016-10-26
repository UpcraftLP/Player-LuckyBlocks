package mod.upcraftlp.playerluckyblocks;

import core.upcraftlp.craftdev.API.common.ModHelper;
import core.upcraftlp.craftdev.API.common.ModLogger;
import core.upcraftlp.craftdev.API.common.ModRegistry;
import mod.upcraftlp.playerluckyblocks.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name = Reference.MODNAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MCVERSION, modid = Reference.MODID, canBeDeactivated = false, dependencies = Reference.DEPENDENCIES)
public class Main {

	@Instance
	public static Main instance;
	
	private static ModLogger log = new ModLogger(Reference.MODID);
	
	public static ModLogger getLogger() {
		return log;
	}
	
	@SidedProxy(clientSide = Reference.CLIENTSIDE_PATH, serverSide = Reference.SERVERSIDE_PATH)
	public static CommonProxy proxy;
		
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModHelper.validateCodeBase(Reference.CODEBASE, Reference.MINIMUM_BUILD, Reference.MODID);
		ModRegistry.registerMod(Reference.MODID, Reference.VERSION, Reference.INTERNAL_UPDATE_URL, Reference.UPDATE_URL);
		proxy.preInit(event);
		log.println("Pre-Initialization finished.");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		log.println("Initialization finished.");
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		log.println("Post-Initialization finished.");
	}
	
	
}
