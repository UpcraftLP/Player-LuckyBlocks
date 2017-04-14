package mod.upcraftlp.playerluckyblocks.special;

import java.lang.reflect.Field;

import core.upcraftlp.craftdev.API.net.NetworkHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

/**
 * workaround to get the private instance from {@link NetworkHandler#INSTANCE}
 */
@Deprecated
public class TempNetworkHandler {

    static {
        try {
            Field f = NetworkHandler.class.getDeclaredField("INSTANCE");
            f.setAccessible(true);
            INSTANCE = (SimpleNetworkWrapper) f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Deprecated
    public static SimpleNetworkWrapper INSTANCE;
}
