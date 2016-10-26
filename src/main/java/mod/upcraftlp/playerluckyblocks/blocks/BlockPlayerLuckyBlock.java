package mod.upcraftlp.playerluckyblocks.blocks;

import java.util.List;
import java.util.Random;

import com.mojang.authlib.GameProfile;

import core.upcraftlp.craftdev.API.templates.Block;
import mod.upcraftlp.playerluckyblocks.Main;
import mod.upcraftlp.playerluckyblocks.API.EnumLuck;
import mod.upcraftlp.playerluckyblocks.API.EventRegistry;
import mod.upcraftlp.playerluckyblocks.API.IEventProvider;
import mod.upcraftlp.playerluckyblocks.blocks.tile.TileEntityPlayerLuckyBlock;
import mod.upcraftlp.playerluckyblocks.init.LuckyBlocks;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPlayerLuckyBlock extends Block implements ITileEntityProvider {

	public static final String KEY_LUCK = "Luck";
	public static final String KEY_OWNER = "Owner";
	
	public BlockPlayerLuckyBlock() {
		super("player_luckyblock", Material.CLAY);
		this.setHardness(0.6f);
		this.setResistance(3.0f);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		// TODO re-roll when using creative key!
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
	
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		if(worldIn.isRemote) return;
		int meta = ((TileEntityPlayerLuckyBlock) worldIn.getTileEntity(pos)).getLuck();
		EnumLuck luck = EnumLuck.rollTheDice(meta);
		IEventProvider event = EventRegistry.getEvent(luck);
		if(event != null) {
			boolean flag = (!player.capabilities.isCreativeMode);
			//HOW IT WORKS: CHECK EVERYTHING AND SET FLAG TO FALSE TO PREVENT FURTER PROCESSING
			//TODO: Silk TOUCH HANDLING
			//TODO: ITEMS WITH SPECIAL PROPERTIES TO PREVENT LUCKY BLOCKS FROM ACTIVATING BAD EVENTS (re-roll)!
			if(flag) event.execute(worldIn, pos, state, player);
		}
		else {
			Main.getLogger().println("Lucky Block at [" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + "] got null event from " + EnumLuck.class.getSimpleName() + " value \"" + luck.getName() + "\"");
		}
	}
	
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		ItemStack stack = new ItemStack(LuckyBlocks.PLAYER_LUCKYBLOCK, 1);
		NBTTagCompound nbt = new NBTTagCompound();
		TileEntityPlayerLuckyBlock te = (TileEntityPlayerLuckyBlock) world.getTileEntity(pos);
		nbt.setInteger(KEY_LUCK, te.getLuck());
		NBTTagCompound profileNBT = new NBTTagCompound();
		NBTUtil.writeGameProfile(profileNBT, te.getGameProfile());
		nbt.setTag(KEY_OWNER, profileNBT);
		stack.setTagCompound(nbt);
		return stack;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPlayerLuckyBlock();
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if(worldIn.getTileEntity(pos) instanceof TileEntityPlayerLuckyBlock) {
			TileEntityPlayerLuckyBlock te = (TileEntityPlayerLuckyBlock) worldIn.getTileEntity(pos);
			NBTTagCompound nbt = stack.getTagCompound();
			if(nbt == null) {
				nbt = new NBTTagCompound();
			}
			GameProfile gp;
			if(nbt.hasKey(KEY_OWNER)) {
				gp = NBTUtil.readGameProfileFromNBT((NBTTagCompound) nbt.getTag(KEY_OWNER));
			}
			else if(placer instanceof EntityPlayer) {
				gp = ((EntityPlayer) placer).getGameProfile();
			}
			else gp = new GameProfile(null, "Notch");
			te.setGameProfile(gp, true);
			te.setLuck(nbt.getInteger(KEY_LUCK));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger(KEY_LUCK, 0);
		ItemStack stack = new ItemStack(this);
		stack.setTagCompound(nbt);
		list.add(stack);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}
