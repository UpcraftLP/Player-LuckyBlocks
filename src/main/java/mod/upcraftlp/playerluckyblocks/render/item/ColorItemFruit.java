package mod.upcraftlp.playerluckyblocks.render.item;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ColorItemFruit implements IItemColor {

    private static final int[] fruit_colors = {
            0x191919, //black       (00, death)
            0x993333, //red         (01)
            0x667F33, //green       (02, giant)
            0x664C33, //brown       (03)
            0x334CB2, //blue        (04, water)
            0x7F3FB2, //purple      (05)
            0x4C7F99, //cyan        (06)
            0x999999, //light gray  (07)
            0x4C4C4C, //gray        (08)
            0xF27FA5, //pink        (09)
            0x7FCC19, //lime        (10)
            0xE5E533, //yellow      (11)
            0x6699D8, //light blue  (12, flight)
            0xB24CD8, //magenta     (13)
            0xD87F33, //orange      (14, explosion)
            0xFFFFFF, //white       (15)
            
    };
    
    @Override
    public int getColorFromItemstack(ItemStack stack, int tintIndex) {
        int meta = stack.getMetadata();
        return fruit_colors.length > meta ? fruit_colors[meta] : 0;
    }

}
