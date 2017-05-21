package mod.upcraftlp.playerluckyblocks.items;

import java.util.List;

import core.upcraftlp.craftdev.API.templates.ItemSword;
import mod.upcraftlp.playerluckyblocks.init.LuckyMisc.ToolMaterials;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.EntityEquipmentSlot.Type;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemAdminArk extends ItemSword {

	public ItemAdminArk() {
		super("admin_ark", ToolMaterials.ADMIN);
		this.setNoRepair();
		this.setMaxDamage(0); //0 = unbreakable
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
	    if(!worldIn.isRemote && entityIn instanceof EntityPlayer) {
	        EntityPlayer player = (EntityPlayer) entityIn;
	        player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 10, 2));
	        player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 10, 20));
	        player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 10, 5));
	        player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 10, 3));
	        player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 10, 2));
	    }
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
	    if(entity instanceof EntityLivingBase) {
	        EntityLivingBase entityLiving = (EntityLivingBase) entity;
	        for(EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
	            if(slot.getSlotType() == Type.ARMOR) {
	                ItemStack armor = entityLiving.getItemStackFromSlot(slot);
	                if(!armor.isEmpty()) {
	                    if(armor.isItemStackDamageable()) armor.damageItem((armor.getMaxDamage() - armor.getItemDamage()) / 2 + 5, entityLiving);
						else armor = ItemStack.EMPTY;
	                    entity.setItemStackToSlot(slot, armor);
	                }
	            }
	        }
	    }
	    return false;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
	    tooltip.add(TextFormatting.GOLD + I18n.format("tooltip.lucky.adminArk"));
	}
	
}
