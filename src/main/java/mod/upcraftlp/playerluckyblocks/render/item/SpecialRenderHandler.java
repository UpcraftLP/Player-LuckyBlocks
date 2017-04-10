package mod.upcraftlp.playerluckyblocks.render.item;

import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
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
    public void onRegisterSpecial(ModelRegistryEvent event) {
       Item fruit = LuckyItems.DEVILS_FRUIT;
       for(int i = 0; i < 16; i++) { //Register all item variants manually
           ModelBakery.registerItemVariants(fruit, new ResourceLocation(fruit.getRegistryName() + "_" + i));
       }
       ModelLoader.setCustomMeshDefinition(fruit, new MeshItemFruit()); //register the stack-based model
       Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ColorItemFruit(), fruit); //register the color handler
    }
    
}
