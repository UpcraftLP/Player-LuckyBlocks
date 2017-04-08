package mod.upcraftlp.playerluckyblocks.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mod.upcraftlp.playerluckyblocks.API.EnumLuck;
import mod.upcraftlp.playerluckyblocks.API.EventRegistry;
import mod.upcraftlp.playerluckyblocks.baseevents.EventDizzyPotion;
import mod.upcraftlp.playerluckyblocks.baseevents.EventEnderPigZombie;
import mod.upcraftlp.playerluckyblocks.baseevents.EventKill;
import mod.upcraftlp.playerluckyblocks.baseevents.EventLuckyBlockTower;
import mod.upcraftlp.playerluckyblocks.baseevents.EventNotFound;
import mod.upcraftlp.playerluckyblocks.baseevents.EventNuke;
import mod.upcraftlp.playerluckyblocks.baseevents.EventSwapPositions;
import mod.upcraftlp.playerluckyblocks.config.LuckyConfig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;

public class LuckyEvents {
	
    /**
     * helper method for instantiating event handlers that extend {@link core.upcraftlp.craftdev.API.util.EventHandler}
     * @param side
     */
    public static void initHandlers(Side side) {

    }
    
	public static void initEvents() {
		/**NEUTRAL**/
		EventRegistry.registerEvent(new EventNotFound(), EnumLuck.NEUTRAL);
		EventRegistry.registerEvent(new EventLuckyBlockTower(), EnumLuck.NEUTRAL);
		
		/**POSTIVIE**/
		
		/**NEGATIVE**/
		EventRegistry.registerEvent(new EventSwapPositions(), EnumLuck.NEGATIVE);
		EventRegistry.registerEvent(new EventEnderPigZombie(), EnumLuck.NEGATIVE);
		EventRegistry.registerEvent(new EventDizzyPotion(), EnumLuck.NEGATIVE);
		
		/**BADASS**/
		if(LuckyConfig.pussyMode) {
			EventRegistry.registerEvent(new EventNotFound(), EnumLuck.BADASS);
		}
		else
		{
			EventRegistry.registerEvent(new EventNuke(), EnumLuck.BADASS);
			EventRegistry.registerEvent(new EventKill(), EnumLuck.BADASS);
		}
		
		
	}
	
	public static void initDrops() {
		
		
		/**Player Lucky Blocks**/
		//Misc
		EventRegistry.registerDrop(Arrays.asList(new ItemStack[] {new ItemStack(LuckyItems.TELEPORT_STAFF)}), "teleport_staff", EnumLuck.POSITIVE);
		EventRegistry.registerDrop(Arrays.asList(new ItemStack[] {new ItemStack(LuckyItems.WATER_ORB)}), "water_orb", EnumLuck.NEUTRAL);
		
		//Ender Armor
		EventRegistry.registerDrop(Arrays.asList(new ItemStack[] {new ItemStack(LuckyItems.ENDER_HELMET)}), "ender_helmet", EnumLuck.NEUTRAL);
		EventRegistry.registerDrop(Arrays.asList(new ItemStack[] {new ItemStack(LuckyItems.ENDER_CHESTPLATE)}), "ender_chestplate", EnumLuck.POSITIVE);
		EventRegistry.registerDrop(Arrays.asList(new ItemStack[] {new ItemStack(LuckyItems.ENDER_LEGGINGS)}), "ender_leggings", EnumLuck.NEUTRAL);
		EventRegistry.registerDrop(Arrays.asList(new ItemStack[] {new ItemStack(LuckyItems.ENDER_BOOTS)}), "ender_boots", EnumLuck.POSITIVE);
		
		
		/**Vanilla Stuff**/
		//rare drops (ores, ingots, etc.)
		EventRegistry.registerDrop(Arrays.asList(new ItemStack[] {new ItemStack(Blocks.DIAMOND_BLOCK, 2)}), "diamond_block", EnumLuck.POSITIVE);
		
		//Redstone Engineer
		List<ItemStack> rs_engineer = new ArrayList<ItemStack>();
		rs_engineer.add(new ItemStack(Items.REDSTONE, 64));
		rs_engineer.add(new ItemStack(Items.REDSTONE, 64));
		rs_engineer.add(new ItemStack(Blocks.PISTON, 16));
		rs_engineer.add(new ItemStack(Blocks.STICKY_PISTON, 32));
		rs_engineer.add(new ItemStack(Blocks.SLIME_BLOCK, 3));
		rs_engineer.add(new ItemStack(Blocks.DAYLIGHT_DETECTOR, 8));
		rs_engineer.add(new ItemStack(Blocks.REDSTONE_BLOCK, 4));
		rs_engineer.add(new ItemStack(Blocks.REDSTONE_LAMP, 16));
		rs_engineer.add(new ItemStack(Blocks.REDSTONE_TORCH, 20));
		rs_engineer.add(new ItemStack(Blocks.LEVER, 8));
		rs_engineer.add(new ItemStack(Blocks.WOODEN_BUTTON, 3));
		rs_engineer.add(new ItemStack(Blocks.STONE_BUTTON, 3));
		rs_engineer.add(new ItemStack(Blocks.DROPPER, 10));
		rs_engineer.add(new ItemStack(Blocks.DISPENSER, 4));
		rs_engineer.add(new ItemStack(Blocks.TNT, 16));
		EventRegistry.registerDrop(rs_engineer, "rs_engineer", EnumLuck.NEUTRAL);
		
		//Demolisher
		List<ItemStack> demolisher = new ArrayList<ItemStack>();
		demolisher.add(new ItemStack(Blocks.REDSTONE_TORCH, 16));
		demolisher.add(new ItemStack(Blocks.LEVER, 16));
		demolisher.add(new ItemStack(Items.REDSTONE, 32));
		demolisher.add(new ItemStack(Items.GUNPOWDER, 20));
		demolisher.add(new ItemStack(Blocks.SAND, 16));
		demolisher.add(new ItemStack(Blocks.TNT, 48));
		EventRegistry.registerDrop(demolisher, "demolisher", EnumLuck.POSITIVE);
		
		//Woodcutter
		List<ItemStack> woodcutter = new ArrayList<ItemStack>();
		woodcutter.add(new ItemStack(Items.IRON_AXE));
		woodcutter.add(new ItemStack(Items.IRON_AXE));
		woodcutter.add(new ItemStack(Items.IRON_AXE));
		woodcutter.add(new ItemStack(Blocks.LOG, 16, 0));
		woodcutter.add(new ItemStack(Blocks.LOG, 16, 1));
		woodcutter.add(new ItemStack(Blocks.LOG, 16, 2));
		woodcutter.add(new ItemStack(Blocks.LOG, 16, 3));
		woodcutter.add(new ItemStack(Blocks.LOG2, 16, 0));
		woodcutter.add(new ItemStack(Blocks.LOG2, 16, 1));
		woodcutter.add(new ItemStack(Blocks.SAPLING, 32, 0));
		woodcutter.add(new ItemStack(Blocks.SAPLING, 32, 1));
		woodcutter.add(new ItemStack(Blocks.SAPLING, 32, 2));
		woodcutter.add(new ItemStack(Blocks.SAPLING, 32, 3));
		woodcutter.add(new ItemStack(Blocks.SAPLING, 32, 4));
		woodcutter.add(new ItemStack(Blocks.SAPLING, 32, 5));
		EventRegistry.registerDrop(woodcutter, "woodcutter", EnumLuck.NEGATIVE);
	}
}
