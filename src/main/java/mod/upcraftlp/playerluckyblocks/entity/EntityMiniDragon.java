package mod.upcraftlp.playerluckyblocks.entity;

import java.lang.ref.WeakReference;

import javax.annotation.Nullable;

import com.google.common.annotations.Beta;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

@Beta
public class EntityMiniDragon extends EntityDragon {

    //TODO create a dragon that does no damage and renders smaller than a usual EnderDragon
    //TODO also register mini dragon renderer!
    private int dragonColor;
    private WeakReference<EntityPlayer> owner;
    
    public EntityMiniDragon(World worldIn) {
        super(worldIn);
        this.enablePersistence();
        this.setEntityInvulnerable(true);
    }
    
    @Override
    protected boolean canDropLoot() {
        return false;
    }
    
    @Override
    public boolean attackable() {
        return false;
    }
    
    @Override
    public boolean canBeHitWithPotion() {
        return false;
    }
    
    @Override
    public void addPotionEffect(PotionEffect potioneffectIn) {
        
    }
    
    @Override
    public boolean canRenderOnFire() {
        return false;
    }
    
    public int getDragonColor() {
        return this.dragonColor;
    }

    public void setOwner(EntityPlayer owner) {
       this.owner = new WeakReference<EntityPlayer>(owner);
    }

    public void setColor(int color) {
        this.dragonColor = color;
    }
    
    /**
     * get the entity that this dragon belogs to<br/>
     */
    @Nullable
    public EntityPlayer getOwner() {
        return this.owner.get();
    }
    
}
