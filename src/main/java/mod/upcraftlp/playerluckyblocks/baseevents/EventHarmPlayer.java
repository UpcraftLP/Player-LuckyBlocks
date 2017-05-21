package mod.upcraftlp.playerluckyblocks.baseevents;

import mod.upcraftlp.playerluckyblocks.API.IEventProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * (c)2017 UpcraftLP
 */
public class EventHarmPlayer implements IEventProvider {

    @Nonnull
    @Override
    public String getName() {
        return "harm_player";
    }

    @Override
    public void execute(World world, BlockPos pos, IBlockState state, @Nonnull EntityPlayer player) {
        player.setHealth(0.5F);
        player.getFoodStats().setFoodLevel(0);
        player.getFoodStats().addExhaustion(20.0F);
    }
}
