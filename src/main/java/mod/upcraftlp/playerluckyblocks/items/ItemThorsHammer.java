package mod.upcraftlp.playerluckyblocks.items;

import java.util.List;

import core.upcraftlp.craftdev.api.item.Item;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemThorsHammer extends Item {

    public ItemThorsHammer() {
        super("lightning_bolt");
        this.setMaxStackSize(16);
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!worldIn.isRemote) {
            Vec3d eyes = playerIn.getPositionEyes(1.0f);
            Vec3d look = playerIn.getLookVec();
            Vec3d vec = eyes.addVector(look.x * 300, look.y * 300, look.z * 300);
            RayTraceResult result = worldIn.rayTraceBlocks(eyes, vec, true, true, true);
            if(result.typeOfHit != RayTraceResult.Type.MISS) {
                Vec3d target = result.hitVec;
                for(int i = 0; i < itemRand.nextInt(3) + 3; i++) {
                    EntityLightningBolt lightning = new EntityLightningBolt(worldIn, target.x + itemRand.nextInt(3) - 1, target.y, target.z + itemRand.nextInt(3) - 1, true);
                    worldIn.spawnEntity(lightning);
                    worldIn.addWeatherEffect(lightning);
                }
            }
            ItemStack stack = playerIn.getHeldItem(handIn);
            stack.shrink(1);
            playerIn.setHeldItem(handIn, stack);
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add(TextFormatting.GOLD + I18n.format("tooltip.lucky.lightningbolt"));
    }

}
