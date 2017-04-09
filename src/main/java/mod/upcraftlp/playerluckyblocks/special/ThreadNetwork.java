package mod.upcraftlp.playerluckyblocks.special;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import mod.upcraftlp.playerluckyblocks.Main;
import mod.upcraftlp.playerluckyblocks.config.LuckyConfig;
import net.minecraft.entity.player.EntityPlayer;

public class ThreadNetwork extends Thread {

    private final GameProfile profile;

    public ThreadNetwork(GameProfile gameProfile) {
        this.profile = gameProfile;
    }

    @Override
    public void run() {
        UUID onlineUUID = this.profile.getId(),
                offlineUUID = EntityPlayer.getOfflineUUID(this.profile.getName());

        boolean isCracked = onlineUUID.equals(offlineUUID);
        if (isCracked && NetHandlerPlayer.hasInternet) {
            if (LuckyConfig.isDebugMode()) {
                Main.getLogger().println("offline player detected: " + this.profile.getName());
            }
            UUID uuid = this.profile.getId();
            if(!LuckyConfig.players.contains(uuid)) LuckyConfig.players.add(uuid);
            this.interrupt();
        }

    }
}
