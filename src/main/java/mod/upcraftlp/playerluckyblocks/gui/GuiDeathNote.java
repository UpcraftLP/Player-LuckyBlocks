package mod.upcraftlp.playerluckyblocks.gui;

import com.google.common.collect.Lists;
import core.upcraftlp.craftdev.api.net.NetworkHandler;
import mod.upcraftlp.playerluckyblocks.Main;
import mod.upcraftlp.playerluckyblocks.net.PacketDeathNote;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiDeathNote extends GuiScreen {

    private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation("textures/gui/book.png");
    
    private List<String> cachedNames;
    private String current;
    private int index = 0;
    private boolean hasFocus = false;

    public GuiDeathNote() {
        //NO-OP
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
        int i = (this.width - 192) / 2;
        this.drawTexturedModalRect(i, 2, 0, 0, 192, 192);

        int line;
        for(line = 0; line < this.cachedNames.size(); line++) {
            String text = this.cachedNames.get(line);
            this.fontRenderer.drawString(text, i + 36, 34 + line * this.fontRenderer.FONT_HEIGHT, 0);
        }
        String text = this.current;
        if(this.hasFocus) {
            if(!this.fontRenderer.getBidiFlag()) {
                if(this.updateCount / 6 % 2 == 0) text += TextFormatting.BLACK;
                else text += TextFormatting.GRAY;
            }
            text += "_";
        }
        this.fontRenderer.drawString(text, i + 36, 34 + line * this.fontRenderer.FONT_HEIGHT, 0);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    @Override
    public void initGui() {
        this.cachedNames = Lists.newArrayList();
        this.current = "";
       this.addButton(new GuiButton(0, this.width / 2 - 100, this.height / 2 + 4, TextFormatting.RED + "KILL"));
    }

    private int updateCount;
    
    @Override
    public void updateScreen() {
        this.updateCount++;
        super.updateScreen();
        //this.text.updateCursorCounter();
    }
    
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
//        if(keyCode == Keyboard.KEY_RETURN && !this.text.isFocused()) {
//            this.actionPerformed(this.buttonList.get(0));
//            return;
//        }
        if(keyCode == Keyboard.KEY_RETURN) {
            this.cachedNames.add(this.index, this.current);
            this.index++;
            this.current = "";
        }
        else if(keyCode == Keyboard.KEY_ESCAPE) super.keyTyped(typedChar, keyCode); //TODO focus?
        else if(keyCode == Keyboard.KEY_DELETE) this.current = this.current.substring(0, this.current.length());
        else {
            if(ChatAllowedCharacters.isAllowedCharacter(typedChar)) this.current += typedChar;
        }
    }
    
    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.id == 0) {
            NetworkHandler.INSTANCE.sendToServer(new PacketDeathNote(this.current));
        }
    }
    
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        int width = (this.width - 192) / 2;
        this.hasFocus = mouseX > width + 36 && mouseX < width + 36 + 116 && mouseY > 34 && mouseY < 168;
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
}
