package mod.upcraftlp.playerluckyblocks.crafting;

import mod.upcraftlp.playerluckyblocks.init.LuckyBlocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class LuckCrafting {

	public static void init() {
		LuckRecipe.create(LuckyBlocks.PLAYER_LUCKYBLOCK);
	}
	
}
