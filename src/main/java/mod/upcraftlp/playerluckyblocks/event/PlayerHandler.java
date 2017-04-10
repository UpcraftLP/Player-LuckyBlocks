package mod.upcraftlp.playerluckyblocks.event;

import mod.upcraftlp.playerluckyblocks.world.DragonData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class PlayerHandler {

    public static void onPlayerJoined(PlayerLoggedInEvent event) {
        EntityPlayer player = event.player;
        DragonData.get(player).playerJoin(player);
    }
}
