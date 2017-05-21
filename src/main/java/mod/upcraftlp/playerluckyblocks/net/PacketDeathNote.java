package mod.upcraftlp.playerluckyblocks.net;

import io.netty.buffer.ByteBuf;
import mod.upcraftlp.playerluckyblocks.Main;
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
        EntityPlayerMP attacker = ctx.getServerHandler().player;
        ItemStack heldStack = attacker.getHeldItem(attacker.getActiveHand());
        if(heldStack.getItem() != LuckyItems.DEATH_NOTE) return null; //prevent abuse or spamming packets
        attacker.closeScreen();
        //TODO??
        //heldStack.shrink(1);
        //attacker.setHeldItem(attacker.getActiveHand(), heldStack);
        attacker.resetActiveHand();
        attacker.getCooldownTracker().setCooldown(LuckyItems.DEATH_NOTE, 200); //10 sec cooldown
        if(!message.playerName.isEmpty()) {
            EntityPlayerMP victim = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(message.playerName);
            if(victim == null) {
                attacker.sendStatusMessage(new TextComponentTranslation("info.lucky.deathNote.notFound", message.playerName).setStyle(new Style().setColor(TextFormatting.RED)), true);
                if(!attacker.isCreative()) attacker.attackEntityFrom(DamageSources.deathNote(null), attacker.getHealth() * (attacker.getRNG().nextFloat() + 0.2F));
            }
            else victim.attackEntityFrom(DamageSources.deathNote(attacker), Float.MAX_VALUE);
        }
        else {
            attacker.sendStatusMessage(new TextComponentTranslation("info.lucky.deathNote.empty", message.playerName).setStyle(new Style().setColor(TextFormatting.RED)), true);
        }

        return null;
    }

}
