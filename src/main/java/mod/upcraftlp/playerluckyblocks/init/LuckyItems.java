package mod.upcraftlp.playerluckyblocks.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mod.upcraftlp.playerluckyblocks.items.ItemTeleportStaff;
import mod.upcraftlp.playerluckyblocks.items.ItemWaterOrb;
import mod.upcraftlp.playerluckyblocks.items.armor.ItemEnderArmor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

public class LuckyItems {

	public static Map<Item, CreativeTabs> itemMap;
	
	public static Item WATER_ORB = new ItemWaterOrb();
	public static Item TELEPORT_STAFF = new ItemTeleportStaff();
	
	/**Ender Armor**/
	public static Item ENDER_HELMET = new ItemEnderArmor(EntityEquipmentSlot.HEAD);
	public static Item ENDER_CHESTPLATE = new ItemEnderArmor(EntityEquipmentSlot.CHEST);
	public static Item ENDER_LEGGINGS = new ItemEnderArmor(EntityEquipmentSlot.LEGS);
	public static Item ENDER_BOOTS = new ItemEnderArmor(EntityEquipmentSlot.FEET);
	
	public static void init() {
		itemMap = new HashMap<Item, CreativeTabs>();
		List<Item> items = new ArrayList<Item>();
		
		items.add(WATER_ORB);
		items.add(TELEPORT_STAFF);
		
		/**Ender Armor**/
		items.add(ENDER_HELMET);
		items.add(ENDER_CHESTPLATE);
		items.add(ENDER_LEGGINGS);
		items.add(ENDER_BOOTS);
		
		for (Item item : items) {
			itemMap.put(item, LuckyTabs.tabPlayerLucky);
		}
	}
}
