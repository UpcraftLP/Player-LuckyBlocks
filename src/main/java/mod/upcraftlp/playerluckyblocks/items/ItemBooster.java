package mod.upcraftlp.playerluckyblocks.items;

import java.util.List;

import core.upcraftlp.craftdev.api.item.Item;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemBooster extends Item {

    public ItemBooster() {
        super("booster");
        this.setMaxStackSize(1);
        this.setMaxDamage(100);
    }
    
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if(!worldIn.isRemote) {
            stack.damageItem(1, playerIn);
            Vec3d lookVec = playerIn.getLookVec();
            playerIn.addVelocity(lookVec.x * 4.0D, lookVec.y *1.5D, lookVec.z * 4.0D);
            playerIn.getCooldownTracker().setCooldown(this, 100);
            playerIn.velocityChanged = true;
            if(itemRand.nextInt(20) == 0 && !playerIn.capabilities.isCreativeMode) stack = ItemStack.EMPTY;
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("tooltip.lucky.booster"));
    }

}
