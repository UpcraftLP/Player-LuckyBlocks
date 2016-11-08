package mod.upcraftlp.playerluckyblocks.items.fruits;

import mod.upcraftlp.playerluckyblocks.init.LuckyPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFruitGiant extends AbstractItemFruit {

	public static final String KEY_GIANT = "giant";
	
	public ItemFruitGiant() {
		super("giant");
	}

	@Override
	public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		player.height *= 3.0f;
		player.eyeHeight *= 3.0f;
		player.addPotionEffect(new PotionEffect(LuckyPotions.GIANT, 20*60*5));
	}

}
