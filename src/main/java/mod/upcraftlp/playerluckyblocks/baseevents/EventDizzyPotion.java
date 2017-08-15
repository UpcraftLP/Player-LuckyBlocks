package mod.upcraftlp.playerluckyblocks.baseevents;

import mod.upcraftlp.playerluckyblocks.api.IEventProvider;
import mod.upcraftlp.playerluckyblocks.init.LuckyPotions;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EventDizzyPotion implements IEventProvider {

	@Override
	public String getName() {
		return "dizzyPotion";
	}

	@Override
	public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(LuckyPotions.DIZZYNESS, 1800)); //1800 ticks = 1.5 minutes
	}

}
