package mod.upcraftlp.playerluckyblocks.baseevents;

import java.util.Random;

import mod.upcraftlp.playerluckyblocks.API.IEventProvider;
import mod.upcraftlp.playerluckyblocks.world.DragonData;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class EventMiniDragon implements IEventProvider {

    private static final Random rand = new Random();
    
    @Override
    public String getName() {
        return "EventMiniDragon";
    }

    @Override
    public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        if(!DragonData.get(player).spawnDragon(player, rand.nextInt())) {
            player.sendStatusMessage(new TextComponentTranslation("info.lucky.NodragonFollow"), true);
        }
    }

}
