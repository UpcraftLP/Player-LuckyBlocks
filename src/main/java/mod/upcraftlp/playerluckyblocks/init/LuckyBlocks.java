package mod.upcraftlp.playerluckyblocks.init;

import mod.upcraftlp.playerluckyblocks.blocks.BlockPlayerLuckyBlock;
import mod.upcraftlp.playerluckyblocks.blocks.tile.TileEntityPlayerLuckyBlock;
import mod.upcraftlp.playerluckyblocks.render.RenderPlayerLuckyBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class LuckyBlocks {

	public static final Block PLAYER_LUCKYBLOCK = new BlockPlayerLuckyBlock();
	
	public static void registerSpecialRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlayerLuckyBlock.class, new RenderPlayerLuckyBlock());
	}
}
