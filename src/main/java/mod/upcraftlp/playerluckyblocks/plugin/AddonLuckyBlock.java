package mod.upcraftlp.playerluckyblocks.plugin;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Random;

/**
 * (c)2017 UpcraftLP
 */
public class AddonLuckyBlock extends Block {

    public AddonLuckyBlock(String name) {
        super(Material.CLAY);
        this.setSoundType(SoundType.STONE);
        this.setRegistryName("lucky", name);
        String[] split = name.split("_");
        StringBuilder builder = new StringBuilder(split[0]);
        for(int i = 1; i < split.length; i++) {
            builder.append(split[i].substring(0, 1).toUpperCase(Locale.ROOT)).append(split[i].substring(1));
        }
        this.setUnlocalizedName(builder.toString());
        this.setHardness(0.2F);
        this.setResistance(6000000.0F);
        this.setHarvestLevel("pickaxe", 0);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityAddonLuckyBlock();
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        triggerEvent(worldIn, pos, state, player, te);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if(worldIn.isBlockPowered(pos)) {
            triggerEvent(worldIn, pos, worldIn.getBlockState(pos), null, worldIn.getTileEntity(pos));
            worldIn.setBlockToAir(pos);
        }
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(world, pos, neighbor);

    }

    private static void triggerEvent(World world, BlockPos pos, IBlockState state, @Nullable EntityPlayer player, @Nullable TileEntity te) {
        if(!world.isRemote && te != null) {
            if(te instanceof TileEntityAddonLuckyBlock) ((TileEntityAddonLuckyBlock) te).activate(world, pos, state, player);
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if(worldIn.isBlockPowered(pos)) triggerEvent(worldIn, pos, state, null, worldIn.getTileEntity(pos));
    }
}
