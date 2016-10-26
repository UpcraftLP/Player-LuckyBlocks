package mod.upcraftlp.playerluckyblocks.API;

import javax.annotation.Nonnull;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IEventProvider {
	
	/**
	 * The name of the Event
	 * @return
	 */
	@Nonnull
	public String getName();

	/**
	 * Executes the Event.
	 * Player is never null
	 * 
	 * @param world
	 * @param pos
	 * @param state
	 * @param player
	 */
	public void execute(World world, BlockPos pos, IBlockState state, @Nonnull EntityPlayer player);
	
}
