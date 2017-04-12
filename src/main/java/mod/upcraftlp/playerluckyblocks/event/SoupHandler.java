package mod.upcraftlp.playerluckyblocks.event;

import mod.upcraftlp.playerluckyblocks.Reference;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = Reference.MODID)
public class SoupHandler {

    @SubscribeEvent
    public static void onSoupEaten(PlayerInteractEvent.RightClickItem event) {
        if(event.getSide() != Side.SERVER || event.getEntityPlayer().isCreative()) return;
        ItemStack stack = event.getItemStack();
        if(stack.getItem() instanceof ItemSoup) {
            ItemSoup item = (ItemSoup) stack.getItem();
            EntityPlayer player = event.getEntityPlayer();
            player.heal(item.getHealAmount(stack));
            player.getFoodStats().addStats(item, stack);
            stack.shrink(1);
            player.setHeldItem(event.getHand(), stack);
            EntityItem itemEntity = new EntityItem(event.getWorld(), player.posX, player.posY, player.posZ, new ItemStack(Items.BOWL));
            itemEntity.setDefaultPickupDelay();
            event.getWorld().spawnEntity(itemEntity);
            event.setCanceled(true);
        }
    }
}
