package mod.upcraftlp.playerluckyblocks.event;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import mod.upcraftlp.playerluckyblocks.init.LuckyMisc.DamageSources;
import mod.upcraftlp.playerluckyblocks.items.armor.ItemEnderArmor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class EnderArmorHandler {

	private static final List<DamageSource> restrictedSources = Arrays.asList(new DamageSource[]{DamageSources.enderDenyWater, DamageSource.DROWN, DamageSource.OUT_OF_WORLD, DamageSource.IN_WALL, DamageSource.FALLING_BLOCK});
	private static final Random rand = new Random();
	
	@SubscribeEvent
	public static void onEnderHit(LivingAttackEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		if(!restrictedSources.contains(event.getSource()) && event.getAmount() < entity.getHealth() && event.getAmount() > 0) {
			for(ItemStack stack : entity.getArmorInventoryList()) {
				if(stack == null || !(stack.getItem() instanceof ItemEnderArmor)) return;
			}
			randomTeleport(entity);
		}
	}
	
	public static void randomTeleport(EntityLivingBase entity) {
		World world = entity.getEntityWorld();
		if(world.isRemote) return;
		double d0 = entity.posX;
        double d1 = entity.posY;
        double d2 = entity.posZ;

        for (int i = 0; i < 16; ++i)
        {
            double d3 = entity.posX + (entity.getRNG().nextDouble() - 0.5D) * 16.0D;
            double d4 = MathHelper.clamp(entity.posY + (double)(entity.getRNG().nextInt(16) - 8), 0.0D, (double)(world.getActualHeight() - 1));
            double d5 = entity.posZ + (entity.getRNG().nextDouble() - 0.5D) * 16.0D;
            entity.dismountRidingEntity();
            entity.removePassengers();
            if (entity.attemptTeleport(d3, d4, d5))
            {
                //hearing the teleport sound twice if too close to the origin point is intentional!
                world.playSound(null, d0, d1, d2, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, entity.getSoundCategory(), rand.nextFloat() * 0.4f + 0.6f, rand.nextFloat()); //origin pos
                entity.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, rand.nextFloat() * 0.4f + 0.6f, rand.nextFloat()); //new pos
                break;
            }
        }
	}
}
