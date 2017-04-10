package mod.upcraftlp.playerluckyblocks.items.fruits;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

public class ItemFruitDeath extends AbstractItemFruit {

    public ItemFruitDeath() {
        super("death");
        this.setPotionEffect(new PotionEffect(MobEffects.WITHER, 6000, 9), 0.95f);
    }
    
    @Override
    public void fruitEffect(ItemStack stack, World worldIn, EntityPlayer player) {
        FoodStats stats = player.getFoodStats();
        stats.setFoodLevel(0);
        stats.setFoodSaturationLevel(100.0f);
        player.attackEntityFrom(DamageSource.STARVE, Float.MAX_VALUE);
    }

}
