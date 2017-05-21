package mod.upcraftlp.playerluckyblocks.event;

import java.util.Iterator;
import java.util.Random;

import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.init.LuckyPotions;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.PotionColorCalculationEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Reference.MODID)
public class PotionHandler {

private static final Random RANDOM = new Random();
	
    private static final int[] rainbowColors = {
            0xa51488,
            0x32d01e,
            0x595c71,
            0x1eaac2,
            0xc3c30e
    };
	
	@SubscribeEvent
	public static void setPotionColor(PotionColorCalculationEvent event) {
		for (PotionEffect effect : event.getEffects()) {
			if (effect.getPotion() == LuckyPotions.DIZZYNESS) {
				int color = rainbowColors[RANDOM.nextInt(rainbowColors.length)];
				event.setColor(color);
			}
		}
	}
}
