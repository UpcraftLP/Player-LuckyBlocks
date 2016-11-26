package mod.upcraftlp.playerluckyblocks.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mod.upcraftlp.playerluckyblocks.blocks.BlockPlayerLuckyBlock;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class LuckRecipe implements IRecipe {
	
	private ItemStack inputStack;
	private static final String KEY_LUCK = BlockPlayerLuckyBlock.KEY_LUCK;
	public static final Map<Item, Integer> modifiers = new HashMap<Item, Integer>();
	
	static {
		//postive
		modifiers.put(Items.DIAMOND, 50);
		modifiers.put(Items.EMERALD, 50);
		modifiers.put(Items.ENCHANTED_BOOK, 30);
		modifiers.put(Items.GUNPOWDER, 15);
		modifiers.put(Items.ENDER_PEARL, 30);
		modifiers.put(Items.REDSTONE, 1);
		modifiers.put(Items.GLOWSTONE_DUST, 5);
		modifiers.put(Item.getItemFromBlock(Blocks.GLOWSTONE), 20);
		modifiers.put(Item.getItemFromBlock(Blocks.REDSTONE_BLOCK), 10);
		modifiers.put(Item.getItemFromBlock(Blocks.DIAMOND_BLOCK), 100);
		modifiers.put(Item.getItemFromBlock(Blocks.EMERALD_BLOCK), 100);
		
		//negative
		modifiers.put(Items.SPIDER_EYE, -20);
		modifiers.put(Items.FERMENTED_SPIDER_EYE, -40);
		modifiers.put(Items.ROTTEN_FLESH, -15);
		modifiers.put(Items.BONE, -10);
		modifiers.put(Items.NETHER_WART, -50);
		modifiers.put(Items.SLIME_BALL, -70);
		modifiers.put(Item.getItemFromBlock(Blocks.HAY_BLOCK), -15);
	}

	public LuckRecipe(ItemStack result) {
		this.inputStack = result;
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		ItemStack output = this.inputStack.copy();
		List<ItemStack> inputItems = new ArrayList<ItemStack>();
		for(int index = 0; index < inv.getSizeInventory(); index++) {
			ItemStack stack = inv.getStackInSlot(index);
			if(stack != null) {
				if(stack.isItemEqualIgnoreDurability(inputStack)) output = stack.copy(); else inputItems.add(stack);
			}
		}
		NBTTagCompound nbt = new NBTTagCompound();
		if(output.hasTagCompound()) {
			nbt = output.getTagCompound();
		}
		int luck = nbt.getInteger(KEY_LUCK);
		luck += getLuckForItems(inputItems);
		luck = MathHelper.clamp(luck, -100, 100);
		nbt.setInteger(KEY_LUCK, luck);
		output.setTagCompound(nbt);
		output.setCount(1);
		return output;
	}
	
	public static int getLuckForItems(List<ItemStack> inputItems) {
		int luck = 0;
		for(ItemStack stack : inputItems) {
			if(modifiers.containsKey(stack.getItem())) luck += modifiers.get(stack.getItem());
		}
		return luck;	
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		for(int index = 0; index < inv.getSizeInventory(); index++) {
			if(inv.getStackInSlot(index) != null && inv.getStackInSlot(index).isItemEqualIgnoreDurability(inputStack)) return true;
		}
		return false;
	}

	@Override
	public int getRecipeSize() {
		return 9;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.inputStack;
	}

	//derived from net.minecraft.item.crafting.ShapelessRecipe
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
    {
        NonNullList<ItemStack> stacks = NonNullList.create();

        for (int i = 0; i < inv.getSizeInventory(); ++i)
        {
            ItemStack itemstack = inv.getStackInSlot(i);
            stacks.add(net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack));
        }

        return stacks;
    }
	
}
