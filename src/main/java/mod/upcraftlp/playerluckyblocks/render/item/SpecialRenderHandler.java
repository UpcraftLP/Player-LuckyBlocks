package mod.upcraftlp.playerluckyblocks.render.item;

import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = Reference.MODID, value = {Side.CLIENT})
public class SpecialRenderHandler {

    @SubscribeEvent
    public static void onRegisterSpecial(ModelRegistryEvent event) {
       Item fruit = LuckyItems.SpecialItems.DEVILS_FRUIT;
       ModelBakery.registerItemVariants(fruit, MeshItemFruit.getAll()); //Register all item variants manually
       ModelLoader.setCustomMeshDefinition(fruit, new MeshItemFruit()); //register the stack-based model
    }
    
}
