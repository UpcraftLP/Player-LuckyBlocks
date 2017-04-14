package mod.upcraftlp.playerluckyblocks.event;

import java.util.Random;

import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.config.LuckyConfig;
import mod.upcraftlp.playerluckyblocks.world.DragonData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

@EventBusSubscriber(modid = Reference.MODID)
public class PlayerHandler {

    private static final Random rand = new Random();
    
    @SubscribeEvent
    public static void onPlayerJoined(PlayerLoggedInEvent event) {
        EntityPlayer player = event.player;
        DragonData.get(player).playerJoin(player);
    }
    
    @SubscribeEvent
    public static void tickPlayer(PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if(player.world.isRemote || rand.nextInt(500) != 0) return;
        if(LuckyConfig.players.contains(player.getUniqueID())) {
            //TODO random events
        }
    }
}
