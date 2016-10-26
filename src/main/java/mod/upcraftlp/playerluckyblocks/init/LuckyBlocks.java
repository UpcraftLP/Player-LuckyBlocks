package mod.upcraftlp.playerluckyblocks.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mod.upcraftlp.playerluckyblocks.blocks.BlockPlayerLuckyBlock;
import mod.upcraftlp.playerluckyblocks.blocks.tile.TileEntityPlayerLuckyBlock;
import mod.upcraftlp.playerluckyblocks.render.RenderPlayerLuckyBlock;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class LuckyBlocks {

	public static Map<Block, CreativeTabs> blockMap;
	
	public static Block PLAYER_LUCKYBLOCK = new BlockPlayerLuckyBlock();
	
	public static void init() {
		blockMap = new HashMap<Block, CreativeTabs>();
		List<Block> blocks = new ArrayList<Block>();
		blocks.add(PLAYER_LUCKYBLOCK);
		for (Block block : blocks) {
			blockMap.put(block, LuckyTabs.tabPlayerLucky);
		}
	}
	
	public static void registerRenders() {
		/**TESRs**/
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlayerLuckyBlock.class, new RenderPlayerLuckyBlock());
	}
}
