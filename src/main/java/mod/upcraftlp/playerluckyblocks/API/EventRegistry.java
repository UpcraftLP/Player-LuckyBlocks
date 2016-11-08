package mod.upcraftlp.playerluckyblocks.API;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import mod.upcraftlp.playerluckyblocks.Main;
import mod.upcraftlp.playerluckyblocks.baseEvents.EventItemDrop;
import net.minecraft.item.ItemStack;

public class EventRegistry {

	private static final Random RANDOM = new Random();
	private static List<IEventProvider> positive = new ArrayList<IEventProvider>();
	private static List<IEventProvider> negative = new ArrayList<IEventProvider>();
	private static List<IEventProvider> neutral = new ArrayList<IEventProvider>();
	private static List<IEventProvider> badass = new ArrayList<IEventProvider>();
	
	public static void registerEvent(IEventProvider event, EnumLuck luck) {
		getListFromLuck(luck).add(event);
	}
	
	public static void registerDrop(List<ItemStack> drops, String dropName, EnumLuck luck) {
		getListFromLuck(luck).add(new EventItemDrop(drops, dropName));
	}
	
	private static List<IEventProvider> getListFromLuck(EnumLuck luck) {
		switch(luck) {
		case POSITIVE: return positive;
		case NEGATIVE: return negative;
		case BADASS: return badass;
		case NEUTRAL: 
		default: return neutral;
		}
		
	}
	
	@Nullable
	public static IEventProvider getEvent(EnumLuck luck) {
		List<IEventProvider> list = getListFromLuck(luck);
		if(list.isEmpty()) return null;
		return list.get(RANDOM.nextInt(list.size()));
	}
	
	/**
	 * removes ALL occurrences of a specific event
	 * @param eventName name of the event to remove
	 */
	public void unregister(String eventName) {
		for(EnumLuck luck : EnumLuck.values()) {
			List<IEventProvider> providers = getListFromLuck(luck);
			Iterator<IEventProvider> i = providers.iterator();
			while(i.hasNext()) {
				IEventProvider event = i.next();
				if(event.getName().equals(eventName)) {
					providers.remove(event);
					Main.getLogger().println("Successfully unregistered Event: " + eventName);
				}
			}
		}
	}
}
