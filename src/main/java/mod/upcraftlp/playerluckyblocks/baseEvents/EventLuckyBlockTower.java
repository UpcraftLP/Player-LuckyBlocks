package mod.upcraftlp.playerluckyblocks.baseEvents;

import mod.upcraftlp.playerluckyblocks.API.IEventProvider;
import mod.upcraftlp.playerluckyblocks.init.LuckyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EventLuckyBlockTower implements IEventProvider {

	@Override
	public String getName() {
		return "EventLuckyBlockTower";
	}

	@Override
	public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		for(int i = 0; i < 5; i++) {
			EntityFallingBlock block = new EntityFallingBlock(world, pos.getX(), pos.getY() + 10 + 2*i, pos.getZ(), LuckyBlocks.PLAYER_LUCKYBLOCK.getDefaultState());
			block.fallTime = 10;
			world.spawnEntityInWorld(block);
		}
	}

}
