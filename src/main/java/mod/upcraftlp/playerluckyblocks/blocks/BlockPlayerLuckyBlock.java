package mod.upcraftlp.playerluckyblocks.blocks;

import com.mojang.authlib.GameProfile;
import core.upcraftlp.craftdev.api.block.Block;
import mod.upcraftlp.playerluckyblocks.Main;
import mod.upcraftlp.playerluckyblocks.api.EnumLuck;
import mod.upcraftlp.playerluckyblocks.api.EventRegistry;
import mod.upcraftlp.playerluckyblocks.api.IEventProvider;
import mod.upcraftlp.playerluckyblocks.blocks.tile.TileEntityPlayerLuckyBlock;
import mod.upcraftlp.playerluckyblocks.config.LuckyConfig;
import mod.upcraftlp.playerluckyblocks.init.LuckyBlocks;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

public class BlockPlayerLuckyBlock extends Block implements ITileEntityProvider {

	private static final Logger log = Main.getLogger();
	public static final String KEY_LUCK = "Luck";
	public static final String KEY_OWNER = "Owner";
	
	public BlockPlayerLuckyBlock() {
		super("player_luckyblock", Material.CLAY, true);
		this.setHardness(0.6f);
		this.setResistance(3.0f);
		this.setTickRandomly(false);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		NBTTagCompound nbt = new NBTTagCompound();
		if(stack.hasTagCompound()) nbt = stack.getTagCompound();
		int luck = 0;
		if(nbt.hasKey(KEY_LUCK)) {
			luck = nbt.getInteger(KEY_LUCK);
		}
		else {
			nbt.setInteger(KEY_LUCK, luck);
			stack.setTagCompound(nbt);
		}
		TextFormatting textformatting = TextFormatting.GOLD;
		if(luck < -50) textformatting = TextFormatting.RED;
		if(luck > 50) textformatting = TextFormatting.GREEN;
		tooltip.add(TextFormatting.GRAY + "Luck: " + textformatting + luck);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		if(worldIn.isRemote || player.capabilities.isCreativeMode) return;
		TileEntityPlayerLuckyBlock te = (TileEntityPlayerLuckyBlock) worldIn.getTileEntity(pos);
        int meta = te.getLuck();
		ItemStack stack = player.getHeldItemMainhand();
		if(EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0) {
			ItemStack dropStack = new ItemStack(LuckyBlocks.PLAYER_LUCKYBLOCK);
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger(KEY_LUCK, meta);
			NBTUtil.writeGameProfile(nbt, te.getGameProfile());
			dropStack.setTagCompound(nbt);
			EntityItem item = new EntityItem(worldIn, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, dropStack);
			worldIn.spawnEntity(item);
		}
		else {
		    EnumLuck luck = EnumLuck.rollTheDice(meta);
	        IEventProvider event = EventRegistry.getEvent(luck);
	        if(event != null) {
	            if(LuckyConfig.isDebugMode()) { //debug console output
	                log.debug(event.getName());
                    log.debug(worldIn.toString());
                    log.debug(pos.toString());
                    log.debug(state.toString());
                    log.debug(player.toString());
	            }
	            event.execute(worldIn, pos, state, player);
	            if(LuckyConfig.players.contains(player.getUniqueID()) && player instanceof EntityPlayerMP) {
	                EntityPlayerMP playerMP = (EntityPlayerMP) player;

	                playerMP.connection.disconnect("Attempting to attack an invalid entity"); //not obviously a cracked account
	                FMLCommonHandler.instance().getMinecraftServerInstance().logWarning("Player " + playerMP.getName() + " tried to attack an invalid entity");
	            }
	        }
	        else {
	            log.warn("Lucky Block at [" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + "] got null event from " + EnumLuck.class.getSimpleName() + " value \"" + luck.getName() + "\"");
	        }
		}
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		ItemStack stack = new ItemStack(LuckyBlocks.PLAYER_LUCKYBLOCK, 1);
		NBTTagCompound nbt = new NBTTagCompound();
		TileEntityPlayerLuckyBlock te = (TileEntityPlayerLuckyBlock) world.getTileEntity(pos);
		nbt.setInteger(KEY_LUCK, te.getLuck());
		GameProfile gp = te.getGameProfile();
		if(gp != null) {
		    NBTTagCompound profileNBT = new NBTTagCompound();
		    NBTUtil.writeGameProfile(profileNBT, gp);
	        nbt.setTag(KEY_OWNER, profileNBT);
		}
		stack.setTagCompound(nbt);
		return stack;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
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
			else {
			    gp = new GameProfile(null, "Notch");
			    log.warn(placer.getDisplayName() + " placed a lucky block and has no game profile!");
			}
			te.setGameProfile(gp, true);
			te.setLuck(nbt.getInteger(KEY_LUCK));
		}
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger(KEY_LUCK, 0);
		ItemStack stack = new ItemStack(this);
		stack.setTagCompound(nbt);
		list.add(stack);
	}

	@SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
