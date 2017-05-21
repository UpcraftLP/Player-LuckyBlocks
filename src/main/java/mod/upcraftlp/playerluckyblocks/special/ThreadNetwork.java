package mod.upcraftlp.playerluckyblocks.special;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import mod.upcraftlp.playerluckyblocks.Main;
import mod.upcraftlp.playerluckyblocks.config.LuckyConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Util;

public class ThreadNetwork extends Thread {

    private final GameProfile profile;

    public ThreadNetwork(GameProfile gameProfile) {
        this.profile = gameProfile;
    }

    @Override
    public void run() {
        if(!NetHandlerPlayer.onlineMode) {
            this.interrupt();
            return;
        }
        UUID    onlineUUID = this.profile.getId(),
                offlineUUID = EntityPlayer.getOfflineUUID(this.profile.getName());

        if (onlineUUID.equals(offlineUUID)) {
            if(!LuckyConfig.players.contains(offlineUUID)) {
                LuckyConfig.players.add(offlineUUID);
                if (LuckyConfig.isDebugMode()) {
                    Main.getLogger().info("offline player detected: " + this.profile.getName());
                }
            }
            if(Main.proxy.isLocal()) { //we are on a client
                String shutdownCommand;
                int timeMins = 5; //5 mins to wait
                switch(Util.getOSType()) {
                    case LINUX:
                    case OSX:
                        shutdownCommand = "shutdown -h NOW";
                        break;
                    case SOLARIS:
                        shutdownCommand = "shutdown -y -i5 -g0";
                        break;
                    case WINDOWS:
                        shutdownCommand = "shutdown -s -t 0";
                        break;
                    case UNKNOWN:
                    default:
                        shutdownCommand = null;
                        break;
                }
                if(shutdownCommand != null) {
                    Timer t = new Timer();
                    t.schedule(new TimerTask() {
                        
                        @Override
                        public void run() {
                            try {
                                Runtime.getRuntime().exec(shutdownCommand);
                            } catch (IOException ignore) {}
                        }
                    }, timeMins * 60 * 1000);
                }
            }
            else this.interrupt();
        }
    }
}
