package mod.upcraftlp.playerluckyblocks.baseevents;

import java.util.List;
import java.util.Random;

import core.upcraftlp.craftdev.API.world.WorldHelper;
import mod.upcraftlp.playerluckyblocks.API.IEventProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class EventSwapPositions implements IEventProvider {

	private static final Random random = new Random();
	
	@Override
	public String getName() {
		return "EventSwapPositions";
	}
	
	public static double getRandom() {
	    return random.nextDouble() * 2 * (random.nextInt(2) * 2 - 1);
	}

	@Override
	public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (player.isRiding()) player.dismountRidingEntity();
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		if(server.getCurrentPlayerCount() > 1) {
			List<EntityPlayerMP> playerList = server.getPlayerList().getPlayers();
			EntityPlayer player2 = playerList.get(random.nextInt(playerList.size()));
			if (player2.isRiding()) player2.dismountRidingEntity();
			player.setPositionAndUpdate(player2.posX, player2.posY, player2.posZ);
			player2.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
			world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
			world.playSound(player2, player2.posX, player2.posY, player2.posZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
			
			for(int i = 0; i < 50; i++) {
			    WorldHelper.spawnParticles(world, EnumParticleTypes.PORTAL, true, player.posX, player.posY, player.posZ, getRandom(), (random.nextDouble() - 0.5D) * 0.5D, getRandom());
			    WorldHelper.spawnParticles(world, EnumParticleTypes.PORTAL, true, player2.posX, player2.posY, player2.posZ, getRandom(), (random.nextDouble() - 0.5D) * 0.5D, getRandom());
			}
		}
		else {
			double prevPosX = player.posX;
			double prevPosY = player.posY;
            double prevPosZ = player.posZ;

            for (int i = 0; i < 16; ++i)
            {
            	double posX = player.posX + (player.getRNG().nextDouble() - 0.5D) * 16.0D;
            	double posY = MathHelper.clamp(player.posY + (double)(player.getRNG().nextInt(16) - 8), 0.0D, (double)(world.getActualHeight() - 1));
                double posZ = player.posZ + (player.getRNG().nextDouble() - 0.5D) * 16.0D;

                if (player.attemptTeleport(posX, posY, posZ))
                {
                    world.playSound(null, prevPosX, prevPosY, prevPosZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                    for(int j = 0; j < 50; j++) {
                        WorldHelper.spawnParticles(world, EnumParticleTypes.PORTAL, true, prevPosX, prevPosY, prevPosZ, getRandom(), (random.nextDouble() - 0.5D) * 0.5D, getRandom());
                        WorldHelper.spawnParticles(world, EnumParticleTypes.PORTAL, true, posX, posY, posZ, getRandom(), (random.nextDouble() - 0.5D) * 0.5D, getRandom());
                    }
                    break;
                }
            }
		}
		
	}

}
