package mod.upcraftlp.playerluckyblocks.api;

import mod.upcraftlp.playerluckyblocks.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * (c)2017 UpcraftLP
 */
public class LootHandler { //TODO LootBags

    public static final String LOOT_CLASSIFIER = Reference.MODID + "_customloot";
    private static final String FORGE_ENTITYDATA = "ForgeData";

    /**
     * Used to add loot overrides to entities, this will <b>CANCEL</b> regular drops!
     */
    public static void addLoot(Entity entity, ItemStack... loot) {
        NBTTagList lootNBT = serializeNBT(loot);
        if(loot.length > 0) {
            NBTTagCompound nbt = entity.writeToNBT(new NBTTagCompound());
            NBTTagCompound entityData = entity.getEntityData();
            entityData.setTag(LOOT_CLASSIFIER, lootNBT);
            nbt.setTag(FORGE_ENTITYDATA, entityData);
            entity.readFromNBT(nbt);
        }
    }

    private static NBTTagList serializeNBT(ItemStack... loot) {
        NBTTagList lootNBT = new NBTTagList();
        for (ItemStack stack : loot) {
            lootNBT.appendTag(stack.serializeNBT());
        }
        return lootNBT;
    }

}
