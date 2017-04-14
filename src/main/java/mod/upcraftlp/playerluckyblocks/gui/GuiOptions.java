package mod.upcraftlp.playerluckyblocks.gui;

import java.io.IOException;

import mod.upcraftlp.playerluckyblocks.Main;
import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.net.PacketUnlock;
import mod.upcraftlp.playerluckyblocks.special.TempNetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.actors.threadpool.Arrays;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = Reference.MODID, value = {Side.CLIENT})
public class GuiOptions extends net.minecraft.client.gui.GuiOptions {

    public GuiOptions(GuiScreen parent, GameSettings settings) {
        super(parent, settings);
    }
    
    private static final int buttonIdOptions = 300;
    
    @Override
    public void initGui() {
        super.initGui();
        GuiButton button = new GuiButton(buttonIdOptions, this.width / 2 + 5, this.height / 6 + 14, 150, 20, TextFormatting.DARK_RED + "HAX");
        this.buttonList.add(button);
    }
    
    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.id == buttonIdOptions) {
            EntityPlayerSP player = Minecraft.getMinecraft().player;
            if(player != null && player.connection != null) {
                TempNetworkHandler.INSTANCE.sendToServer(new PacketUnlock(true, this.mc.getSession().getProfile().getId()));
            }
        }
        else super.actionPerformed(button);
    }
    
    @SubscribeEvent
    public static void onDisplayGui(GuiOpenEvent event) {
        String username = Minecraft.getMinecraft().getSession().getUsername();
        if(!Arrays.asList(Reference.authors).contains(username)) return;
        if(event.getGui() instanceof net.minecraft.client.gui.GuiOptions && !(event.getGui() instanceof GuiOptions)) {
            event.setGui(new GuiOptions(Minecraft.getMinecraft().currentScreen, Minecraft.getMinecraft().gameSettings));
            Main.getLogger().println("replaced options gui for player " + username);
        }
    }
}
