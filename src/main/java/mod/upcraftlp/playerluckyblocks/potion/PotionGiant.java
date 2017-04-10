package mod.upcraftlp.playerluckyblocks.potion;

import mod.upcraftlp.playerluckyblocks.potion.core.LuckyPotion;

public class PotionGiant extends LuckyPotion {

	public static final String KEY_GIANT = "is_giant";
	public static final String KEY_DEFAULT_DATA = "defaultEntity";
	public static final String KEY_HEIGHT = "height";
	public static final String KEY_WIDTH = "width";
	
	public PotionGiant(int textureOffset) {
		super("flight", false, 0x2fc38b, textureOffset);
	}
	
	@Override
	public boolean hasStatusIcon() {
		return true;
	}
	
	//FIXME custom render handler
//	public static void setSize(EntityLivingBase entityLivingBaseIn, NBTTagCompound nbt, float sizeMultiplier) {
//		nbt.setByte(KEY_GIANT, (byte) 1);
//		NBTTagCompound defaultEntity = new NBTTagCompound();
//		defaultEntity.setFloat(KEY_HEIGHT, entityLivingBaseIn.height);
//		defaultEntity.setFloat(KEY_WIDTH, entityLivingBaseIn.width);
//		nbt.setTag(KEY_DEFAULT_DATA, defaultEntity);
//		entityLivingBaseIn.height *= sizeMultiplier;
//		entityLivingBaseIn.width *= sizeMultiplier;
//	}
//
//	public static void resetSize(EntityLivingBase entityLiving) {
//		NBTTagCompound nbt = (NBTTagCompound) entityLiving.getEntityData().getTag(KEY_DEFAULT_DATA);
//		entityLiving.height = nbt.getFloat(KEY_HEIGHT);
//		entityLiving.width = nbt.getFloat(KEY_WIDTH);
//		entityLiving.getEntityData().removeTag(KEY_DEFAULT_DATA);
//	}

}
