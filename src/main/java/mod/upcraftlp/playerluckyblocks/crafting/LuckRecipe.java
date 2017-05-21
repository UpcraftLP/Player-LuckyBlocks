package mod.upcraftlp.playerluckyblocks.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mod.upcraftlp.playerluckyblocks.blocks.BlockPlayerLuckyBlock;
import net.minecraft.block.Block;
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
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.lang3.Validate;

public class LuckRecipe implements IRecipe {
	
	private final ItemStack inputStack;
	private static final String KEY_LUCK = BlockPlayerLuckyBlock.KEY_LUCK;
	private static final Map<ItemStack, Integer> modifiers = new HashMap<ItemStack, Integer>();
	
	static {
		//postive
		modifiers.put(new ItemStack(Items.DIAMOND), 50);
		modifiers.put(new ItemStack(Items.EMERALD), 50);
		modifiers.put(new ItemStack(Items.ENCHANTED_BOOK), 30);
		modifiers.put(new ItemStack(Items.GUNPOWDER), 15);
		modifiers.put(new ItemStack(Items.ENDER_PEARL), 30);
		modifiers.put(new ItemStack(Items.REDSTONE), 1);
		modifiers.put(new ItemStack(Items.GLOWSTONE_DUST), 5);
		modifiers.put(new ItemStack(Blocks.GLOWSTONE), 20);
		modifiers.put(new ItemStack(Blocks.REDSTONE_BLOCK), 10);
		modifiers.put(new ItemStack(Blocks.DIAMOND_BLOCK), 100);
		modifiers.put(new ItemStack(Blocks.EMERALD_BLOCK), 100);
		
		//negative
		modifiers.put(new ItemStack(Items.SPIDER_EYE), -20);
		modifiers.put(new ItemStack(Items.FERMENTED_SPIDER_EYE), -40);
		modifiers.put(new ItemStack(Items.ROTTEN_FLESH), -15);
		modifiers.put(new ItemStack(Items.BONE), -10);
		modifiers.put(new ItemStack(Items.NETHER_WART), -50);
		modifiers.put(new ItemStack(Items.SLIME_BALL), -70);
		modifiers.put(new ItemStack(Blocks.HAY_BLOCK), -15);
	}

	public static LuckRecipe create(Block result) {
	    return create(new ItemStack(result));
    }

	public static LuckRecipe create(Item result) {
	    return create(new ItemStack(result));
    }

	public static LuckRecipe create(ItemStack result) {
        Validate.notNull(result);
        LuckRecipe recipe = new LuckRecipe(result);
        GameRegistry.addRecipe(recipe);
        return recipe;
    }

	private LuckRecipe(ItemStack result) {
		this.inputStack = result;
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		ItemStack output = ItemStack.EMPTY;
		int count = 0;
		NonNullList<ItemStack> inputItems = NonNullList.create();
		for(int index = 0; index < inv.getSizeInventory(); index++) {
			ItemStack stack = inv.getStackInSlot(index);
			if(!stack.isEmpty() && stack.isItemEqualIgnoreDurability(inputStack)) {
			    output = stack.copy();
                if(count++ > 1) return ItemStack.EMPTY;
            }
			else {
			    inputItems.add(stack);
            }
		}
		NBTTagCompound nbt = new NBTTagCompound();
		if(output.hasTagCompound()) {
			nbt = output.getTagCompound();
		}
		int luck = nbt.getInteger(KEY_LUCK);

        for(ItemStack stack : inputItems) {
            if(!stack.isEmpty()) luck += getLuck(stack);
        }
		luck = MathHelper.clamp(luck, -100, 100);
		nbt.setInteger(KEY_LUCK, luck);
		output.setTagCompound(nbt);
		output.setCount(1);
		return output;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
	    int count = 0;
		for(int index = 0; index < inv.getSizeInventory(); index++) {
			if(!inv.getStackInSlot(index).isEmpty() && inv.getStackInSlot(index).isItemEqualIgnoreDurability(inputStack)) {
                count++;
            }
		}
        return count == 1;
	}

    public static int getLuck(ItemStack stack) {
        int luck = 0;
        for (ItemStack stack1 : modifiers.keySet()) {
            if(stack.isItemEqualIgnoreDurability(stack1)) luck += modifiers.get(stack1);
        }
        return luck;
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
    @Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
    {
        NonNullList<ItemStack> stacks = NonNullList.create();

        for (int i = 0; i < inv.getSizeInventory(); ++i)
        {
            ItemStack itemstack = inv.getStackInSlot(i);
            stacks.add(ForgeHooks.getContainerItem(itemstack));
        }

        return stacks;
    }
	
}
