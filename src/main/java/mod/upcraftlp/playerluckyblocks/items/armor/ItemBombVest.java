package mod.upcraftlp.playerluckyblocks.items.armor;

import core.upcraftlp.craftdev.API.templates.ItemArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * (c)2017 UpcraftLP
 */
public class ItemBombVest extends ItemArmor {

    public ItemBombVest() {
        super("bomb_vest", ArmorMaterial.LEATHER, EntityEquipmentSlot.CHEST);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        super.onArmorTick(world, player, itemStack);
    }

    //TODO
}
