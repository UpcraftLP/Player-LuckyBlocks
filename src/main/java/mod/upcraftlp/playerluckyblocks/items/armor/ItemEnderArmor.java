package mod.upcraftlp.playerluckyblocks.items.armor;

import core.upcraftlp.craftdev.API.templates.ItemArmor;
import mod.upcraftlp.playerluckyblocks.event.EnderArmorHandler;
import mod.upcraftlp.playerluckyblocks.init.LuckyMisc.ArmorMaterials;
import mod.upcraftlp.playerluckyblocks.init.LuckyMisc.DamageSources;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEnderArmor extends ItemArmor {

	public ItemEnderArmor(EntityEquipmentSlot equipmentSlot) {
		super("ender", ArmorMaterials.ENDER, equipmentSlot);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		if(player.isInWater()) {
			player.attackEntityFrom(DamageSources.enderDenyWater, 8.0f);
			if(!player.isDead) EnderArmorHandler.randomTeleport(player);
			return;
		}
		if(player.isWet()) {
			player.attackEntityFrom(DamageSources.enderDenyWater, 2.0f);
		}
	}
}
