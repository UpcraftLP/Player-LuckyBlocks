package mod.upcraftlp.playerluckyblocks.crafting;

import mod.upcraftlp.playerluckyblocks.init.LuckyBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ShapedCrafting {

	private static CraftingManager crafting = CraftingManager.getInstance();
	
	public static void init() {
		
		//Player Lucky Block
	    crafting.addRecipe(new ShapedOreRecipe(LuckyBlocks.PLAYER_LUCKYBLOCK, "ggg", "ghg", "ggg", 'g', "ingotGold", 'h', new ItemStack(Blocks.SKULL, 1, OreDictionary.WILDCARD_VALUE)));
	}
}
