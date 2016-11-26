package mod.upcraftlp.playerluckyblocks.baseEvents;

import mod.upcraftlp.playerluckyblocks.API.IEventProvider;
import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EventEnderPigZombie implements IEventProvider {

	@Override
	public String getName() {
		return "EventEnderPigZombie";
	}

	@Override
	public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		EntityPigZombie pigZombie = new EntityPigZombie(world);
		pigZombie.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY(), pos.getZ()+ 0.5D);
		pigZombie.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.DIAMOND_SWORD));
		pigZombie.setAttackTarget(player);
		pigZombie.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(LuckyItems.ENDER_HELMET));
		pigZombie.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(LuckyItems.ENDER_CHESTPLATE));
		pigZombie.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(LuckyItems.ENDER_LEGGINGS));
		pigZombie.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(LuckyItems.ENDER_BOOTS));
		pigZombie.forceSpawn = true;
		pigZombie.setCustomNameTag("Ender Bob");
		pigZombie.enablePersistence();
		world.spawnEntity(pigZombie);
	}

}
