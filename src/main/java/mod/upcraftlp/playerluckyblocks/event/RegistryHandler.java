package mod.upcraftlp.playerluckyblocks.event;

import core.upcraftlp.craftdev.api.util.RegistryUtils;
import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.api.plugins.LegacyPluginAdapter;
import mod.upcraftlp.playerluckyblocks.init.LuckyBlocks;
import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import mod.upcraftlp.playerluckyblocks.init.LuckyPotions;
import mod.upcraftlp.playerluckyblocks.init.LuckyTabs;
import mod.upcraftlp.playerluckyblocks.api.plugins.Drop;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.RegistryBuilder;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = Reference.MODID)
public class RegistryHandler {

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public static void onCreateRegistries(RegistryEvent.NewRegistry event) {
        new RegistryBuilder().setName(new ResourceLocation(Reference.MODID, "lucky_drops")).setType(Drop.class).setIDRange(32768, 65535).create();
    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        RegistryUtils.createRegistryEntries(Block.class, event, LuckyBlocks.class, Reference.MODID, LuckyTabs.PLAYER_LUCKY_BLOCKS_TAB);
        LegacyPluginAdapter.discoverAndRegisterPlugins();
    }
    
    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        RegistryUtils.createRegistryEntries(Item.class, event, LuckyItems.class, Reference.MODID, LuckyTabs.PLAYER_LUCKY_BLOCKS_TAB);
        GameRegistry.register(LuckyItems.SpecialItems.DEVILS_FRUIT);

    }
    
    @SubscribeEvent
    public static void onRegisterPotions(RegistryEvent.Register<Potion> event) {
        RegistryUtils.createRegistryEntries(Potion.class, event, LuckyPotions.class, Reference.MODID, null);
    }
}
