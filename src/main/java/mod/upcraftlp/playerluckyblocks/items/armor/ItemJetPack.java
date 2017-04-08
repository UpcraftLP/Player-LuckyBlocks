package mod.upcraftlp.playerluckyblocks.items.armor;

import java.util.UUID;

import core.upcraftlp.craftdev.API.templates.ItemArmor;
import mod.upcraftlp.playerluckyblocks.event.FlightHandler;
import mod.upcraftlp.playerluckyblocks.init.LuckyMisc.ArmorMaterials;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemJetPack extends ItemArmor {

	public ItemJetPack() {
		super("jetpack", ArmorMaterials.JETPACK, EntityEquipmentSlot.CHEST);
		this.setMaxDamage(400);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
	    if(world.isRemote) return;
	    UUID uuid = player.getUniqueID();
	    if(!FlightHandler.flightPlayers.contains(uuid)) {
	        FlightHandler.flightPlayers.add(uuid);
	    }
	    if(player.capabilities.isFlying && world.getTotalWorldTime() % 20 == 0) itemStack.damageItem(1, player); 
	}
}
