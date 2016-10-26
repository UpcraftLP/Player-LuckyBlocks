package mod.upcraftlp.playerluckyblocks.init;

import core.upcraftlp.craftdev.API.templates.CreativeTab;
import net.minecraft.item.ItemStack;

public class LuckyTabs {

	public static CreativeTab tabPlayerLucky = new CreativeTab("tabPlayerLucky", false);
	
	public static void setIcons() {
		tabPlayerLucky.setIconStack(new ItemStack(LuckyBlocks.PLAYER_LUCKYBLOCK));
	}
}
