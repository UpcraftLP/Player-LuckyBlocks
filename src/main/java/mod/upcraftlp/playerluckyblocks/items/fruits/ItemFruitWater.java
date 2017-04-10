package mod.upcraftlp.playerluckyblocks.items.fruits;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class ItemFruitWater extends AbstractItemFruit {

    public ItemFruitWater() {
        super("water");
        this.setPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 6000, 0), 1.0f);
    }

}
