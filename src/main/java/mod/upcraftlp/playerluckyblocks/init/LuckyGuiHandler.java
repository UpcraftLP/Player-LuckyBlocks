package mod.upcraftlp.playerluckyblocks.init;

import mod.upcraftlp.playerluckyblocks.gui.GuiDeathNote;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class LuckyGuiHandler implements IGuiHandler {

    public static final int DEATHNOTE = 0;
    
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(ID) {
            case DEATHNOTE:
                return new GuiDeathNote();
            default:
                return null;
        }
    }

}
