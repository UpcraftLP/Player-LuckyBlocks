package mod.upcraftlp.playerluckyblocks.proxy;

import core.upcraftlp.craftdev.API.common.ModRegistry;
import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.blocks.tile.TileEntityPlayerLuckyBlock;
import mod.upcraftlp.playerluckyblocks.crafting.LuckCrafting;
import mod.upcraftlp.playerluckyblocks.crafting.ShapedCrafting;
import mod.upcraftlp.playerluckyblocks.init.LuckyBlocks;
import mod.upcraftlp.playerluckyblocks.init.LuckyConfig;
import mod.upcraftlp.playerluckyblocks.init.LuckyEvents;
import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import mod.upcraftlp.playerluckyblocks.init.LuckyPotions;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		LuckyConfig.init(event);
		LuckyBlocks.init();
		LuckyItems.init();
		LuckyPotions.init();
		
		//FIXME:May change, test custom itemBlocks!
		ModRegistry.registerBlocks(LuckyBlocks.blockMap);
		ModRegistry.registerItems(LuckyItems.itemMap);
		
		LuckyEvents.initEvents();
		LuckyEvents.initDrops();
	}
	
	public void init(FMLInitializationEvent event) {
		LuckyItems.itemMap = null;
		LuckyBlocks.blockMap = null;
		System.gc();
		//TODO: Crafting!
		ShapedCrafting.init();
		LuckCrafting.init();
		GameRegistry.registerTileEntity(TileEntityPlayerLuckyBlock.class, Reference.MODID + "_luckyBlock");
	}

	public void postInit(FMLPostInitializationEvent event) {
		
	}

}
