package mod.upcraftlp.playerluckyblocks.render.entity.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Cubik Studio 2.7.405 Beta JAVA exporter
 * Designed & Illustrated by DEMMERS.
 */
public class ModelJetPack extends ModelBiped {

    //fields
    private ModelRenderer e1_Jetpackmain;
    private ModelRenderer e2_Wingbaseright;
    private ModelRenderer e3_Wingbaseleft;
    private ModelRenderer e4_Wingleft;
    private ModelRenderer e5_Wingright;
    private ModelRenderer e6_Wingtopright;
    private ModelRenderer e7_Wingtopleft;
    private ModelRenderer e8_Mainright;
    private ModelRenderer e9_Mainleft;
    private ModelRenderer e10_Mainbottom;
    private ModelRenderer e11_Wingrightsub;
    private ModelRenderer e12_Wingleftsub;
    private ModelRenderer e13_Fuelcanisterleft;
    private ModelRenderer e14_Fuelcanisterright;
    private ModelRenderer e15_Mainmodule;
    private ModelRenderer e16_Mainmodulebase;
    private ModelRenderer e17_Burnerright;
    private ModelRenderer e18_Burnerleft;

    public ModelJetPack()
    {
        textureWidth = 32;
        textureHeight = 32;

        e1_Jetpackmain = new ModelRenderer(this, 0, 25);
        e1_Jetpackmain.addBox(5F, 5F, 7.25F, 6, 6, 1);
        e1_Jetpackmain.setRotationPoint(5F, 5F, 7.25F);
        e1_Jetpackmain.setTextureSize(32, 32);
        e1_Jetpackmain.mirror = false;
        setRotation(e1_Jetpackmain, 0F, 0F, 0F);
        e2_Wingbaseright = new ModelRenderer(this, 14, 26);
        e2_Wingbaseright.addBox(10.625F, 6F, 6.625F, 3, 5, 1);
        e2_Wingbaseright.setRotationPoint(10.625F, 6F, 6.625F);
        e2_Wingbaseright.setTextureSize(32, 32);
        e2_Wingbaseright.mirror = false;
        setRotation(e2_Wingbaseright, 0F, 0.3926991F, 0F);
        e3_Wingbaseleft = new ModelRenderer(this, 22, 26);
        e3_Wingbaseleft.addBox(2.625F, 6F, 7.5F, 3, 5, 1);
        e3_Wingbaseleft.setRotationPoint(2.625F, 6F, 7.5F);
        e3_Wingbaseleft.setTextureSize(32, 32);
        e3_Wingbaseleft.mirror = false;
        setRotation(e3_Wingbaseleft, 0F, 5.890486F, 0F);
        e4_Wingleft = new ModelRenderer(this, 22, 20);
        e4_Wingleft.addBox(0.75F, 6.75F, 6.625F, 2, 4, 1);
        e4_Wingleft.setRotationPoint(0.75F, 6.75F, 6.625F);
        e4_Wingleft.setTextureSize(32, 32);
        e4_Wingleft.mirror = false;
        setRotation(e4_Wingleft, 0F, 0.3926991F, 0F);
        e5_Wingright = new ModelRenderer(this, 8, 13);
        e5_Wingright.addBox(13F, 6.75F, 7.375F, 2, 4, 1);
        e5_Wingright.setRotationPoint(13F, 6.75F, 7.375F);
        e5_Wingright.setTextureSize(32, 32);
        e5_Wingright.mirror = false;
        setRotation(e5_Wingright, 0F, 5.890486F, 0F);
        e6_Wingtopright = new ModelRenderer(this, 8, 2);
        e6_Wingtopright.addBox(13.25F, 9.625F, 7.5F, 2, 2, 1);
        e6_Wingtopright.setRotationPoint(13.25F, 9.625F, 7.5F);
        e6_Wingtopright.setTextureSize(32, 32);
        e6_Wingtopright.mirror = false;
        setRotation(e6_Wingtopright, 0F, 5.890486F, 0F);
        e7_Wingtopleft = new ModelRenderer(this, 14, 13);
        e7_Wingtopleft.addBox(1.125F, 9.625F, 6.875F, 2, 2, 1);
        e7_Wingtopleft.setRotationPoint(1.125F, 9.625F, 6.875F);
        e7_Wingtopleft.setTextureSize(32, 32);
        e7_Wingtopleft.mirror = false;
        setRotation(e7_Wingtopleft, 0F, 0.3926991F, 0F);
        e8_Mainright = new ModelRenderer(this, 0, 9);
        e8_Mainright.addBox(10.5F, 5.5F, 7F, 1, 6, 2);
        e8_Mainright.setRotationPoint(10.5F, 5.5F, 7F);
        e8_Mainright.setTextureSize(32, 32);
        e8_Mainright.mirror = false;
        setRotation(e8_Mainright, 0F, 0F, 0F);
        e9_Mainleft = new ModelRenderer(this, 0, 1);
        e9_Mainleft.addBox(4.5F, 5.5F, 7F, 1, 6, 2);
        e9_Mainleft.setRotationPoint(4.5F, 5.5F, 7F);
        e9_Mainleft.setTextureSize(32, 32);
        e9_Mainleft.mirror = false;
        setRotation(e9_Mainleft, 0F, 0F, 0F);
        e10_Mainbottom = new ModelRenderer(this, 14, 16);
        e10_Mainbottom.addBox(5.625F, 4.5F, 7.5F, 5, 1, 1);
        e10_Mainbottom.setRotationPoint(5.625F, 4.5F, 7.5F);
        e10_Mainbottom.setTextureSize(32, 32);
        e10_Mainbottom.mirror = false;
        setRotation(e10_Mainbottom, 0F, 0F, 0F);
        e11_Wingrightsub = new ModelRenderer(this, 8, 9);
        e11_Wingrightsub.addBox(14.125F, 7.5F, 8F, 2, 3, 1);
        e11_Wingrightsub.setRotationPoint(14.125F, 7.5F, 8F);
        e11_Wingrightsub.setTextureSize(32, 32);
        e11_Wingrightsub.mirror = false;
        setRotation(e11_Wingrightsub, 0F, 5.890486F, 0F);
        e12_Wingleftsub = new ModelRenderer(this, 8, 5);
        e12_Wingleftsub.addBox(-0.5F, 7.5F, 7.25F, 2, 3, 1);
        e12_Wingleftsub.setRotationPoint(-0.5F, 7.5F, 7.25F);
        e12_Wingleftsub.setTextureSize(32, 32);
        e12_Wingleftsub.mirror = false;
        setRotation(e12_Wingleftsub, 0F, 0.3926991F, 0F);
        e13_Fuelcanisterleft = new ModelRenderer(this, 8, 18);
        e13_Fuelcanisterleft.addBox(6F, 3.5F, 6.75F, 2, 6, 1);
        e13_Fuelcanisterleft.setRotationPoint(6F, 3.5F, 6.75F);
        e13_Fuelcanisterleft.setTextureSize(32, 32);
        e13_Fuelcanisterleft.mirror = false;
        setRotation(e13_Fuelcanisterleft, 0F, 0F, 0F);
        e14_Fuelcanisterright = new ModelRenderer(this, 0, 17);
        e14_Fuelcanisterright.addBox(8.5F, 3.5F, 6.75F, 2, 6, 2);
        e14_Fuelcanisterright.setRotationPoint(8.5F, 3.5F, 6.75F);
        e14_Fuelcanisterright.setTextureSize(32, 32);
        e14_Fuelcanisterright.mirror = false;
        setRotation(e14_Fuelcanisterright, 0F, 0F, 0F);
        e15_Mainmodule = new ModelRenderer(this, 20, 13);
        e15_Mainmodule.addBox(7.5F, 8.875F, 6.5F, 2, 2, 1);
        e15_Mainmodule.setRotationPoint(7.5F, 8.875F, 6.5F);
        e15_Mainmodule.setTextureSize(32, 32);
        e15_Mainmodule.mirror = false;
        setRotation(e15_Mainmodule, 0F, 0F, 5.497787F);
        e16_Mainmodulebase = new ModelRenderer(this, 14, 20);
        e16_Mainmodulebase.addBox(6.5F, 5.5F, 7F, 3, 4, 1);
        e16_Mainmodulebase.setRotationPoint(6.5F, 5.5F, 7F);
        e16_Mainmodulebase.setTextureSize(32, 32);
        e16_Mainmodulebase.mirror = false;
        setRotation(e16_Mainmodulebase, 0F, 0F, 0F);
        e17_Burnerright = new ModelRenderer(this, 14, 18);
        e17_Burnerright.addBox(8.75F, 3.375F, 6.875F, 1, 1, 1);
        e17_Burnerright.setRotationPoint(8.75F, 3.375F, 6.875F);
        e17_Burnerright.setTextureSize(32, 32);
        e17_Burnerright.mirror = false;
        setRotation(e17_Burnerright, 0F, 0F, 0F);
        e18_Burnerleft = new ModelRenderer(this, 18, 18);
        e18_Burnerleft.addBox(6.25F, 3.375F, 6.875F, 1, 1, 1);
        e18_Burnerleft.setRotationPoint(6.25F, 3.375F, 6.875F);
        e18_Burnerleft.setTextureSize(32, 32);
        e18_Burnerleft.mirror = false;
        setRotation(e18_Burnerleft, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        e1_Jetpackmain.render(f5);
        e2_Wingbaseright.render(f5);
        e3_Wingbaseleft.render(f5);
        e4_Wingleft.render(f5);
        e5_Wingright.render(f5);
        e6_Wingtopright.render(f5);
        e7_Wingtopleft.render(f5);
        e8_Mainright.render(f5);
        e9_Mainleft.render(f5);
        e10_Mainbottom.render(f5);
        e11_Wingrightsub.render(f5);
        e12_Wingleftsub.render(f5);
        e13_Fuelcanisterleft.render(f5);
        e14_Fuelcanisterright.render(f5);
        e15_Mainmodule.render(f5);
        e16_Mainmodulebase.render(f5);
        e17_Burnerright.render(f5);
        e18_Burnerleft.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
 
}