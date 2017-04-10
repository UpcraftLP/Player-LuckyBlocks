package mod.upcraftlp.playerluckyblocks.baseevents;

import java.util.Random;

import mod.upcraftlp.playerluckyblocks.API.IEventProvider;
import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EventFruitChest implements IEventProvider {

    private static final Random RANDOM = new Random();
    
    @Override
    public String getName() {
        return "fruit_chest";
    }

    @Override
    public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        world.setBlockState(pos, Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, player.getHorizontalFacing()));
        TileEntityChest chestTile = (TileEntityChest) world.getTileEntity(pos);
        chestTile.setCustomName("Treasure Chest");
        NonNullList<ItemStack> fruits = NonNullList.create();
        LuckyItems.DEVILS_FRUIT.getSubItems(LuckyItems.DEVILS_FRUIT, null, fruits);
        for(int i = 0; i < RANDOM.nextInt(5); i++) {
            ItemStack stack = fruits.get(RANDOM.nextInt(fruits.size()));
            stack.setCount(1 + RANDOM.nextInt(4));
            chestTile.setInventorySlotContents(RANDOM.nextInt(chestTile.getSizeInventory()), stack);
        }
        
    }

}
