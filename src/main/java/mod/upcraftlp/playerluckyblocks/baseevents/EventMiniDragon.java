package mod.upcraftlp.playerluckyblocks.baseevents;

import mod.upcraftlp.playerluckyblocks.API.IEventProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class EventMiniDragon implements IEventProvider {

    @Override
    public String getName() {
        return "EventMiniDragon";
    }

    @Override
    public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        //TODO: check if there's a dragon for the player, spawn a new one if not
        player.sendStatusMessage(new TextComponentTranslation("info.lucky.NodragonFollow"), true);
    }

}
