package mod.upcraftlp.playerluckyblocks.event;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;

import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import mod.upcraftlp.playerluckyblocks.init.LuckyPotions;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = Reference.MODID)
public class FlightHandler {

    public static final List<UUID> flightPlayers = Lists.newArrayList();
    
    @SubscribeEvent
    public static void checkFlight(PlayerTickEvent event) {
        if(event.side != Side.SERVER) return;
        EntityPlayerMP player = (EntityPlayerMP) event.player;
        UUID uuid = player.getUniqueID();
        boolean isNotCreative = !player.capabilities.isCreativeMode;
        if(flightPlayers.contains(uuid)) {
            if(!isFlightPlayer(player)) {
                flightPlayers.remove(player.getUniqueID());
                if(isNotCreative) {
                    player.capabilities.allowFlying = false;
                    player.capabilities.isFlying = false; //drops player out of the sky
                    player.sendPlayerAbilities(); //sync the change to the client to actually stop the player from flying
                }
            }
            else if(!player.capabilities.allowFlying) {
                player.capabilities.allowFlying = true;
                player.sendPlayerAbilities();
            }
        }
    }
    
    /**
     * determine if a player meets at least one of the specified conditions to fly
     */
    private static boolean isFlightPlayer(EntityPlayerMP player) {
        if(player.isPotionActive(LuckyPotions.FLIGHT)) return true;
        ItemStack jetPack = player.inventory.armorInventory.get(2);
        if(!jetPack.isEmpty() && jetPack.getItem() == LuckyItems.JETPACK) {
            if(jetPack.hasTagCompound()) return jetPack.getTagCompound().getBoolean("active");
        }
        return false;
    }
    
}
