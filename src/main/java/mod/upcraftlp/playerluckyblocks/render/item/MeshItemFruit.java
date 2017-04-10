package mod.upcraftlp.playerluckyblocks.render.item;

import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import mod.upcraftlp.playerluckyblocks.items.ItemFruit;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;

public class MeshItemFruit implements ItemMeshDefinition {

    @Override
    public ModelResourceLocation getModelLocation(ItemStack stack) {
        int model = 0;
        if(stack.hasTagCompound()) {
            stack.getTagCompound().getInteger(ItemFruit.KEY_FRUIT_MODEL);
        }
        return new ModelResourceLocation(LuckyItems.DEVILS_FRUIT.getRegistryName() + "_" + model, "inventory");
    }

}
