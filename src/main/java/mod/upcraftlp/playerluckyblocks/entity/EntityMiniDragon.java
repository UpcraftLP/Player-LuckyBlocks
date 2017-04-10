package mod.upcraftlp.playerluckyblocks.entity;

import com.google.common.annotations.Beta;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.world.World;

@Beta
public class EntityMiniDragon extends EntityDragon {

    //TODO create a dragon that does no damage and renders smaller than a usual EnderDragon
    //TODO also register mini dragon renderer!
    
    public EntityMiniDragon(World worldIn) {
        super(worldIn);
    }
    
    @Override
    public boolean canRenderOnFire() {
        return false;
    }

    public int getDragonColor() {
        // TODO getColor()!
        return 0;
    }

    public void setOwner(EntityLivingBase owner) {
       //TODO: handle dragon owner 
    }

    public void setColor(int color) {
        // TODO dragon color
        
    }
    
}
