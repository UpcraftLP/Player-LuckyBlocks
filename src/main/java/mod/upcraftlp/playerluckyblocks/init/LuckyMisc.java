package mod.upcraftlp.playerluckyblocks.init;

import core.upcraftlp.craftdev.API.common.ModHelper;
import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.events.EnderArmorHandler;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;

public class LuckyMisc {

	public static void init() {
		MinecraftForge.EVENT_BUS.register(new EnderArmorHandler());
	}
	
	public static class ArmorMaterials {
		private static final String MODID = Reference.MODID;
		
		//TODO: currently just iron stats
		public static ArmorMaterial ENDER = ModHelper.registerArmor("ender", MODID, 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f);
		
		/**WIP**/
		//public static ArmorMaterial INFINITY = ModHelper.registerArmor("infinity", MODID, 30, new int[]{3, 4, 7, 3}, 3, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 5.0f);
	}
	
	public static class DamageSources {
		
		public static final DamageSource enderDenyWater = new DamageSource("enderDenyWater").setDamageBypassesArmor().setDamageIsAbsolute();
		
		/**WIP**/
		//public static final DamageSource inVoid = new DamageSource("inVoid").setDamageAllowedInCreativeMode().setDamageBypassesArmor().setDamageIsAbsolute();
		//public static final DamageSource electric = new DamageSource("electric").setDamageBypassesArmor().setDamageIsAbsolute();
	}
}
