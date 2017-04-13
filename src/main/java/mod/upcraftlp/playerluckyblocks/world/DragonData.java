package mod.upcraftlp.playerluckyblocks.world;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;

import com.google.common.collect.Maps;

import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.entity.EntityMiniDragon;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.phase.PhaseList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class DragonData extends WorldSavedData {

    private static final Random rand = new Random();
    private final Map<UUID, UUID> activeDragons = Maps.newHashMap();
    private final Map<UUID, Integer> inactiveDragons = Maps.newHashMap();
    private World world;
    private static final String DATA_NAME = Reference.MODID + "_DragonHandler";
    private static final String KEY_DRAGONS = "dragons";
    private static final String KEY_OWNER = "owner";
    private static final String KEY_DRAGON_COLOR = "dragon_color";
    
    public DragonData(String s) {
        super(s);
    }
    
    public DragonData() {
        this(DATA_NAME);
    }
    
    public boolean spawnDragon(EntityPlayer player, int color) {
        UUID uuid = player.getUniqueID();
        if(!activeDragons.containsKey(uuid)) {
            EntityMiniDragon dragon = new EntityMiniDragon(this.world);
            dragon.setOwner(player);
            dragon.setColor(color);
            dragon.setPositionAndUpdate(player.posX + (rand.nextDouble() * 20.0D) - 10.0D, player.posY + (rand.nextDouble() * 10.0D) - 5.0D, player.posZ  + (rand.nextDouble() * 20.0D) - 10.0D);
            dragon.getPhaseManager().setPhase(PhaseList.HOLDING_PATTERN);
            this.world.spawnEntity(dragon);
            activeDragons.put(uuid, dragon.getUniqueID());
            return true;
        }
        return false;
    }
    
    public void playerJoin(EntityPlayer player) {
        UUID uuid = player.getUniqueID();
        if(inactiveDragons.containsKey(uuid)) {
            spawnDragon(player, inactiveDragons.get(uuid));
            inactiveDragons.remove(uuid);
            this.setDirty(true);
        }
    }
    
    public static DragonData get(ICommandSender sender) {
        return get(sender.getEntityWorld());
    }
    
    public static DragonData get(Entity entity) {
        return get(entity.getEntityWorld());
    }
    
    public World getWorld() {
        return this.world;
    }
    
    public static DragonData get(World world) {
        MapStorage storage = world.getMapStorage();
        DragonData instance = (DragonData) storage.getOrLoadData(DragonData.class, DATA_NAME);
        if(instance == null) {
            instance = new DragonData();
            storage.setData(DATA_NAME, instance);
        }
        instance.setWorld(world);
        return instance;
    }
    
    public void reset() {
        for(UUID uuid : this.activeDragons.values()) {
            FMLCommonHandler.instance().getMinecraftServerInstance().getEntityFromUuid(uuid).setDead();
        }
        this.activeDragons.clear();
        this.inactiveDragons.clear();
        this.setDirty(true);
        this.world.getMapStorage().saveAllData();
    }

    private void setWorld(World world) {
        this.world = world;
        this.setDirty(true);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        //inactive dragons only!
        NBTTagList tagList = nbt.getTagList(KEY_DRAGONS, NBT.TAG_COMPOUND);
        for(int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            inactiveDragons.put(NBTUtil.getUUIDFromTag(tag.getCompoundTag(KEY_OWNER)), tag.getInteger(KEY_DRAGON_COLOR));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagList nbt = new NBTTagList();
        Iterator<Entry<UUID, UUID>> i = activeDragons.entrySet().iterator();
        
        //active dragons
        while(i.hasNext()) {
            Entry<UUID, UUID> e = i.next();
            NBTTagCompound dragonNBT = new NBTTagCompound();
            dragonNBT.setTag(KEY_OWNER, NBTUtil.createUUIDTag(e.getKey()));
            
            EntityMiniDragon dragon = (EntityMiniDragon) FMLCommonHandler.instance().getMinecraftServerInstance().getEntityFromUuid(e.getValue());
            int color = dragon.getDragonColor();
            dragonNBT.setInteger(KEY_DRAGON_COLOR, color);
            nbt.appendTag(dragonNBT);
        }
        
        //inactive dragons
        Iterator<Entry<UUID, Integer>> i2 = inactiveDragons.entrySet().iterator();
        while(i2.hasNext()) {
            Entry<UUID, Integer> e = i2.next();
            NBTTagCompound dragonNBT = new NBTTagCompound();
            dragonNBT.setTag(KEY_OWNER, NBTUtil.createUUIDTag(e.getKey()));
            dragonNBT.setInteger(KEY_DRAGON_COLOR, e.getValue());
            nbt.appendTag(dragonNBT);
        }
        
        compound.setTag(KEY_DRAGONS, nbt);
        return compound;
    }
    
}
