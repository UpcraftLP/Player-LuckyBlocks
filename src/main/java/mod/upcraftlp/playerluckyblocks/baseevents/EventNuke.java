package mod.upcraftlp.playerluckyblocks.baseevents;

import java.util.Random;

import mod.upcraftlp.playerluckyblocks.api.IEventProvider;
import mod.upcraftlp.playerluckyblocks.util.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class EventNuke implements IEventProvider {

	@Override
	public String getName() {
		return "EventNuke";
	}

	@Override
	public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		Random random = Utils.RANDOM;
		player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE + "May death rain upon them!"), false);
		for(int i = random.nextInt(20); i > 0; i--) {
			EntityTNTPrimed tnt = new EntityTNTPrimed(world, pos.getX() + 0.5D, pos.getY() + 10 + random.nextInt(3), pos.getZ() + 0.5D, null);
			tnt.addVelocity(-0.5D, 0, -0.5D);
			tnt.addVelocity(random.nextDouble(), 0, random.nextDouble());
			tnt.setFuse(40);
			tnt.setGlowing(true); //fancy
			tnt.forceSpawn = true;
			world.spawnEntity(tnt);
		}
	}

}
