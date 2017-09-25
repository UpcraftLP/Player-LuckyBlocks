package mod.upcraftlp.playerluckyblocks.items;

import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;

import core.upcraftlp.craftdev.api.item.ItemSword;
import mod.upcraftlp.playerluckyblocks.init.LuckyMisc.DamageSources;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.animation.ITimeValue;

public class ItemDagger extends ItemSword {

	public ItemDagger() {
		super("shadow_dagger", ToolMaterial.IRON);
		this.setMaxDamage(100);
	}
	
	@Override
	public float getDamageVsEntity() {
		return 2.5f;
	}
	
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
		Multimap<String, AttributeModifier> modifiers = super.getItemAttributeModifiers(equipmentSlot);
		if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
			modifiers.removeAll(SharedMonsterAttributes.ATTACK_SPEED.getName());
            modifiers.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", 96, 0));
        }
		return modifiers;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
	    if(player.world.isRemote) return false;
		//Backstab bonus
		double targetYaw = Math.abs(entity.getRotationYawHead() % 360);
		double playerYaw = Math.abs(player.rotationYawHead % 360);
		boolean isBehind = Math.abs(targetYaw - playerYaw) < 60 || Math.abs(targetYaw - playerYaw) > 300;
		if(isBehind) {
		    Random rand = player.getRNG();
			entity.attackEntityFrom(DamageSources.backStab(player), this.getDamageVsEntity() * rand.nextInt(6));
			entity.playSound(SoundEvents.ENTITY_ARROW_HIT_PLAYER, 0.6f + rand.nextFloat() * 0.4f, rand.nextFloat());
			stack.damageItem(5, player);
		}
		entity.hurtResistantTime = 0;
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);
		if(!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.AQUA + I18n.format("tooltip.lucky.additionalInfo.name", "Shift"));
		}
		else
		{
			tooltip.add(I18n.format("tooltip.lucky.dagger.1"));
			tooltip.add(TextFormatting.RED + I18n.format("tooltip.lucky.dagger.2"));
		}
	}
	
	@Override
    public ImmutableMap<String, ITimeValue> getAnimationParameters(ItemStack stack, World world,
            EntityLivingBase entity) {
        // TODO stabbing animation
	    //or maybe not...
        return super.getAnimationParameters(stack, world, entity);
    }
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
	    if(isSelected && !worldIn.isRemote && entityIn.isSneaking() && entityIn instanceof EntityPlayer && worldIn.getTotalWorldTime() % 30 == 0) {
	        EntityPlayer player = (EntityPlayer) entityIn;
	        stack.damageItem(1, player);
	        player.inventory.setInventorySlotContents(itemSlot, stack);
	    }
	}

}
