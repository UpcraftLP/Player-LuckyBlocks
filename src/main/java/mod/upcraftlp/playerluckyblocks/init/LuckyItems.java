package mod.upcraftlp.playerluckyblocks.init;

import mod.upcraftlp.playerluckyblocks.items.ItemAdminArk;
import mod.upcraftlp.playerluckyblocks.items.ItemBooster;
import mod.upcraftlp.playerluckyblocks.items.ItemDagger;
import mod.upcraftlp.playerluckyblocks.items.ItemDeathNote;
import mod.upcraftlp.playerluckyblocks.items.ItemLightningBolt;
import mod.upcraftlp.playerluckyblocks.items.ItemTeleportStaff;
import mod.upcraftlp.playerluckyblocks.items.ItemWaterOrb;
import mod.upcraftlp.playerluckyblocks.items.armor.ItemEnderArmor;
import mod.upcraftlp.playerluckyblocks.items.armor.ItemJetPack;
import mod.upcraftlp.playerluckyblocks.items.fruits.ItemFruitDeath;
import mod.upcraftlp.playerluckyblocks.items.fruits.ItemFruitFlight;
import mod.upcraftlp.playerluckyblocks.items.fruits.ItemFruitGiant;
import mod.upcraftlp.playerluckyblocks.items.fruits.ItemFruitMagic;
import mod.upcraftlp.playerluckyblocks.items.fruits.ItemFruitWater;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

public class LuckyItems {

    //TODO: add missing items to drops
    
	/**Misc**/
	public static final Item WATER_ORB = new ItemWaterOrb();
	public static final Item TELEPORT_STAFF = new ItemTeleportStaff();
	public static final Item DEATH_NOTE = new ItemDeathNote();
	public static final Item LIGHTNING_BOLT = new ItemLightningBolt();
	public static final Item BOOSTER = new ItemBooster(); //missing
	
	/**Fruits**/
	public static final Item FRUIT_FLIGHT = new ItemFruitFlight(); //missing, unfinished
	public static final Item FRUIT_MAGIC = new ItemFruitMagic(); //missing, unfinished
	public static final Item FRUIT_DEATH = new ItemFruitDeath(); //missing
	public static final Item FRUIT_GIANT = new ItemFruitGiant(); //missing, unfinished
	public static final Item FRUIT_WATER = new ItemFruitWater(); //missing
	
	/**JetPack**/
	public static final Item JETPACK = new ItemJetPack();
	
	/**Ender Armor**/
	public static final Item ENDER_HELMET = new ItemEnderArmor(EntityEquipmentSlot.HEAD);
	public static final Item ENDER_CHESTPLATE = new ItemEnderArmor(EntityEquipmentSlot.CHEST);
	public static final Item ENDER_LEGGINGS = new ItemEnderArmor(EntityEquipmentSlot.LEGS);
	public static final Item ENDER_BOOTS = new ItemEnderArmor(EntityEquipmentSlot.FEET);
	
	/**Swords**/
	public static final Item ADMIN_ARK = new ItemAdminArk(); //missing, unfinished
	public static final Item SHADOW_DAGGER = new ItemDagger();
	
}
