package mod.upcraftlp.playerluckyblocks.baseevents;

import java.util.List;
import java.util.Random;

import core.upcraftlp.craftdev.API.world.WorldHelper;
import mod.upcraftlp.playerluckyblocks.API.IEventProvider;
import mod.upcraftlp.playerluckyblocks.event.EnderArmorHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class EventSwapPositions implements IEventProvider {

	private static final Random random = new Random();
	
	@Override
	public String getName() {
		return "EventSwapPositions";
	}
	
	@Override
	public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		player.dismountRidingEntity();
		player.removePassengers();
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		if(server.getCurrentPlayerCount() > 1) {
			List<EntityPlayerMP> playerList = server.getPlayerList().getPlayers();
			EntityPlayer player2 = playerList.get(random.nextInt(playerList.size()));
			player2.dismountRidingEntity();
			player2.removePassengers();
			double d1 = player.posX;
			double d2 = player.posY;
			double d3 = player.posZ;
			player.setPositionAndUpdate(player2.posX, player2.posY, player2.posZ);
			player2.setPositionAndUpdate(d1, d2, d3);
			player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 0.6f + random.nextFloat() * 0.4f, random.nextFloat());
			player2.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 0.6f + random.nextFloat() * 0.4f, random.nextFloat());
			player.sendMessage(new TextComponentTranslation("info.lucky.whoosh"));
			player2.sendMessage(new TextComponentTranslation("info.lucky.whoosh"));
			player.setHealth(MathHelper.clamp(player.getHealth() * random.nextFloat(), 0.5f, player.getMaxHealth()));
			player.setHealth(MathHelper.clamp(player2.getHealth() * random.nextFloat(), 0.5f, player2.getMaxHealth()));
			
			for(int i = 0; i < 50; i++) {
			    WorldHelper.spawnParticles(world, EnumParticleTypes.PORTAL, true, player.posX, player.posY, player.posZ, EnderArmorHandler.getRandom(random), (random.nextDouble() - 0.5D) * 0.5D, EnderArmorHandler.getRandom(random));
			    WorldHelper.spawnParticles(world, EnumParticleTypes.PORTAL, true, player2.posX, player2.posY, player2.posZ, EnderArmorHandler.getRandom(random), (random.nextDouble() - 0.5D) * 0.5D, EnderArmorHandler.getRandom(random));
			}
		}
		else {
		    if (EnderArmorHandler.randomTeleport(player, 32))
            {
		        player.sendMessage(new TextComponentTranslation("info.lucky.whoosh"));
		        player.setHealth(MathHelper.clamp(player.getHealth() * random.nextFloat(), 0.5f, player.getMaxHealth()));
            }
		}
		
	}

}
