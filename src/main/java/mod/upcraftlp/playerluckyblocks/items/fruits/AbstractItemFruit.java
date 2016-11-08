package mod.upcraftlp.playerluckyblocks.items.fruits;

import core.upcraftlp.craftdev.API.templates.ItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class AbstractItemFruit extends ItemFood {

	protected final String KEY;
	
	public AbstractItemFruit(String name) {
		super("fruit_" + name, 3, 3.0f, false);
		this.KEY = name;
	}
	
	@Override
	public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		player.addTag(KEY);
	}

}
