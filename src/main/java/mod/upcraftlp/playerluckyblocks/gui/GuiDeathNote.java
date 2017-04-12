package mod.upcraftlp.playerluckyblocks.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import mod.upcraftlp.playerluckyblocks.net.PacketDeathNote;
import mod.upcraftlp.playerluckyblocks.special.TempNetworkHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDeathNote extends GuiScreen {

//    private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation("textures/gui/book.png");
    
    private GuiTextField text;

    public GuiDeathNote() {
        
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//        this.mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
//        int i = (this.width - 192) / 2;
//        this.drawTexturedModalRect(i, 2, 0, 0, 192, 192);
        this.text.drawTextBox(); //TODO: book entry
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    @Override
    public void initGui() {
       this.addButton(new GuiButton(0, this.width / 2 - 100, this.height / 2 + 4, TextFormatting.RED + "KILL"));
       this.text = new GuiTextField(1, this.fontRenderer, this.width / 2 - 100, this.height / 2 - 20, 200, 20);
       this.text.setMaxStringLength(32);
       this.text.setFocused(false);
       this.text.setCanLoseFocus(true);
    }
    
    @Override
    public void updateScreen() {
        super.updateScreen();
        this.text.updateCursorCounter();
    }
    
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if(keyCode == Keyboard.KEY_RETURN) {
            this.actionPerformed(this.buttonList.get(0));
            return;
        }
        if(this.text.isFocused()) {
            this.text.textboxKeyTyped(typedChar, keyCode);
        } else {
            super.keyTyped(typedChar, keyCode);
        }
        
    }
    
    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.id == 0) {
            TempNetworkHandler.INSTANCE.sendToServer(new PacketDeathNote(this.text.getText()));
        }
    }
    
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.text.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
}
