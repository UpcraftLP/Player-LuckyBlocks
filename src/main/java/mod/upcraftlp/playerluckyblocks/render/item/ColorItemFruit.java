package mod.upcraftlp.playerluckyblocks.render.item;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ColorItemFruit implements IItemColor {

    private static final int[] fruit_colors = {};
    
    @Override
    public int getColorFromItemstack(ItemStack stack, int tintIndex) {
        int meta = stack.getMetadata();
        return fruit_colors.length > meta ? fruit_colors[meta] : 0;
    }

}
