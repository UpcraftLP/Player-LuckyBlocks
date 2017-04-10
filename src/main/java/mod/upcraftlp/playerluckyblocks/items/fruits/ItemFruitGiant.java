package mod.upcraftlp.playerluckyblocks.items.fruits;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFruitGiant extends AbstractItemFruit {

    public ItemFruitGiant() {
        super("giant");
    }

    @Override
    public void fruitEffect(ItemStack stack, World worldIn, EntityPlayer player) {
        // TODO enlarge player and hitbox, also reach distance!
        
    }

}
