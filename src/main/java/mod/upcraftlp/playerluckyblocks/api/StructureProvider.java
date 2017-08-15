package mod.upcraftlp.playerluckyblocks.API;

import com.google.common.collect.Lists;
import core.upcraftlp.craftdev.API.structures.StructureLoaderNBT;
import core.upcraftlp.craftdev.API.structures.StructureLoaderSchematic;
import jline.internal.Preconditions;
import mod.upcraftlp.playerluckyblocks.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.PlacementSettings;

import java.util.List;
import java.util.Random;

/**
 * (c)2017 UpcraftLP
 */
public class StructureProvider {

    private static final Random rand = new Random();

    private static final List<ResourceLocation> nbt = Lists.newArrayList();
    private static final List<ResourceLocation> schematic = Lists.newArrayList();

    public static void registerNBT(ResourceLocation structure) {
        Preconditions.checkNotNull(structure);
        boolean contains = false;
        for (ResourceLocation loc : nbt) {
            if(!loc.equals(structure)) continue;
            contains = true;
            break;
        }
        if(contains) Main.getLogger().error("Cannot register NBT structure \"" + structure + "\", structure already present.");
        else nbt.add(structure);
    }

    public static void registerSchematic(ResourceLocation structure) {
        Preconditions.checkNotNull(structure);
        boolean contains = false;
        for (ResourceLocation loc : schematic) {
            if(!loc.equals(structure)) continue;
            contains = true;
            break;
        }
        if(contains) Main.getLogger().error("Cannot register Schematic structure \"" + structure + "\", structure already present.");
        else schematic.add(structure);
    }

    public static void generate(World world, BlockPos pos, PlacementSettings settings) {
        List<ResourceLocation> all = Lists.newArrayList();
        all.addAll(nbt);
        all.addAll(schematic);
        if(all.isEmpty()) {
            Main.getLogger().info("no valid structure registered!");
            return;
        }
        ResourceLocation location = all.get(rand.nextInt(all.size()));
        if(nbt.contains(location)) {
            StructureLoaderNBT.loadFromAssets(world, pos, location, settings);
        }
        else {
            StructureLoaderSchematic.loadFromAssets(world, pos, location, settings);
        }
    }

    public static void remove(ResourceLocation name) {
        for(ResourceLocation loc : nbt) {
            if(loc.equals(name)) {
                nbt.remove(loc);
            }
        }
        for(ResourceLocation loc : schematic) {
            if(loc.equals(name)) schematic.remove(loc);
        }
        Main.getLogger().info("unregistered structure: \"" + name + "\"");
    }

    public static void remove(String name) {
        remove(new ResourceLocation(name));
    }
}
