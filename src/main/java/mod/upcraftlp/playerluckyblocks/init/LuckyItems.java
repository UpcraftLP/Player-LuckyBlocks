package mod.upcraftlp.playerluckyblocks.init;

import mod.upcraftlp.playerluckyblocks.items.ItemAdminArk;
import mod.upcraftlp.playerluckyblocks.items.ItemBooster;
import mod.upcraftlp.playerluckyblocks.items.ItemDagger;
import mod.upcraftlp.playerluckyblocks.items.ItemDeathNote;
import mod.upcraftlp.playerluckyblocks.items.ItemFruit;
import mod.upcraftlp.playerluckyblocks.items.ItemLightningBolt;
import mod.upcraftlp.playerluckyblocks.items.ItemTeleportStaff;
import mod.upcraftlp.playerluckyblocks.items.ItemWaterOrb;
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
	public static final Item LIGHTNING_BOLT = new ItemLightningBolt();
	public static final Item BOOSTER = new ItemBooster();
	public static final Item DEVILS_FRUIT = new ItemFruit();
	
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
