package mod.upcraftlp.playerluckyblocks.render.item;

import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import mod.upcraftlp.playerluckyblocks.items.ItemFruit;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;

public class MeshItemFruit implements ItemMeshDefinition {

    private static final ModelResourceLocation[] locations;
    
    static {
        locations = new ModelResourceLocation[16];
        for(int i = 0; i < 16; i++) {
            locations[i] = new ModelResourceLocation(LuckyItems.SpecialItems.DEVILS_FRUIT.getRegistryName(), "texture=" + i);
        }
    }
    
    @Override
    public ModelResourceLocation getModelLocation(ItemStack stack) {
        int model = 0;
        if(stack.hasTagCompound()) {
            model = stack.getTagCompound().getInteger(ItemFruit.KEY_FRUIT_MODEL);
        }
        return locations[model];
    }
    
    public static ModelResourceLocation[] getAll() {
        return locations;
    }
    
}
