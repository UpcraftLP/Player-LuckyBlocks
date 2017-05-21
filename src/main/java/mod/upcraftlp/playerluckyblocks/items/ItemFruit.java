package mod.upcraftlp.playerluckyblocks.items;

import java.util.List;
import java.util.Random;

import core.upcraftlp.craftdev.API.templates.ItemFood;
import mod.upcraftlp.playerluckyblocks.event.FlightHandler;
import mod.upcraftlp.playerluckyblocks.init.LuckyPotions;
import mod.upcraftlp.playerluckyblocks.init.LuckyTabs;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFruit extends ItemFood {

    private static final Random rand = new Random();
    public static final String KEY_FRUIT_MODEL = "model";
    
	public ItemFruit() {
		super("devil_fruit", 3, 3.0f, false);
		this.setMaxStackSize(1);
		this.setCreativeTab(LuckyTabs.tabPlayerLucky); //need this here because the fruit is handled special
	}
	
	@Override
	public final void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
	    if(worldIn.isRemote || itemRand.nextFloat() >= 0.95f) return;
	    switch(stack.getMetadata()) {
	        case 0: //(black)death
	            FoodStats stats = player.getFoodStats();
	            stats.setFoodLevel(0);
	            stats.setFoodSaturationLevel(100.0f);
	            player.attackEntityFrom(DamageSource.STARVE, Float.MAX_VALUE);
	            player.addPotionEffect(new PotionEffect(MobEffects.WITHER, 6000, 9));
	            break;
	            
	        case 1: //(red)
	            
	            break;
	            
	        case 2: //(green)giant
	            //TODO enlarge player and hitbox, also reach distance!
	            break;
	            
	        case 3: //(brown)
	            break;
	            
	        case 4: //(blue)water
	            player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 6000));
	            break;
	            
	        case 5: //(purple)dizzyness
	            player.addPotionEffect(new PotionEffect(LuckyPotions.DIZZYNESS, ((int) rand.nextFloat() * 500) + 1000));
	            break;
	            
	        case 6: //(cyan)sparkling
	            player.addPotionEffect(new PotionEffect(LuckyPotions.SPARKLING, ((int) rand.nextFloat() * 500) + 1000));
                break;
                
	        case 7: //(light gray)
                break;
                
	        case 8: //(gray)
                break;
                
	        case 9: //(pink)
                break;
                
	        case 10: //(lime)
                break;
                
	        case 11: //(yellow)
                break;
                
	        case 12: //(light blue)flight
	            player.addPotionEffect(new PotionEffect(LuckyPotions.FLIGHT, ((int) rand.nextFloat() * 800) + 1000, 1));
                FlightHandler.flightPlayers.add(player.getUniqueID());
                break;
                
	        case 13: //(magenta)
                break;
                
	        case 14: //(orange)explosion
				worldIn.createExplosion(player, player.posX, player.posY, player.posZ, 6.5F, true);
                break;
                
	        case 15: //(white)
                break;
            default: //unsupported meta
                break;
	    }
	}
	
	//needed for the fruit chest event
	public void getSubItems(NonNullList<ItemStack> subItems) {
	    for(int i = 0; i < 16; i++) {
            subItems.add(new ItemStack(this, 1, i));
        }
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        getSubItems(subItems);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add(TextFormatting.GRAY + I18n.format("tooltip.lucky.devilsfruit"));
    }
    
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        if(nbt == null) nbt = new NBTTagCompound();
        nbt.setInteger(KEY_FRUIT_MODEL, rand.nextInt(16));
        stack.setTagCompound(nbt);
        return super.initCapabilities(stack, nbt);
    }
    
    @Override
    public boolean getHasSubtypes() {
        return true;
    }
    
    @Override
    public boolean getShareTag() {
        return false;
    }
    
}
