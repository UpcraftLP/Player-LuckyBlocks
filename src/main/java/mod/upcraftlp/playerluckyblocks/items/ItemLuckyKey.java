package mod.upcraftlp.playerluckyblocks.items;

import core.upcraftlp.craftdev.API.templates.Item;
import mod.upcraftlp.playerluckyblocks.api.EnumLuck;
import mod.upcraftlp.playerluckyblocks.blocks.tile.TileEntityPlayerLuckyBlock;
import mod.upcraftlp.playerluckyblocks.init.LuckyBlocks;
import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

import static mod.upcraftlp.playerluckyblocks.blocks.BlockPlayerLuckyBlock.KEY_LUCK;

/**
 * (c)2017 UpcraftLP
 */
public class ItemLuckyKey extends Item {

    public ItemLuckyKey() {
        super("lucky_key");
        this.setMaxStackSize(1);
    }

    public static ItemStack withLuck(int luck) {
        ItemStack stack = new ItemStack(LuckyItems.LUCKY_KEY);
        NBTTagCompound nbt = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
        nbt.setInteger(KEY_LUCK, luck);
        stack.setTagCompound(nbt);
        return stack;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote && worldIn.getBlockState(pos).getBlock() == LuckyBlocks.PLAYER_LUCKYBLOCK) {
            ItemStack stack = player.getHeldItem(hand);
            int luck = stack.hasTagCompound() ? stack.getTagCompound().getInteger(KEY_LUCK) : EnumLuck.randomLuck();
            TileEntityPlayerLuckyBlock te = (TileEntityPlayerLuckyBlock) worldIn.getTileEntity(pos);
            te.setLuck(te.getLuck() + luck); //make it actually random
            if(!player.isCreative()) player.setHeldItem(hand, ItemStack.EMPTY);
        }
        return EnumActionResult.SUCCESS;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return super.hasEffect(stack) || (stack.hasTagCompound() && stack.getTagCompound().hasKey(KEY_LUCK));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if(stack.hasTagCompound() && stack.getTagCompound().hasKey(KEY_LUCK)) {
            TextFormatting textformatting = TextFormatting.GOLD;
            int luck = stack.getTagCompound().getInteger(KEY_LUCK);
            if(luck < -50) textformatting = TextFormatting.RED;
            if(luck > 50) textformatting = TextFormatting.GREEN;
            tooltip.add(textformatting + I18n.format("tooltip.lucky.key_luck2", luck));
        }
        else tooltip.add(TextFormatting.AQUA + I18n.format("tooltip.lucky.key_luck1"));
        tooltip.add(TextFormatting.GRAY.toString() + TextFormatting.ITALIC.toString() + I18n.format("tooltip.lucky.key_luck"));
    }
}
