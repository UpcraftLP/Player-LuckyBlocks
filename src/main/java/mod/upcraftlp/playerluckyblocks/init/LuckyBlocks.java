package mod.upcraftlp.playerluckyblocks.init;

import core.upcraftlp.craftdev.API.templates.Item;
import mod.upcraftlp.playerluckyblocks.blocks.BlockPlayerLuckyBlock;
import mod.upcraftlp.playerluckyblocks.blocks.tile.TileEntityPlayerLuckyBlock;
import mod.upcraftlp.playerluckyblocks.render.tile.RenderPlayerLuckyBlock;
import net.minecraft.block.Block;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LuckyBlocks {

	public static final Block PLAYER_LUCKYBLOCK = new BlockPlayerLuckyBlock();
	
	@SuppressWarnings("deprecation")
	@SideOnly(Side.CLIENT)
    public static void registerSpecialRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlayerLuckyBlock.class, new RenderPlayerLuckyBlock());
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(LuckyBlocks.PLAYER_LUCKYBLOCK), 0, TileEntityPlayerLuckyBlock.class);
	}
}
