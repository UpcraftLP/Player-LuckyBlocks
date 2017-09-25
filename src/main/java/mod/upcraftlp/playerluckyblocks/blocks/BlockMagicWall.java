package mod.upcraftlp.playerluckyblocks.blocks;

import core.upcraftlp.craftdev.api.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

/**
 * (c)2017 UpcraftLP
 */
public class BlockMagicWall extends Block {

    public BlockMagicWall() {
        super("magic_wall", Material.GLASS, true);
        this.setBlockUnbreakable();
        this.setResistance(1.0F);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(TextFormatting.RED.toString() + I18n.format("tooltip.lucky.magic_wall_1"));
        tooltip.add(TextFormatting.DARK_GRAY.toString() + TextFormatting.ITALIC.toString() + I18n.format("tooltip.lucky.magic_wall_2"));
    }
}
