package mod.upcraftlp.playerluckyblocks.items;

import core.upcraftlp.craftdev.API.client.ClientHandler;
import core.upcraftlp.craftdev.API.templates.Item;
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

	public ItemTeleportStaff() {
		super("teleport_staff");
		this.setMaxStackSize(1);
		this.setMaxDamage(128);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		if(!worldIn.isRemote && playerIn.isSneaking()) {
			RayTraceResult result = playerIn.rayTrace(32, 1);
			BlockPos pos = playerIn.playerLocation;
			switch(result.typeOfHit) {
			case ENTITY:
				pos = result.getBlockPos();
				break;
			case BLOCK:
				pos = result.getBlockPos().offset(result.sideHit);
				break;
			case MISS:
				Vec3d lookVec = playerIn.getLookVec();
				pos = new BlockPos(playerIn.posX + lookVec.xCoord * 16.0D, playerIn.posY + lookVec.yCoord * 16.0D, playerIn.posZ + lookVec.zCoord * 16.0D);
				break;
			}
			if(worldIn.isAirBlock(pos.up())) {
				if(!playerIn.capabilities.isCreativeMode) itemStackIn.damageItem(1, playerIn);
				worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
				ClientHandler.spawnParticles(EnumParticleTypes.PORTAL, worldIn, true, playerIn.getPosition(), 70, 0.7D);
				playerIn.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY() + 0.2D, pos.getZ() + 0.5D);
				worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
				ClientHandler.spawnParticles(EnumParticleTypes.PORTAL, worldIn, true, playerIn.getPosition(), 70, 0.7D);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
	}
}
