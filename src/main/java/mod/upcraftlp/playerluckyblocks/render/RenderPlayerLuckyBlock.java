package mod.upcraftlp.playerluckyblocks.render;

import java.util.Map;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

import mod.upcraftlp.playerluckyblocks.blocks.tile.TileEntityPlayerLuckyBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelHumanoidHead;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class RenderPlayerLuckyBlock extends TileEntitySpecialRenderer<TileEntityPlayerLuckyBlock>{
	
	private final ModelHumanoidHead head = new ModelHumanoidHead();
	private ResourceLocation resourcelocation;
	
	@Override
	public void renderTileEntityAt(TileEntityPlayerLuckyBlock te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		GameProfile profile = te.getGameProfile();
		Minecraft mc = Minecraft.getMinecraft();
		if(profile == null) profile = mc.player.getGameProfile();
		
		SkinManager manager = mc.getSkinManager();
		Map<Type, MinecraftProfileTexture> skinmap = manager.loadSkinFromCache(profile);
		if(skinmap != null && skinmap.get(Type.SKIN) != null) {
			this.resourcelocation = manager.loadSkin(skinmap.get(Type.SKIN), Type.SKIN);
		}
		else this.resourcelocation = DefaultPlayerSkin.getDefaultSkinLegacy();
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
		if(destroyStage >= 0) {
			this.bindTexture(DESTROY_STAGES[destroyStage]);
			 GlStateManager.matrixMode(5890);
	         GlStateManager.pushMatrix();
	         GlStateManager.scale(4.0F, 2.0F, 1.0F);
	         GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
	         GlStateManager.matrixMode(5888);
		}
		else
		{
			this.renderPlayerHead(x, y, z, EnumFacing.NORTH, destroyStage);
			this.renderPlayerHead(x, y, z, EnumFacing.EAST, destroyStage);
			this.renderPlayerHead(x, y, z, EnumFacing.SOUTH, destroyStage);
			this.renderPlayerHead(x, y, z, EnumFacing.WEST, destroyStage);
		}
	}
	
	public void renderPlayerHead(double x, double y, double z, EnumFacing facing, int destroyStage) {
		ModelBase modelbase = this.head;
		
		this.bindTexture(this.resourcelocation);
		GlStateManager.pushMatrix();
		GlStateManager.disableCull();
		
		float f;
		switch (facing)
        {
            case NORTH:
                GlStateManager.translate(x + 0.5F, y + 0.25F, z + 0.78F);
                f = 180.0f;
                break;
            case SOUTH:
                GlStateManager.translate(x + 0.5F, y + 0.25F, z + 0.22F);
                f = 0.0F;
                break;
            case WEST:
                GlStateManager.translate(x + 0.78F, y + 0.25F, z + 0.5F);
                f = 90.0F;
                break;
            case EAST:
            default:
                GlStateManager.translate(x + 0.22F, y + 0.25F, z + 0.5F);
                f = 270.0F;
        }
		GlStateManager.enableRescaleNormal();
	    GlStateManager.scale(-1.0F, -1.0F, 1.0F);
	    GlStateManager.enableAlpha();
	    GlStateManager.enableBlendProfile(GlStateManager.Profile.PLAYER_SKIN);
		//RENDER!
	    modelbase.render((Entity)null, 0.0f, 0.0F, 0.0F, f, 0.0F, 0.0625F);
		
		GlStateManager.popMatrix();
		 if (destroyStage >= 0)
	        {
	            GlStateManager.matrixMode(5890);
	            GlStateManager.popMatrix();
	            GlStateManager.matrixMode(5888);
	        }
	}
	
}
