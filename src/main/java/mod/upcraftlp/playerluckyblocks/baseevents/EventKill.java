package mod.upcraftlp.playerluckyblocks.baseevents;

import mod.upcraftlp.playerluckyblocks.API.IEventProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class EventKill implements IEventProvider {

	@Override
	public String getName() {
		return "kill";
	}

	@Override
	public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		player.onKillCommand();
		player.setDead();
		if(!world.getWorldInfo().isHardcoreModeEnabled()) player.sendMessage(new TextComponentTranslation("info.lucky.deleteWorld"));
	}

}
