package mod.upcraftlp.playerluckyblocks.potion;

import java.util.Random;

import core.upcraftlp.craftdev.API.world.WorldHelper;
import mod.upcraftlp.playerluckyblocks.potion.core.LuckyPotion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.Explosion;

public class PotionSparkling extends LuckyPotion {

    private static final Random rand = new Random();
    
    public PotionSparkling(int textureIndex) {
        super("sparkling", false, 0x5c5d8d, textureIndex);
    }
    
    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
    
    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        if(entityLivingBaseIn.world.getTotalWorldTime() % 10 == rand.nextInt(10)) {
            for(int i = 0; i < rand.nextInt(30); i++) WorldHelper.spawnParticles(entityLivingBaseIn.world, EnumParticleTypes.CRIT_MAGIC, false, entityLivingBaseIn.posX, entityLivingBaseIn.posY + entityLivingBaseIn.height / 2, entityLivingBaseIn.posZ, rand.nextFloat() * 2.0F - 1.0F, rand.nextFloat() * 2.0F - 1.0F, rand.nextFloat() * 2.0F - 1.0F);
        }
        if(entityLivingBaseIn.getActivePotionEffect(this).getDuration() == 1) {
            Explosion e = entityLivingBaseIn.world.createExplosion(null, entityLivingBaseIn.posX, entityLivingBaseIn.posY, entityLivingBaseIn.posZ, 8.5f, false);
            entityLivingBaseIn.attackEntityFrom(DamageSource.causeExplosionDamage(e), Float.MAX_VALUE);
        }
        super.performEffect(entityLivingBaseIn, amplifier);
    }

}
