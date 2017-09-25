package mod.upcraftlp.playerluckyblocks.plugin;

import net.minecraft.item.ItemSword;

import java.util.Locale;

/**
 * @author UpcraftLP
 */
public class AddonLuckySword extends ItemSword {

    public AddonLuckySword(String name) {
        super(ToolMaterial.IRON);
        this.setRegistryName("lucky", name);
        String[] split = name.split("_");
        StringBuilder builder = new StringBuilder(split[0]);
        for(int i = 1; i < split.length; i++) {
            builder.append(split[i].substring(0, 1).toUpperCase(Locale.ROOT)).append(split[i].substring(1));
        }
        this.setUnlocalizedName(builder.toString());
    }
    //FIXME implement
}
