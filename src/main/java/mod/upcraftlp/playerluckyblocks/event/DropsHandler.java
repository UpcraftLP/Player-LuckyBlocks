package mod.upcraftlp.playerluckyblocks.event;

import mod.upcraftlp.playerluckyblocks.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static mod.upcraftlp.playerluckyblocks.api.LootHandler.LOOT_CLASSIFIER;

/**
 * (c)2017 UpcraftLP
 */
@Mod.EventBusSubscriber(modid = Reference.MODID)
public class DropsHandler {

    @SubscribeEvent
    public static void onEntityDrops(LivingDropsEvent event) {
        System.out.println("event called");
        EntityLivingBase entity = event.getEntityLiving();
        NBTTagCompound nbt = entity.getEntityData();
        if(nbt.hasKey(LOOT_CLASSIFIER, Constants.NBT.TAG_LIST)) {
            NBTTagList lootStacks = nbt.getTagList(LOOT_CLASSIFIER, Constants.NBT.TAG_COMPOUND);
            for(int i = 0; i < lootStacks.tagCount(); i++) {
                ItemStack stack = new ItemStack(lootStacks.getCompoundTagAt(i));
                event.getDrops().add(new EntityItem(entity.world, entity.posX, entity.posY + entity.getRNG().nextFloat() * 0.5F, entity.posZ, stack));
            }
            event.getDrops().clear();
        }
    }

}
