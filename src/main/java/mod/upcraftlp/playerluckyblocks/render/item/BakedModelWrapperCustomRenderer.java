package mod.upcraftlp.playerluckyblocks.render.item;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

public class BakedModelWrapperCustomRenderer implements IBakedModel {

    private final IBakedModel model;
    
    public BakedModelWrapperCustomRenderer(final IBakedModel modelIn) {
        this.model = modelIn;
    }
    
    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
        return this.model.getQuads(state, side, rand);
    }

    @Override
    public boolean isAmbientOcclusion() {
        return this.model.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return this.model.isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return true;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return this.model.getParticleTexture();
    }

    @Override
    @Deprecated
    public ItemCameraTransforms getItemCameraTransforms() {
        return this.model.getItemCameraTransforms();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return this.model.getOverrides();
    }

}
