package mod.upcraftlp.playerluckyblocks.proxy;

import core.upcraftlp.craftdev.API.common.ModRegistry;
import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.blocks.tile.TileEntityPlayerLuckyBlock;
import mod.upcraftlp.playerluckyblocks.init.LuckyBlocks;
import mod.upcraftlp.playerluckyblocks.init.LuckyConfig;
import mod.upcraftlp.playerluckyblocks.init.LuckyEvents;
import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import mod.upcraftlp.playerluckyblocks.init.LuckyMisc;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		LuckyConfig.init(event);
		LuckyBlocks.init();
		LuckyItems.init();
		
		//FIXME:May change, test custom itemBlocks!
		ModRegistry.registerBlocks(LuckyBlocks.blockMap);
		ModRegistry.registerItems(LuckyItems.itemMap);
		
		LuckyEvents.initEvents();
		LuckyEvents.initDrops();
		LuckyMisc.init();
	}
	
	public void init(FMLInitializationEvent event) {
		//TODO: Crafting!
		//ShapedCrafting.init();
		GameRegistry.registerTileEntity(TileEntityPlayerLuckyBlock.class, Reference.MODID + "_luckyBlock");
	}

	public void postInit(FMLPostInitializationEvent event) {
		
	}

}
