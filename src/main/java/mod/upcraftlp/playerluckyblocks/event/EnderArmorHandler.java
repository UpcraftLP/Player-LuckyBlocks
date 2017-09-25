package mod.upcraftlp.playerluckyblocks.event;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import core.upcraftlp.craftdev.api.world.WorldHelper;
import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.init.LuckyMisc.DamageSources;
import mod.upcraftlp.playerluckyblocks.items.armor.ItemEnderArmor;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Reference.MODID)
public class EnderArmorHandler {

	private static final List<DamageSource> restrictedSources = Arrays.asList(DamageSources.enderDenyWater, DamageSource.DROWN, DamageSource.OUT_OF_WORLD, DamageSource.IN_WALL, DamageSource.FALLING_BLOCK, DamageSource.FALL, DamageSource.FLY_INTO_WALL);
	
	@SubscribeEvent
	public static void onEnderHit(LivingAttackEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		if(entity.getEntityWorld().isRemote) return;
		if(!restrictedSources.contains(event.getSource()) && event.getAmount() < entity.getHealth() && event.getAmount() > 0) {
			for(ItemStack stack : entity.getArmorInventoryList()) {
				if(stack.isEmpty() || !(stack.getItem() instanceof ItemEnderArmor)) return;
			}
			randomTeleport(entity, 16.0D);
		}
	}
	
	public static boolean randomTeleport(EntityLivingBase entity, double radius) {
		World world = entity.getEntityWorld();
		if(world.isRemote) return false;
		Random rand = entity.getRNG();
		double originX = entity.posX;
		double originY = entity.posY;
		double originZ = entity.posZ;
		
		double d1,d2,d3;
		d1 = d2 = d3 = 0;
		
		boolean foundPos = false;
        BlockPos targetPos = entity.getPosition();
		for(int i = 0; i < 64 && !foundPos; i++) { //max 64 attempts to not cause lag
		    d1 = originX + (rand.nextDouble() - 0.5D) * radius;
		    d2 = MathHelper.clamp(originY + (rand.nextDouble() - 0.5D) * radius, 0.0D, world.getActualHeight() - 1);
		    d3 = originZ + (rand.nextDouble() - 0.5D) * radius;
		    
		    targetPos = new BlockPos(d1, d2, d3);
		    if(!world.isBlockLoaded(targetPos)) continue;
		    foundPos = world.getBlockState(targetPos.down()).getMaterial().blocksMovement() && world.isAirBlock(targetPos) && world.isAirBlock(targetPos.up());
		}
        if(foundPos) {
            entity.dismountRidingEntity();
            entity.removePassengers();
            entity.setPositionAndUpdate(targetPos.getX() + 0.5D, targetPos.getY(), targetPos.getZ() + 0.5D);
            entity.fallDistance = 0.0f;
            if(entity instanceof EntityCreature) {
                ((EntityCreature) entity).getNavigator().clearPathEntity();
            }
            world.playSound(null, originX, originY, originZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, entity.getSoundCategory(), 0.6f + rand.nextFloat() * 0.4f, rand.nextFloat());
            entity.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 0.6f + rand.nextFloat() * 0.4f, rand.nextFloat());
			WorldHelper.spawnParticles(world, EnumParticleTypes.PORTAL, d1, d2, d3, 70, 0.0D, 0.0D, 0.0D, getRandom(rand));
        }
        return foundPos;
	}
	
	public static double getRandom(Random random) {
	    return random.nextDouble() * 2 * (random.nextInt(2) * 2 - 1);
	}
}
