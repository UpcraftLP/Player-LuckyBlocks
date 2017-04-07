package mod.upcraftlp.playerluckyblocks.items;

import java.util.List;

import com.google.common.collect.Multimap;

import core.upcraftlp.craftdev.API.templates.ItemSword;
import mod.upcraftlp.playerluckyblocks.init.LuckyMisc.DamageSources;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class ItemDagger extends ItemSword {

	public ItemDagger() {
		super("dagger", ToolMaterial.IRON);
		this.setMaxDamage(100);
	}
	
	@Override
	public float getDamageVsEntity() {
		return 2.5f;
	}
	
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
		Multimap<String, AttributeModifier> modifiers = super.getItemAttributeModifiers(equipmentSlot); //HashMultimap.<String, AttributeModifier>create();
		if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
			modifiers.removeAll(SharedMonsterAttributes.ATTACK_SPEED.getName());
            modifiers.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", 96, 0));
        }
		return modifiers;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		//Backstab bonus
		double targetYaw = Math.abs(entity.getRotationYawHead() % 360);
		double playerYaw = Math.abs(player.rotationYawHead % 360);
		boolean isBehind = Math.abs(targetYaw - playerYaw) < 60 || Math.abs(targetYaw - playerYaw) > 300;
		if(isBehind) {
			entity.hurtResistantTime = 0;
			entity.attackEntityFrom(DamageSources.backStab(player), this.getDamageVsEntity()*5);
			entity.playSound(SoundEvents.ENTITY_ARROW_HIT_PLAYER, 1.0F, 0.5F);
			stack.damageItem(5, player);
		}
		entity.hurtResistantTime = 0;
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);
		if(!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.AQUA + "Press <Shift> for more info!");
		}
		else
		{
			tooltip.add("Will cause additional damage when attacking from behind!");
			tooltip.add(TextFormatting.RED + "Strong, but costs extra durability");
		}
	}

}
