package mod.upcraftlp.playerluckyblocks.potion.core;

import core.upcraftlp.craftdev.api.potion.Potion;
import mod.upcraftlp.playerluckyblocks.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public abstract class LuckyPotion extends Potion {

    private int[] textureOffsets;
    
    public LuckyPotion(String name, boolean isBadEffectIn, int liquidColorIn, int textureIndex) {
        super(name, isBadEffectIn, liquidColorIn);
        this.setTextureOffsets(Reference.getTextureOffset(textureIndex));
        if(!isBadEffectIn) this.setBeneficial();
    }
    
    @Override
    public boolean hasStatusIcon() {
        return true;
    }
    
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        mc.renderEngine.bindTexture(this.getTexture());
        mc.currentScreen.drawTexturedModalRect(x + 8, y + 8, this.textureOffsets[0], this.textureOffsets[1], 16, 16);
    }
    
    public void setTextureOffsets(int[] offsets) {
        this.textureOffsets = offsets;
    }
    
    private ResourceLocation getTexture() {
        return Reference.POTION_ICON;
    }

}
