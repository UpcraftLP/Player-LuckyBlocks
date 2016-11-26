package mod.upcraftlp.playerluckyblocks.crafting;

import mod.upcraftlp.playerluckyblocks.init.LuckyBlocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ShapedCrafting {

	private static CraftingManager crafting = CraftingManager.getInstance();
	
	public static void init() {
		
		//Player Lucky Block
		NonNullList<ItemStack> skulls = NonNullList.create();
		Items.SKULL.getSubItems(Items.SKULL, null, skulls);
		for(ItemStack stack : skulls) {
			crafting.addRecipe(new ShapedOreRecipe(LuckyBlocks.PLAYER_LUCKYBLOCK, "ggg", "ghg", "ggg", 'g', "ingotGold", 'h', stack));
		}
	}
}
