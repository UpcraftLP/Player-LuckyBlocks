package mod.upcraftlp.playerluckyblocks.init;

import mod.upcraftlp.playerluckyblocks.items.*;
import mod.upcraftlp.playerluckyblocks.items.armor.ItemBombVest;
import mod.upcraftlp.playerluckyblocks.items.armor.ItemEnderArmor;
import mod.upcraftlp.playerluckyblocks.items.armor.ItemJetPack;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

public class LuckyItems {

    //TODO: add missing items to drops
    
	/**Misc**/
	public static final Item WATER_ORB = new ItemWaterOrb();
	public static final Item TELEPORT_STAFF = new ItemTeleportStaff();
	public static final Item DEATH_NOTE = new ItemDeathNote();
	public static final Item LIGHTNING_BOLT = new ItemThorsHammer();
	public static final Item BOOSTER = new ItemBooster();
	public static final Item ENERGY_BAR = new ItemEnergyBar();
	public static final Item LUCKY_KEY = new ItemLuckyKey();
	
	/**JetPack**/
	public static final Item JETPACK = new ItemJetPack();
	
	/**Ender Armor**/
	public static final Item ENDER_HELMET = new ItemEnderArmor(EntityEquipmentSlot.HEAD);
	public static final Item ENDER_CHESTPLATE = new ItemEnderArmor(EntityEquipmentSlot.CHEST);
	public static final Item ENDER_LEGGINGS = new ItemEnderArmor(EntityEquipmentSlot.LEGS);
	public static final Item ENDER_BOOTS = new ItemEnderArmor(EntityEquipmentSlot.FEET);

	public static final Item BOMB_VEST = new ItemBombVest(); //unimplemented, unfinished
	
	/**Swords**/
	public static final Item ADMIN_ARK = new ItemAdminArk(); //unfinished
	public static final Item SHADOW_DAGGER = new ItemDagger();
	public static final Item BACON_SWORD = new ItemBaconSword();
	
	/**
	 * class needed to trick resource loader to not register these items and register them manually instead
	 */
	public static class SpecialItems {
	    
	    public static final Item DEVILS_FRUIT = new ItemFruit();
	
	}
}
