package mod.upcraftlp.playerluckyblocks.API;

import javax.annotation.Nonnull;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IEventProvider {
	
	/**
	 * @return the name of the event
	 */
	@Nonnull
	public String getName();

	/**
	 * executes the event.
	 * 
	 * @param world		the world
	 * @param pos		the block's position
	 * @param state		the current blockstate
	 * @param player	the player who opened the block
	 */
	public void execute(World world, BlockPos pos, IBlockState state, @Nonnull EntityPlayer player);
	
}
