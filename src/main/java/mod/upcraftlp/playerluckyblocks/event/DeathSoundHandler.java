package mod.upcraftlp.playerluckyblocks.event;

import mod.upcraftlp.playerluckyblocks.Main;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;;

@EventBusSubscriber
public class DeathSoundHandler {

	@SubscribeEvent
	public static void playDeathSound(PlayerRespawnEvent event) {
		Main.getLogger().println("sound");
		event.player.playSound(SoundEvents.ENTITY_ARROW_HIT_PLAYER, 1.0f, 1.0f);
	}
}
