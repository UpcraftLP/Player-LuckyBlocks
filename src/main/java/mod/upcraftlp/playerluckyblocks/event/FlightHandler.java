package mod.upcraftlp.playerluckyblocks.event;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;

import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = Reference.MODID)
public class FlightHandler {

    public static final List<UUID> flightPlayers = Lists.newArrayList();
    
    @SubscribeEvent
    public static void checkFlight(PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if(event.side != Side.SERVER) return;
        UUID uuid = player.getUniqueID();
        if(flightPlayers.contains(uuid)) {
            if(player.inventory.armorInventory.get(2).getItem() != LuckyItems.JETPACK) {
                player.capabilities.allowFlying = false;
                flightPlayers.remove(player.getUniqueID());
                player.capabilities.isFlying = false; //drops player out of the sky
                player.sendPlayerAbilities(); //sync the change to the client to actually stop the player from flying
            }
            else if(!player.capabilities.allowFlying) {
                player.capabilities.allowFlying = true;
                player.sendPlayerAbilities();
            }
        }
    }
    
}
