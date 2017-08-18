package mod.upcraftlp.playerluckyblocks;

import mod.upcraftlp.playerluckyblocks.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(name = Reference.MODNAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MCVERSIONS, modid = Reference.MODID, canBeDeactivated = false, dependencies = Reference.DEPENDENCIES, updateJSON = Reference.UPDATE_JSON, guiFactory = Reference.GUI_FACTORY)
public class Main {

    @Instance
    public static Main instance;

    private static Logger log = LogManager.getFormatterLogger(Reference.MODID);

    public static Logger getLogger() {
        return log;
    }

    @SidedProxy(clientSide = Reference.CLIENTSIDE_PATH, serverSide = Reference.SERVERSIDE_PATH)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        log.info("Pre-Initialization finished.");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        log.info("Initialization finished.");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
        log.info("Post-Initialization finished.");
    }
    
    @EventHandler
    public void onWorldLoad(FMLServerAboutToStartEvent event) {
        proxy.serverAboutToStart(event);
    }
    
    @EventHandler
    public void onWorldEnd(FMLServerStoppedEvent event) {
        proxy.unloadedWorld(event);
    }
    
    @EventHandler
    public void onImcMessage(IMCEvent event) {
        //handle IMC messages here
    }
}
