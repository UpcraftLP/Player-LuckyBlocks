package mod.upcraftlp.playerluckyblocks.special;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mojang.authlib.GameProfile;

import mod.upcraftlp.playerluckyblocks.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class NetHandlerPlayer {

    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();
    static volatile boolean onlineMode = false;
    
    static {
        EXECUTOR.execute(() -> {
                MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                if(server == null || server.isDedicatedServer() || !server.isServerInOnlineMode()) return;
                try {
                    onlineMode = InetAddress.getByName("minecraft.net") != null;
                } catch (UnknownHostException ignore) {}
        });
    }
    
    public void doChecks(GameProfile gameProfile) {
        EXECUTOR.execute(new ThreadNetwork(gameProfile));
    }

    public void handlePlayerCallback(EntityPlayerMP player) {
        if(this != CommonProxy.getInstance()) return;
        //TODO: loopback succeeded, enable specialties
    }
    
}
