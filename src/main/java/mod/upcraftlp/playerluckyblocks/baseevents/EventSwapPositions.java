package mod.upcraftlp.playerluckyblocks.baseevents;

import java.util.List;
import java.util.Random;

import core.upcraftlp.craftdev.API.client.ClientHandler;
import mod.upcraftlp.playerluckyblocks.API.IEventProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class EventSwapPositions implements IEventProvider {

	private Random random = new Random();
	
	@Override
	public String getName() {
		return "EventSwapPositions";
	}

	@Override
	public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (player.isRiding()) player.dismountRidingEntity();
		if(FMLCommonHandler.instance().getMinecraftServerInstance().getCurrentPlayerCount() > 1) {
			List<EntityPlayerMP> playerList = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers();
			EntityPlayer player2 = playerList.get(random.nextInt(playerList.size()));
			if (player2.isRiding()) player2.dismountRidingEntity();
			player.setPositionAndUpdate(player2.posX, player2.posY, player2.posZ);
			player2.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
			world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
			world.playSound(player2, player2.posX, player2.posY, player2.posZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
			ClientHandler.spawnParticles(EnumParticleTypes.PORTAL, world, true, player.getPosition(), 50, 1.0D);
			ClientHandler.spawnParticles(EnumParticleTypes.PORTAL, world, true, player2.getPosition(), 50, 1.0D);
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
                    ClientHandler.spawnParticles(EnumParticleTypes.PORTAL, world, true, prevPosX, prevPosY, prevPosZ, 50, 1.0D);
                    ClientHandler.spawnParticles(EnumParticleTypes.PORTAL, world, true, posX, posY, posZ, 50, 1.0D);
                    break;
                }
            }
		}
		
	}

}
