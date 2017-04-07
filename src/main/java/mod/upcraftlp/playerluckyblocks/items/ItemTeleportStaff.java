package mod.upcraftlp.playerluckyblocks.items;

import core.upcraftlp.craftdev.API.templates.Item;
import core.upcraftlp.craftdev.API.world.WorldHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemTeleportStaff extends Item {

    public static final double MAX_TELEPORT_DISTANCE = 32.0D;
    
	public ItemTeleportStaff() {
		super("teleport_staff");
		this.setMaxStackSize(1);
		this.setMaxDamage(128);
		this.setFull3D();
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack itemStackIn = playerIn.getHeldItem(hand);
		if(!worldIn.isRemote && playerIn.isSneaking()) {
			RayTraceResult result = playerIn.rayTrace(MAX_TELEPORT_DISTANCE, 1);
			BlockPos pos = playerIn.getPosition();
			switch(result.typeOfHit) {
			case ENTITY:
				pos = result.getBlockPos();
				break;
			case BLOCK:
				pos = result.getBlockPos().offset(result.sideHit);
				break;
			case MISS:
			default:
				Vec3d lookVec = playerIn.getLookVec();
				pos = new BlockPos(playerIn.posX + lookVec.xCoord * MAX_TELEPORT_DISTANCE, playerIn.posY + lookVec.yCoord * MAX_TELEPORT_DISTANCE, playerIn.posZ + lookVec.zCoord * MAX_TELEPORT_DISTANCE);
				break;
			}
			if(worldIn.isAirBlock(pos.up())) {
				itemStackIn.damageItem(1, playerIn);
				worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
				for(int i = 0; i < 70; i++) {
				    WorldHelper.spawnParticles(worldIn, EnumParticleTypes.PORTAL, true, playerIn.posX, playerIn.posY, playerIn.posZ, 0.05D, 0.05D, 0.05D);
				}
				playerIn.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY() + 0.2D, pos.getZ() + 0.5D);
				playerIn.fallDistance = 0.0f;
				worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
				for(int i = 0; i < 70; i++) {
                    WorldHelper.spawnParticles(worldIn, EnumParticleTypes.PORTAL, true, playerIn.posX, playerIn.posY, playerIn.posZ, 0.05D, 0.05D, 0.05D);
                }
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
	}
}
