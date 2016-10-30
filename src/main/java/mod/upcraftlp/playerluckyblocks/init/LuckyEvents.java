package mod.upcraftlp.playerluckyblocks.init;

import java.util.Arrays;

import mod.upcraftlp.playerluckyblocks.API.EnumLuck;
import mod.upcraftlp.playerluckyblocks.API.EventRegistry;
import mod.upcraftlp.playerluckyblocks.baseEvents.EventEnderPigZombie;
import mod.upcraftlp.playerluckyblocks.baseEvents.EventLuckyBlockTower;
import mod.upcraftlp.playerluckyblocks.baseEvents.EventNotFound;
import mod.upcraftlp.playerluckyblocks.baseEvents.EventNuke;
import mod.upcraftlp.playerluckyblocks.baseEvents.EventSwapPositions;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class LuckyEvents {
	
	public static void initEvents() {
		/**NEUTRAL**/
		EventRegistry.registerEvent(new EventNotFound(), EnumLuck.NEUTRAL);
		EventRegistry.registerEvent(new EventLuckyBlockTower(), EnumLuck.NEUTRAL);
		
		/**POSTIVIE**/
		
		/**NEGATIVE**/
		EventRegistry.registerEvent(new EventSwapPositions(), EnumLuck.NEGATIVE);
		EventRegistry.registerEvent(new EventEnderPigZombie(), EnumLuck.NEGATIVE);
		
		/**BADASS**/
		EventRegistry.registerEvent(new EventNuke(), EnumLuck.BADASS);
		
	}
	
	public static void initDrops() {
		/**Vanilla Stuff**/
		EventRegistry.registerDrop(Arrays.asList(new ItemStack[] {new ItemStack(Blocks.DIAMOND_BLOCK, 2)}), "diamond_block", EnumLuck.POSITIVE);
		
		/**Misc**/
		EventRegistry.registerDrop(Arrays.asList(new ItemStack[] {new ItemStack(LuckyItems.TELEPORT_STAFF)}), "teleport_staff", EnumLuck.POSITIVE);
		EventRegistry.registerDrop(Arrays.asList(new ItemStack[] {new ItemStack(LuckyItems.WATER_ORB)}), "water_orb", EnumLuck.NEUTRAL);
		
		/**Ender Armor**/
		EventRegistry.registerDrop(Arrays.asList(new ItemStack[] {new ItemStack(LuckyItems.ENDER_HELMET)}), "ender_helmet", EnumLuck.NEUTRAL);
		EventRegistry.registerDrop(Arrays.asList(new ItemStack[] {new ItemStack(LuckyItems.ENDER_CHESTPLATE)}), "ender_chestplate", EnumLuck.POSITIVE);
		EventRegistry.registerDrop(Arrays.asList(new ItemStack[] {new ItemStack(LuckyItems.ENDER_LEGGINGS)}), "ender_leggings", EnumLuck.NEUTRAL);
		EventRegistry.registerDrop(Arrays.asList(new ItemStack[] {new ItemStack(LuckyItems.ENDER_BOOTS)}), "ender_boots", EnumLuck.POSITIVE);
		
	}
}
