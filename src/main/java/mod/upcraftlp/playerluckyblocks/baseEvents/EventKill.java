package mod.upcraftlp.playerluckyblocks.baseEvents;

import mod.upcraftlp.playerluckyblocks.API.IEventProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class EventKill implements IEventProvider {

	@Override
	public String getName() {
		return "kill";
	}

	@Override
	public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		player.onKillCommand();
		if(!world.getWorldInfo().isHardcoreModeEnabled()) player.sendMessage(new TextComponentString("If this was a hardcore world it would have just been deleted."));
	}

}
