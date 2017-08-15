package mod.upcraftlp.playerluckyblocks.blocks.tile;

import com.mojang.authlib.GameProfile;

import mod.upcraftlp.playerluckyblocks.blocks.BlockPlayerLuckyBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class TileEntityPlayerLuckyBlock extends TileEntity {
	
	private static final String KEY_OWNER = BlockPlayerLuckyBlock.KEY_OWNER;
	private static final String KEY_LUCK = BlockPlayerLuckyBlock.KEY_LUCK;
	private GameProfile profile;
	private int luck;
	
	public void setGameProfile(GameProfile profile, boolean markDirty) {
		if(profile.isComplete() && profile.getProperties().containsKey("textures")) {
			this.profile = profile;
		}
		else
		{
		    FMLCommonHandler.instance().getMinecraftServerInstance().getMinecraftSessionService().fillProfileProperties(profile, false);
		}
		if(markDirty) this.markDirty();
	}
	
	public GameProfile getGameProfile() {
		return this.profile;
	}
	
	public void setLuck(int luck) {
		this.luck = MathHelper.clamp(luck, -100, 100);
		this.markDirty();
	}
	
	public int getLuck() {
		return this.luck;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagCompound nbt = new NBTTagCompound();
		if(this.getGameProfile() != null) {
			NBTUtil.writeGameProfile(nbt, this.getGameProfile());
			compound.setTag(KEY_OWNER, nbt);
			compound.setInteger(KEY_LUCK, this.getLuck());
		}
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		if(compound.hasKey(KEY_OWNER)) {
			NBTTagCompound nbt = (NBTTagCompound) compound.getTag(KEY_OWNER);
			GameProfile gp = NBTUtil.readGameProfileFromNBT(nbt);
			this.setGameProfile(gp, false);
		}
		else
		{
			this.setGameProfile(Minecraft.getMinecraft().getSession().getProfile(), true);
		}
		this.luck = compound.getInteger(KEY_LUCK);
		super.readFromNBT(compound);
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.getPos(), 0, this.getUpdateTag());
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
}