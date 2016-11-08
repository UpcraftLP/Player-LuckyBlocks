package mod.upcraftlp.playerluckyblocks.init;

import mod.upcraftlp.playerluckyblocks.potion.PotionFlight;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class LuckyPotions {
	
	public static Potion GIANT = new PotionFlight();
	
	public static void init() {
		GameRegistry.register(GIANT);
	}

}
