package mod.upcraftlp.playerluckyblocks.proxy;

import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.blocks.tile.TileEntityPlayerLuckyBlock;
import mod.upcraftlp.playerluckyblocks.config.LuckyConfig;
import mod.upcraftlp.playerluckyblocks.crafting.LuckCrafting;
import mod.upcraftlp.playerluckyblocks.crafting.ShapedCrafting;
import mod.upcraftlp.playerluckyblocks.init.LuckyEvents;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		LuckyConfig.init(event);
		LuckyEvents.initHandlers(event.getSide());
		LuckyEvents.initEvents();
		LuckyEvents.initDrops();
	}
	
	public void init(FMLInitializationEvent event) {
		//TODO: Crafting!
		ShapedCrafting.init();
		LuckCrafting.init();
		GameRegistry.registerTileEntity(TileEntityPlayerLuckyBlock.class, Reference.MODID + "_luckyBlock");
	}

	public void postInit(FMLPostInitializationEvent event) {
		
	}

}
