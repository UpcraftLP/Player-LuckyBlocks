package mod.upcraftlp.playerluckyblocks.init;

import core.upcraftlp.craftdev.api.creativetab.CreativeTab;
import net.minecraft.item.ItemStack;

public class LuckyTabs {

	public static final CreativeTab PLAYER_LUCKY_BLOCKS_TAB = new CreativeTab("tabPlayerLucky");
	public static final CreativeTab LUCKY_ADDONS_TAB = new CreativeTab("tabAddonLucky");

	public static void updateIcons() {
		PLAYER_LUCKY_BLOCKS_TAB.setIconStack(new ItemStack(LuckyBlocks.PLAYER_LUCKYBLOCK));
		LUCKY_ADDONS_TAB.setIconStack(new ItemStack(LuckyBlocks.PLAYER_LUCKYBLOCK));
	}

}