package mod.upcraftlp.playerluckyblocks.special;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mojang.authlib.GameProfile;

public class NetHandlerPlayer {

    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();
    static volatile boolean hasInternet = false;
    
    static {
        EXECUTOR.execute(new Runnable() {
            
            @Override
            public void run() {
                try {
                    hasInternet = InetAddress.getByName("minecraft.net") != null;
                } catch (UnknownHostException ignore) {}
            }
        });
    }
    
    public void doChecks(GameProfile gameProfile) {
        EXECUTOR.execute(new ThreadNetwork(gameProfile));
    }
    
}
