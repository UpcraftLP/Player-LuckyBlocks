package mod.upcraftlp.playerluckyblocks.items.fruits;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFruitFlight extends AbstractItemFruit {

	public ItemFruitFlight() {
		super("flight");
	}
	
	@Override
	public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		// TODO creative flight!
	}

}
