package mod.upcraftlp.playerluckyblocks.items;

import core.upcraftlp.craftdev.API.templates.ItemFood;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ItemFruit extends ItemFood {

	public ItemFruit() {
		super("devils_fruit", 3, 3.0f, false);
	}
	
	@Override
	public final void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
	    if(worldIn.isRemote || itemRand.nextFloat() >= 0.95f) return;
	    switch(stack.getMetadata()) {
	        case 0: //death
	            FoodStats stats = player.getFoodStats();
	            stats.setFoodLevel(0);
	            stats.setFoodSaturationLevel(100.0f);
	            player.attackEntityFrom(DamageSource.STARVE, Float.MAX_VALUE);
	            player.addPotionEffect(new PotionEffect(MobEffects.WITHER, 6000, 9));
	            break;
	            
	        case 1: //flight
	            //TODO creative flight!
	            //see flight handler!
	            break;
	            
	        case 2: //giant
	            //TODO enlarge player and hitbox, also reach distance!
	            break;
	            
	        case 3: //magic
	            //TODO magic effect?!
	            //sparkling effect all the time
	            break;
	            
	        case 4: //water
	            player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 6000));
	            break;
	            
	        case 5:
	            break;
	            
	        case 6:
                break;
                
	        case 7:
                break;
                
	        case 8:
                break;
                
	        case 9:
                break;
                
	        case 10:
                break;
                
	        case 11:
                break;
                
	        case 12:
                break;
                
	        case 13:
                break;
                
	        case 14:
                break;
                
	        case 15:
                break;
                
            default:
                break;
	    }
	}
	
    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for(int i = 0; i < 16; i++) {
            subItems.add(new ItemStack(this, 1, i));
        }
    }
    
}
