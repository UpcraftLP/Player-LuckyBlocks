package mod.upcraftlp.playerluckyblocks.proxy;

import core.upcraftlp.craftdev.api.net.NetworkHandler;
import mod.upcraftlp.playerluckyblocks.Main;
import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.api.plugins.LegacyPluginAdapter;
import mod.upcraftlp.playerluckyblocks.blocks.tile.TileEntityPlayerLuckyBlock;
import mod.upcraftlp.playerluckyblocks.config.LuckyConfig;
import mod.upcraftlp.playerluckyblocks.crafting.LuckCrafting;
import mod.upcraftlp.playerluckyblocks.crafting.ShapedCrafting;
import mod.upcraftlp.playerluckyblocks.init.LuckyEvents;
import mod.upcraftlp.playerluckyblocks.init.LuckyGuiHandler;
import mod.upcraftlp.playerluckyblocks.init.LuckyModCompat;
import mod.upcraftlp.playerluckyblocks.init.LuckyTabs;
import mod.upcraftlp.playerluckyblocks.net.PacketDeathNote;
import mod.upcraftlp.playerluckyblocks.net.PacketUnlock;
import mod.upcraftlp.playerluckyblocks.plugin.TileEntityAddonLuckyBlock;
import mod.upcraftlp.playerluckyblocks.special.NetHandlerPlayer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {

    protected static final NetHandlerPlayer instance = new NetHandlerPlayer();
    
	public void preInit(FMLPreInitializationEvent event) {
		LuckyConfig.init(event);
		LuckyEvents.initHandlers(event.getSide());
		LuckyEvents.initEvents();
		LuckyEvents.initDrops();
		LuckyEvents.initStructures();
	}
	
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new LuckyGuiHandler());
		NetworkHandler.registerPacket(PacketDeathNote.class, PacketDeathNote.class, Side.SERVER);
		NetworkHandler.registerPacket(PacketUnlock.class, PacketUnlock.class, Side.SERVER);
		//TODO: Crafting!
		ShapedCrafting.init();
		LuckCrafting.init();
		LegacyPluginAdapter.initCrafting();

		GameRegistry.registerTileEntity(TileEntityPlayerLuckyBlock.class, Reference.MODID + "_luckyBlock");
		GameRegistry.registerTileEntity(TileEntityAddonLuckyBlock.class, Reference.MODID + "_addonLuckyBlock");

		LuckyTabs.updateIcons();
	}

	public void postInit(FMLPostInitializationEvent event) {
		LuckyModCompat.init();
	}

    public void serverAboutToStart(FMLServerAboutToStartEvent event) {
        
    }
    
    public void unloadedWorld(FMLServerStoppedEvent event) {
        
    }

    public boolean isLocal() {
        return false;
    }
    
    public static NetHandlerPlayer getInstance() {
        return instance;
    }

	/**
	 * register a lucky block or a block added through the lootplusplus addon generator
	 *
	 * this method will automatically generate an itemblock and model for the block.
	 * INTERNAL USE <b>ONLY</b>
	 * @param block the block to be registered.
	 */
	public void registerBlock(Block block) {
		GameRegistry.register(block);
		block.setCreativeTab(LuckyTabs.LUCKY_ADDONS_TAB);
		GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName().substring(5)));
    }

    public void registerItem(Item item) {
		GameRegistry.register(item);
		item.setCreativeTab(LuckyTabs.LUCKY_ADDONS_TAB);
    }
}
