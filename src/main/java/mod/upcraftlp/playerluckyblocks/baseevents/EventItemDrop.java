package mod.upcraftlp.playerluckyblocks.baseevents;

import java.util.List;

import mod.upcraftlp.playerluckyblocks.API.IEventProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EventItemDrop implements IEventProvider {

	private List<ItemStack> drops;
	private String name;
	
	public EventItemDrop(List<ItemStack> toDrop, String dropsName) {
		this.drops = toDrop;
		this.name = dropsName;
	}
	
	@Override
	public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		for(ItemStack stack : this.drops) {
			//use stack.copy() here or drop itemStacks will be invalid if this is called more than once per running Minecraft session!
			EntityItem item = new EntityItem(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, stack.copy());
			item.setDefaultPickupDelay();
			world.spawnEntity(item);
		}
	}
	
	@Override
	public String getName() {
		return "EventDropItem_" + this.name;
	}
}
