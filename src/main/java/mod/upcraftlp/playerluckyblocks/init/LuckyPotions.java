package mod.upcraftlp.playerluckyblocks.init;

import mod.upcraftlp.playerluckyblocks.potion.PotionDizzyness;
import mod.upcraftlp.playerluckyblocks.potion.PotionGiant;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class LuckyPotions {
	
	public static final Potion GIANT = new PotionGiant();
	public static final Potion DIZZYNESS = new PotionDizzyness();
	
	public static void init() {
		GameRegistry.register(GIANT);
		GameRegistry.register(DIZZYNESS);
	}

}
