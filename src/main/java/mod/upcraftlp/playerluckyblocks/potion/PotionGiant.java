package mod.upcraftlp.playerluckyblocks.potion;

import core.upcraftlp.craftdev.API.templates.Potion;
import mod.upcraftlp.playerluckyblocks.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionGiant extends Potion {

	public static final ResourceLocation ICON = new ResourceLocation(Reference.MODID, "textures/gui/potions.png");
	public static final String KEY_GIANT = "is_giant";
	public static final String KEY_DEFAULT_DATA = "defaultEntity";
	public static final String KEY_HEIGHT = "height";
	public static final String KEY_WIDTH = "width";
	
	public PotionGiant() {
		super("flight", false, 0x2fc38b);
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
	
	public static void setSize(EntityLivingBase entityLivingBaseIn, NBTTagCompound nbt, float sizeMultiplier) {
		nbt.setByte(KEY_GIANT, (byte) 1);
		NBTTagCompound defaultEntity = new NBTTagCompound();
		defaultEntity.setFloat(KEY_HEIGHT, entityLivingBaseIn.height);
		defaultEntity.setFloat(KEY_WIDTH, entityLivingBaseIn.width);
		nbt.setTag(KEY_DEFAULT_DATA, defaultEntity);
		entityLivingBaseIn.height *= sizeMultiplier;
		entityLivingBaseIn.width *= sizeMultiplier;
	}

	public static void resetSize(EntityLivingBase entityLiving) {
		NBTTagCompound nbt = (NBTTagCompound) entityLiving.getEntityData().getTag(KEY_DEFAULT_DATA);
		entityLiving.height = nbt.getFloat(KEY_HEIGHT);
		entityLiving.width = nbt.getFloat(KEY_WIDTH);
		entityLiving.getEntityData().removeTag(KEY_DEFAULT_DATA);
	}
	
}
