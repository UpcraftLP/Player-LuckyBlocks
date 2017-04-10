package mod.upcraftlp.playerluckyblocks.items;

import core.upcraftlp.craftdev.API.templates.Item;
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
    }
    
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if(!worldIn.isRemote) {
            stack.damageItem(1, playerIn);
            Vec3d lookVec = playerIn.getLookVec();
            playerIn.motionX += lookVec.xCoord * 2.0D;
            playerIn.motionY += lookVec.yCoord * 2.0D;
            playerIn.motionZ += lookVec.zCoord * 2.0D;
            playerIn.getCooldownTracker().setCooldown(this, 100);
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    };

}
