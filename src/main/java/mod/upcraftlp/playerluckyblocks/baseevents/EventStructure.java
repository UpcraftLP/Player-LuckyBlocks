package mod.upcraftlp.playerluckyblocks.baseevents;

import static mod.upcraftlp.playerluckyblocks.Reference.MODID;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import core.upcraftlp.craftdev.API.structures.StructureLoaderNBT;
import mod.upcraftlp.playerluckyblocks.API.StructureProvider;
import mod.upcraftlp.playerluckyblocks.Main;
import mod.upcraftlp.playerluckyblocks.API.IEventProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
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
