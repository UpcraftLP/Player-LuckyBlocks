package mod.upcraftlp.playerluckyblocks.items;

import core.upcraftlp.craftdev.API.templates.Item;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWaterOrb extends Item {

	public static int MAX_AIR  = 300;
	
	public ItemWaterOrb() {
		super("water_orb");
		this.setMaxStackSize(1);
		this.setMaxDamage(512);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(worldIn.isRemote) return;
		if(entityIn instanceof EntityLivingBase) {
			EntityLivingBase entity = (EntityLivingBase) entityIn;
			if(entity.isInWater() && entity.getAir()  < 5) {
				//TODO: respiration(enchantment)-sensitive!
				entity.setAir(MAX_AIR);
				stack.attemptDamageItem(1, itemRand);
				if(stack.func_190916_E() <= 0) stack = null; //TODO: ItemStack#getStackSize() ??
				entity.replaceItemInInventory(itemSlot, stack);
			}
		}
		
	}

}
