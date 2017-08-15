package mod.upcraftlp.playerluckyblocks.baseevents;

import mod.upcraftlp.playerluckyblocks.api.StructureProvider;
import mod.upcraftlp.playerluckyblocks.api.IEventProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.PlacementSettings;

public class EventStructure implements IEventProvider {

    @Override
    public String getName() {
        return "structure";
    }

    @Override
    public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        PlacementSettings settings = new PlacementSettings();
        settings.setRotation(Rotation.values()[world.rand.nextInt(Rotation.values().length)]);
        StructureProvider.generate(world, pos, settings);
    }

}
