package mod.upcraftlp.playerluckyblocks.init;

import static mod.upcraftlp.playerluckyblocks.API.EnumLuck.BADASS;
import static mod.upcraftlp.playerluckyblocks.API.EnumLuck.NEGATIVE;
import static mod.upcraftlp.playerluckyblocks.API.EnumLuck.NEUTRAL;
import static mod.upcraftlp.playerluckyblocks.API.EnumLuck.POSITIVE;

import com.google.common.collect.Lists;

import mod.upcraftlp.playerluckyblocks.API.EventRegistry;
import mod.upcraftlp.playerluckyblocks.baseevents.EventDizzyPotion;
import mod.upcraftlp.playerluckyblocks.baseevents.EventEnderPigZombie;
import mod.upcraftlp.playerluckyblocks.baseevents.EventKill;
import mod.upcraftlp.playerluckyblocks.baseevents.EventLuckyBlockTower;
import mod.upcraftlp.playerluckyblocks.baseevents.EventMiniDragon;
import mod.upcraftlp.playerluckyblocks.baseevents.EventNotFound;
import mod.upcraftlp.playerluckyblocks.baseevents.EventNuke;
import mod.upcraftlp.playerluckyblocks.baseevents.EventSwapPositions;
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
		EventRegistry.registerEvent(new EventNotFound(), NEUTRAL);
		EventRegistry.registerEvent(new EventLuckyBlockTower(), NEUTRAL);
		
		/**POSTIVIE**/
		EventRegistry.registerEvent(new EventMiniDragon(), POSITIVE);
		
		/**NEGATIVE**/
		EventRegistry.registerEvent(new EventSwapPositions(), NEGATIVE);
		EventRegistry.registerEvent(new EventEnderPigZombie(), NEGATIVE);
		EventRegistry.registerEvent(new EventDizzyPotion(), NEGATIVE);
		
		/**BADASS**/
		EventRegistry.registerEvent(new EventNuke(), BADASS);
        EventRegistry.registerEvent(new EventKill(), BADASS);
	}
	
	public static void initDrops() {
		
		
		/**Player Lucky Blocks**/
		//Misc
		EventRegistry.registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.TELEPORT_STAFF)), "teleport_staff", POSITIVE);
		EventRegistry.registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.WATER_ORB)), "water_orb", NEUTRAL);
		
		//Ender Armor
		EventRegistry.registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.ENDER_HELMET)), "ender_helmet", NEUTRAL);
		EventRegistry.registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.ENDER_CHESTPLATE)), "ender_chestplate", POSITIVE);
		EventRegistry.registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.ENDER_LEGGINGS)), "ender_leggings", NEUTRAL);
		EventRegistry.registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.ENDER_BOOTS)), "ender_boots", POSITIVE);
		
		//Misc
		EventRegistry.registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.JETPACK)), "jetpack", POSITIVE);
		EventRegistry.registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.DEATH_NOTE)), "deathNote", POSITIVE);
		EventRegistry.registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.LIGHTNING_BOLT, 8)), "zeus", POSITIVE);
		EventRegistry.registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.SHADOW_DAGGER, 2)), "assassin", POSITIVE);
		EventRegistry.registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.BOOSTER)), "assassin", NEUTRAL);
		
		/**Vanilla Stuff**/
		//rare drops (ores, ingots, etc.)
		EventRegistry.registerDrop(Lists.newArrayList(new ItemStack(Blocks.DIAMOND_BLOCK, 2)), "diamond_block", POSITIVE);
		
		//Redstone Engineer
		EventRegistry.registerDrop(Lists.newArrayList(
                new ItemStack(Items.REDSTONE, 64),
                new ItemStack(Items.REDSTONE, 64),
                new ItemStack(Blocks.PISTON, 16),
                new ItemStack(Blocks.STICKY_PISTON, 32),
                new ItemStack(Blocks.SLIME_BLOCK, 3),
                new ItemStack(Blocks.DAYLIGHT_DETECTOR, 8),
                new ItemStack(Blocks.REDSTONE_BLOCK, 4),
                new ItemStack(Blocks.REDSTONE_LAMP, 16),
                new ItemStack(Blocks.REDSTONE_TORCH, 20),
                new ItemStack(Blocks.LEVER, 8),
                new ItemStack(Blocks.WOODEN_BUTTON, 3),
                new ItemStack(Blocks.STONE_BUTTON, 3),
                new ItemStack(Blocks.DROPPER, 10),
                new ItemStack(Blocks.DISPENSER, 4),
                new ItemStack(Blocks.TNT, 16)
		        ), "rs_engineer", NEUTRAL);
		
		//Demolisher
		EventRegistry.registerDrop(Lists.newArrayList(
		        new ItemStack(Blocks.REDSTONE_TORCH, 16),
		        new ItemStack(Blocks.LEVER, 16),
		        new ItemStack(Items.REDSTONE, 32),
		        new ItemStack(Items.GUNPOWDER, 20),
		        new ItemStack(Blocks.SAND, 16),
		        new ItemStack(Blocks.TNT, 48)
		        ), "demolisher", POSITIVE);
		
		//Woodcutter
		EventRegistry.registerDrop(Lists.newArrayList(
		        new ItemStack(Items.IRON_AXE),
		        new ItemStack(Items.IRON_AXE),
		        new ItemStack(Items.IRON_AXE),
		        new ItemStack(Blocks.LOG, 16, 0),
		        new ItemStack(Blocks.LOG, 16, 1),
		        new ItemStack(Blocks.LOG, 16, 2),
		        new ItemStack(Blocks.LOG, 16, 3),
		        new ItemStack(Blocks.LOG2, 16, 0),
		        new ItemStack(Blocks.LOG2, 16, 1),
		        new ItemStack(Blocks.SAPLING, 32, 0),
		        new ItemStack(Blocks.SAPLING, 32, 1),
		        new ItemStack(Blocks.SAPLING, 32, 2),
		        new ItemStack(Blocks.SAPLING, 32, 3),
		        new ItemStack(Blocks.SAPLING, 32, 4),
		        new ItemStack(Blocks.SAPLING, 32, 5)
		        ), "woodcutter", NEGATIVE);
	}
}
