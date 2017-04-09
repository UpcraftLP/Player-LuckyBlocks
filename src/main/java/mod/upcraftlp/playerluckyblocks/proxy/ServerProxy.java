package mod.upcraftlp.playerluckyblocks.proxy;

import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.special.NetHandlerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
@EventBusSubscriber(modid = Reference.MODID, value = {Side.SERVER})
public class ServerProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
	
	@Override
	public void serverAboutToStart(FMLServerAboutToStartEvent event) {
	    super.serverAboutToStart(event);
	}
	
	@Override
	public void unloadedWorld(FMLServerStoppedEvent event) {
	    super.unloadedWorld(event);
	}
	
	@SubscribeEvent
	public static void onPlayerJoin(PlayerEvent.LoadFromFile event) {
	    NetHandlerPlayer.doChecks(event.getEntityPlayer().getGameProfile());
	}
}
