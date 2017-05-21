package mod.upcraftlp.playerluckyblocks.crafting;

import mod.upcraftlp.playerluckyblocks.init.LuckyBlocks;
import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ShapedCrafting {

	public static void init() {
		
		//Player Lucky Block
        GameRegistry.addRecipe(new ShapedOreRecipe(LuckyBlocks.PLAYER_LUCKYBLOCK, "ggg", "ghg", "ggg", 'g', "ingotGold", 'h', new ItemStack(Blocks.SKULL, 1, OreDictionary.WILDCARD_VALUE)));

	    //Bacon Sword
        GameRegistry.addRecipe(new ShapedOreRecipe(LuckyItems.BACON_SWORD, "B", "B", "S", 'B', Items.PORKCHOP, 'S', "bone"));

	    //energy bar
        GameRegistry.addRecipe(new ShapedOreRecipe(LuckyItems.ENERGY_BAR, "SMS", "WWW", 'S', Items.SUGAR, 'M', Blocks.BROWN_MUSHROOM, 'W', "cropWheat"));
	}
}
