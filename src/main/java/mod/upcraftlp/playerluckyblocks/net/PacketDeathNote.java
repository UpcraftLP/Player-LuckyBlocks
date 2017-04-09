package mod.upcraftlp.playerluckyblocks.net;

import io.netty.buffer.ByteBuf;
import mod.upcraftlp.playerluckyblocks.init.LuckyItems;
import mod.upcraftlp.playerluckyblocks.init.LuckyMisc.DamageSources;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketDeathNote implements IMessage, IMessageHandler<PacketDeathNote, IMessage> {

    private String playerName;
    
    public PacketDeathNote() {
        //needed to not cause an InstantiationException
    }
    
    public PacketDeathNote(String playerName) {
        this.playerName = playerName;
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        this.playerName = new PacketBuffer(buf).readString(32);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        new PacketBuffer(buf).writeString(this.playerName);
    }
    
    @Override
    public IMessage onMessage(PacketDeathNote message, MessageContext ctx) {
        EntityPlayerMP victim = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(message.playerName);
        EntityPlayerMP attacker = ctx.getServerHandler().player;
        attacker.setHeldItem(attacker.getActiveHand(), ItemStack.EMPTY);
        attacker.getCooldownTracker().setCooldown(LuckyItems.DEATH_NOTE, 200); //10 sec cooldown
        attacker.resetActiveHand();
        if(victim == null) {
            attacker.sendStatusMessage(new TextComponentTranslation("info.lucky.deathNote.notFound", message.playerName).setStyle(new Style().setColor(TextFormatting.RED)), true);
        }
        victim.attackEntityFrom(DamageSources.deathNote(attacker), Float.MAX_VALUE);
        return null;
    }

}
