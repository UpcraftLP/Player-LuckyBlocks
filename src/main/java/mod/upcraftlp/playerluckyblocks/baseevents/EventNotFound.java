package mod.upcraftlp.playerluckyblocks.baseevents;

import com.mojang.realmsclient.gui.ChatFormatting;

import mod.upcraftlp.playerluckyblocks.API.IEventProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class EventNotFound implements IEventProvider {

	@Override
	public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		player.sendStatusMessage(new TextComponentString(ChatFormatting.RED + "Error 404: Event not found."), true);
	}

	@Override
	public String getName() {
		return "EventNotFound";
	}

}
