package mod.upcraftlp.playerluckyblocks.init;

import core.upcraftlp.craftdev.API.util.ModHelper;
import mod.upcraftlp.playerluckyblocks.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.common.util.EnumHelper;

public class LuckyMisc {
	
	public static class ArmorMaterials {
		private static final String MODID = Reference.MODID;
		
		//TODO: currently just iron stats
		public static ArmorMaterial ENDER = ModHelper.registerArmor("ender", MODID, 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f);
		public static ArmorMaterial JETPACK = ModHelper.registerArmor("jetpack", MODID, 11, new int[]{3, 6, 7, 3}, 9, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.5f);
				
		/**WIP**/
		//TODO: INFINITY ARMOR
		//public static ArmorMaterial INFINITY = ModHelper.registerArmor("infinity", MODID, 30, new int[]{3, 4, 7, 3}, 3, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 5.0f);
	}
	
	@SuppressWarnings("FinalStaticMethod")
    public static class DamageSources {
		
		public static final DamageSource enderDenyWater = new DamageSource("enderDenyWater").setDamageBypassesArmor().setDamageIsAbsolute();
		
		public static final DamageSource backStab(Entity attacker) {
			return new EntityDamageSource("backStab", attacker).setDamageBypassesArmor().setDamageIsAbsolute();
		}
		
		public static final DamageSource deathNote(Entity attacker) {
            return new EntityDamageSource("deathNote", attacker).setDamageBypassesArmor().setDamageIsAbsolute().setDamageAllowedInCreativeMode().setMagicDamage();
        }
		
		/**WIP**/
		//TODO: DAMAGE SOURCES
		//public static final DamageSource inVoid = new DamageSource("inVoid").setDamageAllowedInCreativeMode().setDamageBypassesArmor().setDamageIsAbsolute();
		//public static final DamageSource electric = new DamageSource("electric").setDamageBypassesArmor().setDamageIsAbsolute();
	}
	
	public static class ToolMaterials {
		
		public static final ToolMaterial ADMIN = EnumHelper.addToolMaterial("admin", 1000, 1, 3.0f, 3000.0f, 100);
		
	}
	
	public static class Sounds {
	    
	    //TODO:SOUNDS
	    //public static final SoundEvent DEATH_SOUND_1 = ModHelper.registerSound();
	    
	}
}
