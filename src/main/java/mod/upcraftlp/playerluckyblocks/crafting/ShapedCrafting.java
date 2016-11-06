package mod.upcraftlp.playerluckyblocks.crafting;

import java.util.ArrayList;
import java.util.List;

import mod.upcraftlp.playerluckyblocks.init.LuckyBlocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ShapedCrafting {

	private static CraftingManager crafting = CraftingManager.getInstance();
	
	public static void init() {
		
		//Player Lucky Block
		List<ItemStack> skulls = new ArrayList<ItemStack>();
		Items.SKULL.getSubItems(Items.SKULL, null, skulls);
		for(ItemStack stack : skulls) {
			crafting.addRecipe(new ShapedOreRecipe(LuckyBlocks.PLAYER_LUCKYBLOCK, "ggg", "ghg", "ggg", 'g', "ingotGold", 'h', stack));
		}
	}
}
