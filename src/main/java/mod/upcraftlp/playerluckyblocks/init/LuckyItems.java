package mod.upcraftlp.playerluckyblocks.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mod.upcraftlp.playerluckyblocks.items.ItemAdminArk;
import mod.upcraftlp.playerluckyblocks.items.ItemTeleportStaff;
import mod.upcraftlp.playerluckyblocks.items.ItemWaterOrb;
import mod.upcraftlp.playerluckyblocks.items.armor.ItemEnderArmor;
import mod.upcraftlp.playerluckyblocks.items.armor.ItemJetPack;
import mod.upcraftlp.playerluckyblocks.items.fruits.ItemFruitFlight;
import mod.upcraftlp.playerluckyblocks.items.fruits.ItemFruitMagic;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

public class LuckyItems {

	public static Map<Item, CreativeTabs> itemMap = new HashMap<Item, CreativeTabs>();;
	
	/**Misc**/
	public static final Item WATER_ORB = new ItemWaterOrb();
	public static final Item TELEPORT_STAFF = new ItemTeleportStaff();
	
	/**Fruits**/
	public static final Item FRUIT_FLIGHT = new ItemFruitFlight();
	public static final Item FRUIT_GIANT = new ItemFruitMagic();
	
	/**JetPack**/
	public static final Item JETPACK = new ItemJetPack();
	
	/**Ender Armor**/
	public static final Item ENDER_HELMET = new ItemEnderArmor(EntityEquipmentSlot.HEAD);
	public static final Item ENDER_CHESTPLATE = new ItemEnderArmor(EntityEquipmentSlot.CHEST);
	public static final Item ENDER_LEGGINGS = new ItemEnderArmor(EntityEquipmentSlot.LEGS);
	public static final Item ENDER_BOOTS = new ItemEnderArmor(EntityEquipmentSlot.FEET);
	
	/**Swords**/
	public static final Item ADMIN_ARK = new ItemAdminArk();
	
	public static void init() {
		List<Item> items = new ArrayList<Item>();
		
		/**Misc**/
		items.add(WATER_ORB);
		items.add(TELEPORT_STAFF);
		
		/**Fruits**/
		items.add(FRUIT_FLIGHT);
		items.add(FRUIT_GIANT);
		
		/**JetPack**/
		items.add(JETPACK);
		
		/**Ender Armor**/
		items.add(ENDER_HELMET);
		items.add(ENDER_CHESTPLATE);
		items.add(ENDER_LEGGINGS);
		items.add(ENDER_BOOTS);
		
		/**Swords**/
		items.add(ADMIN_ARK);
		
		for (Item item : items) {
			itemMap.put(item, LuckyTabs.tabPlayerLucky);
		}
	}
}
