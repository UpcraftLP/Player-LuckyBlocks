package mod.upcraftlp.playerluckyblocks.items.fruits;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFruitMagic extends AbstractItemFruit {

	public ItemFruitMagic() {
		super("magic");
	}

    @Override
    public void fruitEffect(ItemStack stack, World worldIn, EntityPlayer player) {
        // TODO magic effect?!
        //sparkling effect all the time
    }

}
