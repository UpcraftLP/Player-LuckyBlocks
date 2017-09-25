package mod.upcraftlp.playerluckyblocks.api.plugins;

import mod.upcraftlp.playerluckyblocks.Main;
import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.api.IEventProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * (c)2017 UpcraftLP
 */
public class Drop extends IForgeRegistryEntry.Impl<Drop> implements IEventProvider {

    public static IForgeRegistry<Drop> REGISTRY = GameRegistry.findRegistry(Drop.class);
    private static final Logger log = Main.getLogger();

    private static final Pattern TYPE_REGEX = Pattern.compile("^[tT](ype|YPE)=\\w+,");
    private static final Pattern ID_REGEX = Pattern.compile("ID=(\".*$|;)|([^,;]+)"); //TODO regex doesn't work correctly!
    private static final Pattern ID_NUMERIC_REGEX = Pattern.compile("id:\\d+");

    private static final Pattern AMOUNT_REGEX = Pattern.compile("amount=\\d+"); //TODO random amounts
    private static final Pattern DAMAGE_REGEX = Pattern.compile("damage=\\d+");
    private static final Pattern SPECIAL_REGEX = Pattern.compile("#(rand\\(\\d+,\\d+\\))|(bPosX|Y|Z)|(randList\\(\\d+,\\d+\\)f)"); //TODO replace special stuff!

    private static final Pattern RANDOM_REGEX = Pattern.compile("#rand\\(\\d+,\\d+\\)");


    private String raw;
    private int luck;
    private float chance;
    public final boolean requiresPlayer;

    public Drop(String raw, float chance) {
        this.chance = chance;
        this.raw = raw;
        this.requiresPlayer = false; //FIXME check if drop requires player
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
        synchronized (Drop.class) {
            if(this.raw.matches("^group\\(.*\\)$")) { //FIXME properly parse groups
                String toDrop = this.raw.substring(6, this.raw.length() - 1);
                for(String drop : toDrop.split(";")) {
                    doDrop(drop, world, pos, state, player);
                }
            }
            else doDrop(this.raw, world, pos, state, player);
        }
    }

    private static void doDrop(String drop, World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        Random random = world.rand;

        log.warn(drop);
        Matcher mRand = RANDOM_REGEX.matcher(drop);
        while(mRand.find()) {
            String found = mRand.group();
            String[] rand = found.substring(6, found.length() - 1).split(",");
            int rand0 = Integer.parseInt(rand[0]);
            int rand1 = Integer.parseInt(rand[1]);
            int randomInt = rand0 + random.nextInt(rand1 - rand0);
            drop = drop.replace(found, Integer.toString(randomInt));
        }
        log.warn(drop);

        Matcher mType = TYPE_REGEX.matcher(drop);
        String typeStr = "none";
        if(mType.find()) {
            String found = mType.group();
            typeStr = found.substring(5, found.length() - 1);
            drop = drop.replace(found, "");
        }
        DropType type = DropType.byName(typeStr);

        Matcher mID = ID_REGEX.matcher(drop);
        String ID = "";
        if(mID.find()) {
            ID = mID.group().substring(3);
            if(ID.startsWith("\"") && ID.endsWith("\"")) ID = ID.substring(1, ID.length() - 1);
        }
        //TODO preprocess NBT and replace item and block IDs with registry names

        log.warn(drop);
        log.warn(ID);

        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        switch (type) { //FIXME parse all drop types! //TODO TYPE usages
            case BLOCK:
                break;
            case COMMAND:
                server.commandManager.executeCommand(server, ID);
                break;
            case DIFFICULTY:
                server.setDifficultyForAllWorlds(EnumDifficulty.valueOf(ID.toUpperCase(Locale.ROOT)));
                break;
            case EFFECT:
                break;
            case ENTITY:
                if(ID.equals("FallingSand")) ID = "falling_block"; //TODO check for legacy names
                NBTTagCompound entityData = new NBTTagCompound(); //TODO set nbt data

                Entity e = EntityList.createEntityByIDFromName(new ResourceLocation(ID), world);
                if(e != null) {
                    NBTTagCompound entityNBT = e.writeToNBT(new NBTTagCompound());
                    entityNBT.setTag("ForgeData", entityData);
                    e.readFromNBT(entityNBT);
                    e.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
                    world.spawnEntity(e);
                }
                break;
            case EXPLOSION:
            case FILL:
            case ITEM:
            case MESSAGE:
                server.getPlayerList().sendMessage(new TextComponentString(ID.replace("$", "\u00a7")));
                break;
            case NONE: //item drop
                int amount = 1;
                Matcher mAmount = AMOUNT_REGEX.matcher(drop);
                if(mAmount.find()) {
                    amount = Integer.parseInt(mAmount.group().substring(7));
                }

                ItemStack stack = new ItemStack(PluginUtils.getItemByText(ID), amount); //TODO nbt?

                EntityItem entityItem = new EntityItem(world, pos.getX() + world.rand.nextDouble(), pos.getY() + 0.2D + world.rand.nextDouble(), pos.getZ() + world.rand.nextDouble(), stack);
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

    public int getLuck() {
        return luck;
    }

    public float getChance() {
        return chance;
    }
}
