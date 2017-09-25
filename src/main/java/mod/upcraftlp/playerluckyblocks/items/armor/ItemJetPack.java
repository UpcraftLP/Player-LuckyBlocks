package mod.upcraftlp.playerluckyblocks.items.armor;

import java.util.UUID;

import core.upcraftlp.craftdev.api.item.ItemArmor;
import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.event.FlightHandler;
import mod.upcraftlp.playerluckyblocks.init.LuckyMisc.ArmorMaterials;
import mod.upcraftlp.playerluckyblocks.render.entity.armor.ModelJetPack;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemJetPack extends ItemArmor { //TODO keybinding to activate

	public ItemJetPack() {
		super("jetpack", ArmorMaterials.JETPACK, EntityEquipmentSlot.CHEST);
		this.setMaxDamage(400);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
	    if(world.isRemote) return;
	    if(!itemStack.hasTagCompound()) {
	    	NBTTagCompound nbt = new NBTTagCompound();
	    	nbt.setBoolean("active", true);
	    	itemStack.setTagCompound(nbt);
		}
		if(!itemStack.getTagCompound().getBoolean("active")) return;
	    UUID uuid = player.getUniqueID();
	    if(!FlightHandler.flightPlayers.contains(uuid)) {
	        FlightHandler.flightPlayers.add(uuid);
	    }
	    if(player.capabilities.isFlying && world.getTotalWorldTime() % 20 == 0) itemStack.damageItem(1, player); 
	}

	@Nullable
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		return new ModelJetPack();
	}

	@Nullable
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		String suffix = "off";
		if(stack.hasTagCompound() && stack.getTagCompound().getBoolean("active")) suffix = "on";
		return Reference.ID_PREFIX + "textures/entity/armor/jetpack_" + suffix + ".png";
	}
}
