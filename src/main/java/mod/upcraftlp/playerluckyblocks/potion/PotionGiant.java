package mod.upcraftlp.playerluckyblocks.potion;

import core.upcraftlp.craftdev.API.templates.Potion;
import mod.upcraftlp.playerluckyblocks.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionGiant extends Potion {

	public static final ResourceLocation ICON = new ResourceLocation(Reference.MODID, "textures/gui/potions.png");
	public static final String KEY_GIANT = "is_giant";
	
	public PotionGiant() {
		super("giant", false, 0x2fc38b);
		this.setBeneficial();
	}
	
	@Override
	public boolean hasStatusIcon() {
		return true;
	}
	
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
		mc.renderEngine.bindTexture(ICON);
		mc.currentScreen.drawTexturedModalRect(x + 8, y + 8, 0, 0, 16, 16);
	}
	
}
