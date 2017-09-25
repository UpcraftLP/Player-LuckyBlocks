package mod.upcraftlp.playerluckyblocks.api.plugins;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import mod.upcraftlp.playerluckyblocks.util.Utils;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author UpcraftLP
 */
public class DropRegistry {

    private static Map<Integer, Map<ResourceLocation, List<Drop>>> REGISTRY = Maps.newConcurrentMap();
    private static int ID = 0;

    public static List<Drop> forLuck(int luck, ResourceLocation blockName) {

        //TODO calculate randomness
        luck =
                luck >  80 ?  2 :
                luck >  40 ?  1 :
                luck > -30 ?  0 :
                luck > -70 ? -1 :
                             -2;

        if(REGISTRY.containsKey(luck)) return REGISTRY.get(luck).get(blockName);
        else throw new IllegalArgumentException("no such blockdrops registered: " + blockName.toString() + ", for luck range: " + luck);
    }



    public static Drop getRandomDrop(ResourceLocation luckyblock, int luck, boolean hasPlayer) { //TODO implement separate registry for each luckyblock, currently done for ALL luckyblocks together!
        List<Drop> dropCopy = forLuck(luck, luckyblock);
        List<Drop> drops = new ArrayList<>(dropCopy);
        Drop drop;
        long time = System.nanoTime();
        do {
            if(drops.isEmpty()) return null;
            int selected = 0;
            double total = drops.get(0).getChance();
            for(int i = 1; i < drops.size(); i++) {
                Drop current = drops.get(i);
                total += current.getChance();
                if(Utils.RANDOM.nextDouble() <= (current.getChance() / total)) selected = i;
            }
            drop = drops.get(selected);
            if(drop == null) break;
            if(!hasPlayer && drop.requiresPlayer) drops.remove(drop);
        } while ((drop.requiresPlayer && !hasPlayer) && System.nanoTime() - time / 1000 > 500L); //do not return drops that require a player when there is none
        return drop;
    }

    public static void registerDrop(Drop drop, ResourceLocation luckyBlock, int luck) {
        if(drop.getRegistryName() == null) drop.setRegistryName(luckyBlock.getResourcePath(), Integer.toString(ID++));
        Drop.REGISTRY.register(drop);
        Map<ResourceLocation, List<Drop>> map = REGISTRY.containsKey(luck) ? REGISTRY.get(luck) : Maps.newConcurrentMap();
        List<Drop> drops = map.containsKey(luckyBlock) ? map.get(luckyBlock) : Lists.newArrayList();
        drops.add(drop);
        if(!map.containsValue(drops)) map.put(luckyBlock, drops);
        if(!REGISTRY.containsValue(map)) REGISTRY.put(luck, map);
    }
}
