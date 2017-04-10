package mod.upcraftlp.playerluckyblocks.world;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.google.common.collect.Maps;

import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.entity.EntityMiniDragon;
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

    private final Map<UUID, UUID> activeDragons = Maps.newHashMap();
    private final Map<UUID, Integer> inactiveDragons = Maps.newHashMap();
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
            EntityMiniDragon dragon = new EntityMiniDragon(player.world);
            dragon.setOwner(player);
            dragon.setColor(color);
            player.world.spawnEntity(dragon);
            activeDragons.put(uuid, dragon.getUniqueID());
            return true;
        }
        return false;
    }
    
    public void playerJoin(EntityPlayer player) {
        UUID uuid = player.getUniqueID();
        if(inactiveDragons.containsKey(uuid)) {
            spawnDragon(player, inactiveDragons.get(uuid));
        }
    }
    
    public static DragonData get(EntityPlayer player) {
        return get(player.getEntityWorld());
    }
    
    public static DragonData get(World world) {
        MapStorage storage = world.getMapStorage();
        DragonData instance = (DragonData) storage.getOrLoadData(DragonData.class, DATA_NAME);
        if(instance == null) {
            instance = new DragonData();
            storage.setData(DATA_NAME, instance);
        }
        return instance;
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
