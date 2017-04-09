package mod.upcraftlp.playerluckyblocks.event;

import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = Reference.MODID, value = {Side.CLIENT})
public class RenderHandler {

    @SubscribeEvent
    public static void onRenderDagger(RenderLivingEvent.Pre<EntityLivingBase> event) {
        EntityLivingBase entity = event.getEntity();
        if(entity.isSneaking() && (entity.getHeldItemMainhand().getItem() == LuckyItems.DAGGER || entity.getHeldItemOffhand().getItem() == LuckyItems.DAGGER)) {
            event.setCanceled(true);
            GlStateManager.pushMatrix();
            GlStateManager.pushAttrib();
            GlStateManager.disableLighting();
          //TODO: OpenGL shader overlay
            
            GlStateManager.enableLighting();
            GlStateManager.popAttrib();
            GlStateManager.popMatrix();
            
        }
    }
}
