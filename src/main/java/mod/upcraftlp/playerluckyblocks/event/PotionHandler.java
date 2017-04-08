package mod.upcraftlp.playerluckyblocks.event;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
	
	private static final List<String> rainbowColors = Arrays.asList(new String[] {
			"a51488",
			"32d01e",
			"595c71",
			"1eaac2",
			"c3c30e"
	});
	
	@SubscribeEvent
	public static void onDizzyPotion(PotionColorCalculationEvent event) {
		Iterator<PotionEffect> i = event.getEffects().iterator();
		while(i.hasNext()) {
			PotionEffect effect = i.next();
			if(effect.getPotion().equals(LuckyPotions.DIZZYNESS)) {
				int color = Integer.parseUnsignedInt(rainbowColors.get(RANDOM.nextInt(rainbowColors.size())), 16);
				event.setColor(color);
			}
		}
	}
}
