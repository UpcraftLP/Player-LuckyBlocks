package mod.upcraftlp.playerluckyblocks.api.plugins.plugindrops;

import core.upcraftlp.craftdev.API.structures.StructureLoaderSchematic;
import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.api.IEventProvider;
import mod.upcraftlp.playerluckyblocks.api.plugins.PluginUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * (c)2017 UpcraftLP
 */
public class Drop implements IEventProvider {

    private static final Pattern TYPE_REGEX = Pattern.compile("type=.*,");
    private static final Pattern FORMATTING_REGEX = Pattern.compile("\\$.");
    private static final Pattern ID_REGEX = Pattern.compile("ID=(\".*\")|.*");
    private static final Pattern AMOUNT_REGEX = Pattern.compile("amount=\\d+");
    private static final Pattern DAMAGE_REGEX = Pattern.compile("damage=\\d+");
    private static final Pattern SPECIAL_REGEX = Pattern.compile("#(rand\\(\\d+,\\d+\\))|(bPosX|Y|Z)|(randList\\(\\d+,\\d+\\)f)"); //TODO replace special stuff!

    private String raw;
    private int luck;
    private float chance;

    public Drop(String raw, int luck, float chance) {

        this.luck = luck;
        this.chance = chance;
        this.raw = raw;
    }

    public String raw() {
        return this.raw;
    }

    @Nonnull
    @Override
    public String getName() {
        return Reference.ID_PREFIX + "plugin_drop@" + this.raw;
    }

    @Override
    public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        for(String drop : this.raw.split(";")) {
            Matcher mType = TYPE_REGEX.matcher(raw);
            String typeStr = "none";
            if(mType.find()) {
                String found = mType.group();
                typeStr = found.substring(0, found.length() - 1);
            }
            Type type = Type.valueOf(typeStr.toUpperCase(Locale.ROOT)); //TODO TYPE usages

            Matcher mID = ID_REGEX.matcher(raw);
            String ID = ""; //TODO ID usages!
            if(mID.find()) {
                ID = mID.group().substring(3);
                if(ID.startsWith("\"") && ID.endsWith("\"")) ID = ID.substring(1, ID.length() - 1);
            }

            switch (type) { //FIXME parse all drop types!
                case COMMAND:
                    INetHandler handler = FMLCommonHandler.instance().getClientPlayHandler();
                    if(handler instanceof INetHandlerPlayServer) {
                        ((INetHandlerPlayServer) handler).processChatMessage(new CPacketChatMessage(ID));
                    }
                    break;
                case DIFFICULTY:
                    break;
                case EXPLOSION:
                    break;
                case ENTITY:
                    break;
                case FILL:
                    break;
                case ITEM:
                    break;
                case MESSAGE:
                    FMLCommonHandler.instance().getMinecraftServerInstance().sendMessage(new TextComponentString(ID));
                    break;
                case NONE: //item drop
                    ItemStack stack = new ItemStack(PluginUtils.getItemByText(ID)); //TODO nbt?
                    EntityItem entityItem = new EntityItem(world, pos.getX() + world.rand.nextDouble(), pos.getY() + 0.2D + world.rand.nextDouble(), pos.getY() + world.rand.nextDouble(), stack);
                    entityItem.setDefaultPickupDelay();
                    world.spawnEntity(entityItem);
                    break;
                case PARTICLE:
                    break;
                case SOUND:
                    break;
                case STRUCTURE:
                    PluginUtils.loadStructure(world, pos, ID);
                    break;
            }
        }
    }

    public static void parseDrop(String raw, World world, BlockPos pos, IBlockState state, EntityPlayer player) {

    }

    public static enum Type {
        COMMAND,
        DIFFICULTY,
        EXPLOSION,
        ENTITY,
        FILL,
        ITEM,
        MESSAGE,
        NONE,
        PARTICLE,
        SOUND,
        STRUCTURE
    }
}
