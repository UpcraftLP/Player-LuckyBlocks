package mod.upcraftlp.playerluckyblocks.items.fruits;

import core.upcraftlp.craftdev.API.templates.ItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class AbstractItemFruit extends ItemFood {

	protected final String KEY;
	
	public AbstractItemFruit(String name) {
		super("fruit_" + name, 3, 3.0f, false);
		this.KEY = "fruit_" + name;
	}
	
	@Override
	public final void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);
	    player.addTag(KEY);
		this.fruitEffect(stack, worldIn, player);
	}

	/**
	 * method called in a fruit's {@link net.minecraft.item.ItemFood#onFoodEaten()} method to allow for customization
	 */
    public void fruitEffect(ItemStack stack, World worldIn, EntityPlayer player) {}

}
