package mod.upcraftlp.playerluckyblocks.api.plugins;

import com.google.common.collect.Lists;
import mod.upcraftlp.playerluckyblocks.Main;
import mod.upcraftlp.playerluckyblocks.api.plugins.plugindrops.Drop;
import mod.upcraftlp.playerluckyblocks.util.Utils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;

/**
 * (c)2017 UpcraftLP
 */
public class ZipPlugin {

    private static final Logger log = Main.getLogger();
    private final ZipFile file;
    private ResourceLocation blockname;

    public ZipPlugin(ZipFile file) {
        this.file = file;
    }

    public boolean hasEntry(String entry) {
        return this.file.getEntry(entry) != null;
    }

    public String getFileName() {
        return Utils.filterChars(new File(this.file.getName()).getName());
    }

    public ZipFile getFile() {
        return this.file;
    }

    public String[] readLines(String fileName) {
        List<String> ret = Lists.newArrayList();
        if(this.hasEntry(fileName)) {
            InputStream inputStream = null;
            BufferedReader reader = null;
            try {
                inputStream = this.file.getInputStream(this.file.getEntry(fileName));
                reader = new BufferedReader(new InputStreamReader(inputStream));
                Iterator<String> iterator = reader.lines().iterator();
                while (iterator.hasNext()) {
                    String line = iterator.next();
                    if(!line.startsWith("/") && !line.isEmpty()) ret.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                IOUtils.closeQuietly(reader);
                IOUtils.closeQuietly(inputStream);
            }
        }
        return ret.toArray(new String[ret.size()]);
    }

    public void registerCraftingRecipes() {
        if(this.hasEntry("recipes.txt")) {
            String[] recipes = this.readLines("recipes.txt");
            for(String recipe : recipes) {
                String[] parts = recipe.split(",");
                Item output = PluginUtils.getItemByText(parts[parts.length - 1]);
                if(output == null) {
                    log.error("Exception registering crafting recipes for " + this.getFileName() + ": " + parts[parts.length - 1] + " is no valid Item/Block, ignoring recipe!");
                    continue;
                }
                Object[] recipeData = new String[parts.length - 4];
                System.arraycopy(parts, 3, recipeData, 0, recipeData.length);
                GameRegistry.addRecipe(new ItemStack(output), recipeData);
                log.info("successfully registered crafting recipe for " + parts[parts.length - 1]);
            }
        }
    }

    public void registerBlocks() {
        //TODO parse /config/block_additions/*
        this.blockname = null; //FIXME get this plugin's lucky block and save it!
    }

    public void registerItems() {
        //TODO parse /config/item_additions/*
        //see PluginUtils$Registries!
    }

    private static final Pattern LUCK_REGEX = Pattern.compile("@luck=\\d+"); //matches "@luck=" + Integer
    private static final Pattern CHANCE_REGEX = Pattern.compile("@chance=(\\d+\\.)?\\d+"); //matches "@chance=" + Float
    private static final Pattern GROUP_REGEX = Pattern.compile("^group\\(.*\\)$"); //matches <line start> + "group(" + <string> + ")" + <line end>

    public void registerDrops() {
        if(!this.hasEntry("drops.txt")) return;
        String[] drops = this.readLines("drops.txt");
        for(String drop : drops) {
            float chance = 0.0F;
            int luck = 0;
            Matcher mLuck = LUCK_REGEX.matcher(drop);
            if(mLuck.find()) {
                String found = mLuck.group();
                luck = Integer.parseInt(found.substring(6));
                drop = drop.replace(found, "");
            }

            Matcher mChance = CHANCE_REGEX.matcher(drop);
            if(mChance.find()) {
                String found = mChance.group();
                chance = Float.parseFloat(found.substring(8));
                drop = drop.replace(found, "");
            }

            Matcher mGroup = GROUP_REGEX.matcher(drop);
            if(mGroup.find()) {
                String found = mGroup.group();
                drop = found.substring(6, found.length() - 1);
            }
            registerDrop(drop, luck, chance);
        }
    }

    public void registerDrop(String raw, int luck, float chance) {
        Drop d = new Drop(raw, luck, chance); //TODO register drop
    }
}
