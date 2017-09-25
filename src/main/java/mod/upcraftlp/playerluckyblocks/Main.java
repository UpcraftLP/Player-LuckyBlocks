package mod.upcraftlp.playerluckyblocks;

import core.upcraftlp.craftdev.api.util.UpdateChecker;
import mod.upcraftlp.playerluckyblocks.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static mod.upcraftlp.playerluckyblocks.Reference.*;

@Mod(name = MODNAME, version = VERSION, acceptedMinecraftVersions = MCVERSIONS, modid = MODID, dependencies = DEPENDENCIES, updateJSON = UPDATE_JSON, guiFactory = GUI_FACTORY)
public class Main {

    @Instance
    public static Main instance;

    private static Logger log = LogManager.getFormatterLogger(MODID);

    public static Logger getLogger() {
        return log;
    }

    @SidedProxy(clientSide = CLIENTSIDE_PATH, serverSide = SERVERSIDE_PATH)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        UpdateChecker.registerMod(MODID);
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
