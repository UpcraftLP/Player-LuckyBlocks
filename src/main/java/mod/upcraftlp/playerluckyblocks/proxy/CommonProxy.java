package mod.upcraftlp.playerluckyblocks.proxy;

import core.upcraftlp.craftdev.API.net.NetworkHandler;
import mod.upcraftlp.playerluckyblocks.Main;
import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.blocks.tile.TileEntityPlayerLuckyBlock;
import mod.upcraftlp.playerluckyblocks.config.LuckyConfig;
import mod.upcraftlp.playerluckyblocks.crafting.LuckCrafting;
import mod.upcraftlp.playerluckyblocks.crafting.ShapedCrafting;
import mod.upcraftlp.playerluckyblocks.init.LuckyEvents;
import mod.upcraftlp.playerluckyblocks.init.LuckyGuiHandler;
import mod.upcraftlp.playerluckyblocks.net.PacketDeathNote;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		LuckyConfig.init(event);
		LuckyEvents.initHandlers(event.getSide());
		LuckyEvents.initEvents();
		LuckyEvents.initDrops();
	}
	
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new LuckyGuiHandler());
		NetworkHandler.registerPacket(PacketDeathNote.class, PacketDeathNote.class, Side.SERVER);
		//TODO: Crafting!
		ShapedCrafting.init();
		LuckCrafting.init();
		GameRegistry.registerTileEntity(TileEntityPlayerLuckyBlock.class, Reference.MODID + "_luckyBlock");
	}

	public void postInit(FMLPostInitializationEvent event) {
		
	}

    public void serverAboutToStart(FMLServerAboutToStartEvent event) {
        
    }
    
    public void unloadedWorld(FMLServerStoppedEvent event) {
        
    }

}
