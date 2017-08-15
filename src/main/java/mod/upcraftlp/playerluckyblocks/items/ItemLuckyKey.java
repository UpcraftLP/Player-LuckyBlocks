package mod.upcraftlp.playerluckyblocks.items;

import core.upcraftlp.craftdev.API.templates.Item;
import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

import static mod.upcraftlp.playerluckyblocks.blocks.BlockPlayerLuckyBlock.KEY_LUCK;

import java.util.List;

/**
 * (c)2017 UpcraftLP
 */
public class ItemLuckyKey extends Item {

    public ItemLuckyKey() {
        super("lucky_key");
    }

    public static ItemStack withLuck(int luck) {
        ItemStack stack = new ItemStack(LuckyItems.LUCKY_KEY);
        NBTTagCompound nbt = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
        nbt.setInteger(KEY_LUCK, luck);
        stack.setTagCompound(nbt);
        return stack;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return super.hasEffect(stack) || (stack.hasTagCompound() && stack.getTagCompound().hasKey(KEY_LUCK));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if(stack.hasTagCompound() && stack.getTagCompound().hasKey(KEY_LUCK)) {
            tooltip.add(TextFormatting.RED + I18n.format("tooltip.lucky.key_luck2", stack.getTagCompound().getInteger(KEY_LUCK)));
        }
        else tooltip.add(TextFormatting.AQUA + I18n.format("tooltip.lucky.key_luck1"));
        tooltip.add(TextFormatting.GRAY.toString() + TextFormatting.ITALIC.toString() + I18n.format("tooltip.lucky.key_luck"));
    }
}
