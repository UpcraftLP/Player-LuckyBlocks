package mod.upcraftlp.playerluckyblocks.plugin;

import static mod.upcraftlp.playerluckyblocks.blocks.BlockPlayerLuckyBlock.KEY_LUCK;

import mod.upcraftlp.playerluckyblocks.api.ILuckHandler;
import mod.upcraftlp.playerluckyblocks.api.plugins.Drop;
import mod.upcraftlp.playerluckyblocks.api.plugins.DropRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * (c)2017 UpcraftLP
 */
public class TileEntityAddonLuckyBlock extends TileEntity implements ILuckHandler {

    private int luck = 0;

    @Override
    public void setLuck(int luck) {
        this.luck = luck;
    }

    @Override
    public int getLuck() {
        return this.luck;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger(KEY_LUCK, this.luck);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.luck = compound.getInteger(KEY_LUCK);
    }

    public void activate(World world, BlockPos pos, IBlockState state, @Nullable EntityPlayer player) {
        Drop d = DropRegistry.getRandomDrop(state.getBlock().getRegistryName(), this.luck, player != null);
        if(d != null) d.execute(world, pos, state, player);
    }

}
