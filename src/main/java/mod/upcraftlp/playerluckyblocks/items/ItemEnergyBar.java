package mod.upcraftlp.playerluckyblocks.items;

import core.upcraftlp.craftdev.API.templates.ItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * (c)2017 UpcraftLP
 */
public class ItemEnergyBar extends ItemFood {

    public ItemEnergyBar() {
        super("energy_bar", 5, 3.5F, false);
        this.setPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 4), 0.6F);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        super.onFoodEaten(stack, worldIn, player);
        player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1800, 2)); //1:30 min speed 3
    }
}
