package mod.upcraftlp.playerluckyblocks.event;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = Reference.MODID, value = {Side.CLIENT})
public class RenderHandler {

    private static final FloatBuffer colorBuffer = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.LITTLE_ENDIAN).asFloatBuffer();
    
    @SubscribeEvent
    public static void onRenderDagger(RenderLivingEvent.Pre<EntityLivingBase> event) {
        EntityLivingBase entity = event.getEntity();
        boolean holdsDagger = entity.getHeldItemMainhand().getItem() == LuckyItems.SHADOW_DAGGER || entity.getHeldItemOffhand().getItem() == LuckyItems.SHADOW_DAGGER;
        boolean isNonPlayerOrSneaking = entity instanceof EntityPlayer ? entity.isSneaking() : true; 
        if(isNonPlayerOrSneaking && holdsDagger) {
            long x = Minecraft.getSystemTime();
            float alpha = (float) Math.sin(0.00025f*Math.PI*x)*0.3f;
            if(alpha <= 0.1f) {
                event.setCanceled(true);
            }
            else {
                GlStateManager.enableBlend();
                GlStateManager.getFloat(GL11.GL_CURRENT_COLOR, colorBuffer);
                GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GlStateManager.color(colorBuffer.get(), colorBuffer.get(), colorBuffer.get(), alpha);
                colorBuffer.clear();
            }
        }
    }
    
}
