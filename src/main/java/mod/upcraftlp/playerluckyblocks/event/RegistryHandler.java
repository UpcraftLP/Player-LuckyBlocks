package mod.upcraftlp.playerluckyblocks.event;

import core.upcraftlp.craftdev.API.util.RegistryUtils;
import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.init.LuckyBlocks;
import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import mod.upcraftlp.playerluckyblocks.init.LuckyPotions;
import mod.upcraftlp.playerluckyblocks.init.LuckyTabs;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber(modid = Reference.MODID)
public class RegistryHandler {

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        RegistryUtils.createRegistryEntries(Block.class, event, LuckyBlocks.class, Reference.MODID, LuckyTabs.tabPlayerLucky);
    }
    
    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        RegistryUtils.createRegistryEntries(Item.class, event, LuckyItems.class, Reference.MODID, LuckyTabs.tabPlayerLucky);
        GameRegistry.register(LuckyItems.SpecialItems.DEVILS_FRUIT);
    }
    
    @SubscribeEvent
    public static void onRegisterPotions(RegistryEvent.Register<Potion> event) {
        RegistryUtils.createRegistryEntries(Potion.class, event, LuckyPotions.class, Reference.MODID, null);
    }

}
