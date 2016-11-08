package mod.upcraftlp.playerluckyblocks.items.armor;

import core.upcraftlp.craftdev.API.templates.ItemArmor;
import mod.upcraftlp.playerluckyblocks.init.LuckyMisc.ArmorMaterials;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemJetPack extends ItemArmor {

	public ItemJetPack() {
		super("jetpack", ArmorMaterials.JETPACK, EntityEquipmentSlot.CHEST);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		// TODO packet-handling (flight)!
		super.onArmorTick(world, player, itemStack);
	}

}
