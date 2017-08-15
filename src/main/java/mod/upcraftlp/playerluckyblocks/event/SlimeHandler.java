package mod.upcraftlp.playerluckyblocks.event;

import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.api.LootHandler;
import mod.upcraftlp.playerluckyblocks.config.LuckyConfig;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * (c)2017 UpcraftLP
 */
@Mod.EventBusSubscriber(modid = Reference.MODID)
public class SlimeHandler {

    @SubscribeEvent
    public static void onRedstoneTrigger(BlockEvent.NeighborNotifyEvent event) {
        if (!LuckyConfig.redstoneSlimes) return;
        World world = event.getWorld();
        if (event.getState().getBlock() == Blocks.SLIME_BLOCK && world.getDifficulty() != EnumDifficulty.PEACEFUL) {
            BlockPos pos = event.getPos();
            if (!world.isRemote && world.isBlockPowered(pos)) {
                world.setBlockToAir(pos);
                EntitySlime slime = new EntitySlime(world);
                slime.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
                slime.addVelocity(0, 0.7D, 0);
                NBTTagCompound nbt = new NBTTagCompound();
                slime.writeEntityToNBT(nbt);
                nbt.setInteger("Size", 1);
                slime.readEntityFromNBT(nbt);
                LootHandler.addLoot(slime, new ItemStack(Items.SLIME_BALL, 5)); //TODO check if that can be used for infinite farms!
                world.spawnEntity(slime);
            }
        }
    }

}
