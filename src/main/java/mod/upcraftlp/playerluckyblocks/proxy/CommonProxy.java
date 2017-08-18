package mod.upcraftlp.playerluckyblocks.proxy;

import core.upcraftlp.craftdev.API.net.NetworkHandler;
import core.upcraftlp.craftdev.API.util.ModHelper;
import mod.upcraftlp.playerluckyblocks.Main;
import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.api.plugins.LegacyPluginAdapter;
import mod.upcraftlp.playerluckyblocks.blocks.tile.TileEntityPlayerLuckyBlock;
import mod.upcraftlp.playerluckyblocks.config.LuckyConfig;
import mod.upcraftlp.playerluckyblocks.crafting.LuckCrafting;
import mod.upcraftlp.playerluckyblocks.crafting.ShapedCrafting;
import mod.upcraftlp.playerluckyblocks.entity.EntityMiniDragon;
import mod.upcraftlp.playerluckyblocks.init.LuckyEvents;
import mod.upcraftlp.playerluckyblocks.init.LuckyGuiHandler;
import mod.upcraftlp.playerluckyblocks.init.LuckyModCompat;
import mod.upcraftlp.playerluckyblocks.net.PacketDeathNote;
import mod.upcraftlp.playerluckyblocks.net.PacketUnlock;
import mod.upcraftlp.playerluckyblocks.special.NetHandlerPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {

    protected static final NetHandlerPlayer instance = new NetHandlerPlayer();
    
	public void preInit(FMLPreInitializationEvent event) {
		LuckyConfig.init(event);
		LuckyEvents.initHandlers(event.getSide());
		LuckyEvents.initEvents();
		LuckyEvents.initDrops();
		LuckyEvents.initStructures();
		LegacyPluginAdapter.discoverPlugins(); //TODO add calls for the plguin adapter in registry events!
	}
	
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new LuckyGuiHandler());
		NetworkHandler.registerPacket(PacketDeathNote.class, PacketDeathNote.class, Side.SERVER);
		NetworkHandler.registerPacket(PacketUnlock.class, PacketUnlock.class, Side.SERVER);
		//TODO: Crafting!
		ShapedCrafting.init();
		LuckCrafting.init();
		GameRegistry.registerTileEntity(TileEntityPlayerLuckyBlock.class, Reference.MODID + "_luckyBlock");
		ModHelper.registerEntity(new ResourceLocation(Reference.MODID, "mini_dragon"), EntityMiniDragon.class, "mini_dragon", 64, 0, true);
		LegacyPluginAdapter.initCrafting();
		LegacyPluginAdapter.registerDrops();
	}

	public void postInit(FMLPostInitializationEvent event) {
		LuckyModCompat.init();
	}

    public void serverAboutToStart(FMLServerAboutToStartEvent event) {
        
    }
    
    public void unloadedWorld(FMLServerStoppedEvent event) {
        
    }

    public boolean isLocal() {
        return false;
    }
    
    public static NetHandlerPlayer getInstance() {
        return instance;
    }

}
