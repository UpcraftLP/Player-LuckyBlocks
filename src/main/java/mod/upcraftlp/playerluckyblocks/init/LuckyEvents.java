package mod.upcraftlp.playerluckyblocks.init;

import static mod.upcraftlp.playerluckyblocks.api.EnumLuck.BADASS;
import static mod.upcraftlp.playerluckyblocks.api.EnumLuck.NEGATIVE;
import static mod.upcraftlp.playerluckyblocks.api.EnumLuck.NEUTRAL;
import static mod.upcraftlp.playerluckyblocks.api.EnumLuck.POSITIVE;

import com.google.common.collect.Lists;

import static mod.upcraftlp.playerluckyblocks.api.EventRegistry.*;
import mod.upcraftlp.playerluckyblocks.api.StructureProvider;
import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.baseevents.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
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
		registerEvent(new EventNotFound(), NEUTRAL);
		registerEvent(new EventLuckyBlockTower(), NEUTRAL);
		registerEvent(new EventStructure(), NEUTRAL);
		
		/**POSTIVIE**/
		registerEvent(new EventFruitChest(), POSITIVE);
		
		/**NEGATIVE**/
		registerEvent(new EventSwapPositions(), NEGATIVE);
		registerEvent(new EventEnderPigZombie(), NEGATIVE);
		registerEvent(new EventDizzyPotion(), NEGATIVE);
		registerEvent(new EventHarmPlayer(), NEGATIVE);
		
		/**BADASS**/
		registerEvent(new EventNuke(), BADASS);
        registerEvent(new EventKill(), BADASS);
	}
	
	public static void initDrops() {

		/**Player Lucky Blocks**/
		//Armor
		registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.ENDER_HELMET)), "ender_helmet", NEUTRAL);
		registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.ENDER_CHESTPLATE)), "ender_chestplate", POSITIVE);
		registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.ENDER_LEGGINGS)), "ender_leggings", NEUTRAL);
		registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.ENDER_BOOTS)), "ender_boots", POSITIVE);

        registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.JETPACK)), "jetpack", POSITIVE);

        //Misc
		registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.DEATH_NOTE)), "deathNote", POSITIVE);
		registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.LIGHTNING_BOLT, 8)), "zeus", POSITIVE);
		registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.BOOSTER)), "booster", NEUTRAL);
        registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.TELEPORT_STAFF)), "teleport_staff", POSITIVE);
        registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.WATER_ORB)), "water_orb", NEUTRAL);

		registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.ENERGY_BAR, 4)), "energyBar", POSITIVE);

        //Swords
        registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.SHADOW_DAGGER, 2)), "assassin", POSITIVE);
        registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.BACON_SWORD, 1)), "baconSword", NEGATIVE);
        registerDrop(Lists.newArrayList(new ItemStack(LuckyItems.ADMIN_ARK, 1)), "adminArk", POSITIVE);

		/**Vanilla Stuff**/
		//rare drops (ores, ingots, etc.)
		registerDrop(Lists.newArrayList(new ItemStack(Blocks.DIAMOND_BLOCK, 5)), "diamond_blocks", POSITIVE);

		registerDrop(Lists.newArrayList(
		        new ItemStack(Items.APPLE, 5),
                new ItemStack(Items.GOLDEN_APPLE, 7),
                new ItemStack(Items.GOLDEN_APPLE, 4, 1),
                new ItemStack(Items.GOLDEN_APPLE, 3),
                new ItemStack(Items.APPLE, 9),
                new ItemStack(Items.GOLDEN_APPLE, 3, 1)
        ), "apples", POSITIVE);

        registerDrop(Lists.newArrayList(

                new ItemStack(Items.CHORUS_FRUIT, 7),
                new ItemStack(Items.CHORUS_FRUIT_POPPED, 25),
                new ItemStack(Items.CHORUS_FRUIT, 9)
                ), "chorus", NEUTRAL);

		//Redstone Engineer
		registerDrop(Lists.newArrayList(
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
		registerDrop(Lists.newArrayList(
		        new ItemStack(Blocks.REDSTONE_TORCH, 16),
		        new ItemStack(Blocks.LEVER, 16),
		        new ItemStack(Items.REDSTONE, 32),
		        new ItemStack(Items.GUNPOWDER, 20),
		        new ItemStack(Blocks.SAND, 16),
		        new ItemStack(Blocks.TNT, 48)
		        ), "demolisher", POSITIVE);
		
		//Woodcutter
		registerDrop(Lists.newArrayList(
		        new ItemStack(Items.IRON_AXE, 1, 56),
		        new ItemStack(Items.IRON_AXE, 1, 102),
		        new ItemStack(Items.IRON_AXE, 1, 30),
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

	private static ResourceLocation getLocation(String name) {
        return new ResourceLocation(Reference.MODID, name);
    }

	public static void initStructures() {
    	//NBT
        StructureProvider.registerNBT(getLocation("lucky_pyramid"));
        StructureProvider.registerNBT(getLocation("tnt_pyramid"));

		//Schematic
        //nothing here atm, schematic structures suck :P
	}
}
