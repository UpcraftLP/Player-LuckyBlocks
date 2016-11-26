package mod.upcraftlp.playerluckyblocks.event;

import java.util.Arrays;
import java.util.List;

import mod.upcraftlp.playerluckyblocks.init.LuckyMisc.DamageSources;
import mod.upcraftlp.playerluckyblocks.items.armor.ItemEnderArmor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnderArmorHandler {

	private List<DamageSource> restrictedSources = Arrays.asList(new DamageSource[]{DamageSources.enderDenyWater, DamageSource.DROWN, DamageSource.OUT_OF_WORLD, DamageSource.IN_WALL, DamageSource.FALLING_BLOCK});
	
	@SubscribeEvent
	public void onEnderHit(LivingAttackEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		if(!restrictedSources.contains(event.getSource()) && !entity.world.isRemote && event.getAmount() < entity.getHealth() && event.getAmount() > 0) {
			for(ItemStack stack : entity.getArmorInventoryList()) {
				if(stack == null || !(stack.getItem() instanceof ItemEnderArmor)) return;
			}
			randomTeleport(entity);
		}
	}
	
	public static void randomTeleport(EntityLivingBase entity) {
		World world = entity.world;
		double d0 = entity.posX;
        double d1 = entity.posY;
        double d2 = entity.posZ;

        for (int i = 0; i < 16; ++i)
        {
            double d3 = entity.posX + (entity.getRNG().nextDouble() - 0.5D) * 16.0D;
            double d4 = MathHelper.clamp(entity.posY + (double)(entity.getRNG().nextInt(16) - 8), 0.0D, (double)(world.getActualHeight() - 1));
            double d5 = entity.posZ + (entity.getRNG().nextDouble() - 0.5D) * 16.0D;

            if (entity.isRiding())
            {
                entity.dismountRidingEntity();
            }

            if (entity.attemptTeleport(d3, d4, d5))
            {
                world.playSound((EntityPlayer)null, d0, d1, d2, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                entity.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                break;
            }
        }
	}
}
