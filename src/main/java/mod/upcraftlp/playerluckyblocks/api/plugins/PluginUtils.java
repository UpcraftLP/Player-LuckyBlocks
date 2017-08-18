package mod.upcraftlp.playerluckyblocks.api.plugins;

import com.google.common.collect.Lists;
import core.upcraftlp.craftdev.API.structures.StructureLoaderSchematic;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.PlacementSettings;

import java.util.List;

/**
 * (c)2017 UpcraftLP
 */
public class PluginUtils {

    public static Item getItemByText(String id) {
        ResourceLocation resourcelocation = new ResourceLocation(id);
        Item item = Item.REGISTRY.getObject(resourcelocation);
        return item;
    }

    public static class Registries {
        public static final List<Item> ITEMS = Lists.newArrayList();
        public static final List<Block> BLOCKS = Lists.newArrayList();
        //public static final Map<String, List<Drop>> DROPS = Maps.newConcurrentMap();
    }

    public static void loadStructure(World world, BlockPos pos, String name) {
        PlacementSettings settings = new PlacementSettings();
        settings.setRotation(Rotation.values()[world.rand.nextInt(Rotation.values().length)]); //random rotation
        settings.setSeed(world.getSeed());
        settings.setIgnoreEntities(false);
        settings.setIgnoreStructureBlock(true);
        settings.setRandom(world.rand);
        StructureLoaderSchematic.loadFromExternalFile(world, pos, "lucky_block_compat/" + name + ".schematic", settings);
    }

    //public static void registerDrop(List<ItemStack> drops, String blockname, float chance, int luck); //FIXME drop registry!

}
