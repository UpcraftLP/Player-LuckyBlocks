package mod.upcraftlp.playerluckyblocks.init;

import mod.upcraftlp.playerluckyblocks.items.ItemAdminArk;
import mod.upcraftlp.playerluckyblocks.items.ItemDagger;
import mod.upcraftlp.playerluckyblocks.items.ItemTeleportStaff;
import mod.upcraftlp.playerluckyblocks.items.ItemWaterOrb;
import mod.upcraftlp.playerluckyblocks.items.armor.ItemEnderArmor;
import mod.upcraftlp.playerluckyblocks.items.armor.ItemJetPack;
import mod.upcraftlp.playerluckyblocks.items.fruits.ItemFruitFlight;
import mod.upcraftlp.playerluckyblocks.items.fruits.ItemFruitMagic;
import net.minecraft.item.Item;

public class LuckyItems {

	/**Misc**/
	public static final Item WATER_ORB = new ItemWaterOrb();
	public static final Item TELEPORT_STAFF = new ItemTeleportStaff();
	
	/**Fruits**/
	public static final Item FRUIT_FLIGHT = new ItemFruitFlight();
	public static final Item FRUIT_GIANT = new ItemFruitMagic();
	
	/**JetPack**/
	//public static final Item JETPACK = new ItemJetPack();
	
	/**Ender Armor**/ //! order is important due to some glitchy implementations ;)
	public static final Item ENDER_BOOTS = null;//new ItemEnderArmor();
	public static final Item ENDER_LEGGINGS = null;//new ItemEnderArmor();
	public static final Item ENDER_CHESTPLATE = null;//new ItemEnderArmor();
	public static final Item ENDER_HELMET = null;//new ItemEnderArmor();
	
	static {
	    
	}
	
	/**Swords**/
	public static final Item ADMIN_ARK = new ItemAdminArk();
	public static final Item DAGGER = new ItemDagger();
	
}
