package mod.upcraftlp.playerluckyblocks.api.plugins;

import core.upcraftlp.craftdev.api.structures.StructureLoaderSchematic;
import mod.upcraftlp.playerluckyblocks.Reference;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.PlacementSettings;

/**
 * (c)2017 UpcraftLP
 */
public class PluginUtils {

    public static Item getItemByText(String id) {
        ResourceLocation resourcelocation = new ResourceLocation(id);
        return Item.REGISTRY.getObject(resourcelocation);
    }

    public static void loadStructure(World world, BlockPos pos, String name) {
        PlacementSettings settings = new PlacementSettings();
        settings.setRotation(Rotation.values()[world.rand.nextInt(Rotation.values().length)]); //random rotation
        settings.setSeed(world.getSeed());
        settings.setIgnoreEntities(false);
        settings.setIgnoreStructureBlock(true);
        settings.setRandom(world.rand);
        try {
            StructureLoaderSchematic.loadFromExternalFile(world, pos, "lucky_block_compat/" + name, settings);
        }
        catch (Exception e) {
            ResourceLocation location = new ResourceLocation(Reference.MODID, "lucky/" + name);
            StructureLoaderSchematic.loadFromAssets(world, pos, location, settings);
        }
    }
}
