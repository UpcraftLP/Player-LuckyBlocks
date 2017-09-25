package mod.upcraftlp.playerluckyblocks.api.plugins;

import com.google.common.collect.Lists;
import mod.upcraftlp.playerluckyblocks.Main;
import mod.upcraftlp.playerluckyblocks.plugin.AddonLuckyBlock;
import mod.upcraftlp.playerluckyblocks.plugin.AddonLuckyBow;
import mod.upcraftlp.playerluckyblocks.plugin.AddonLuckySword;
import mod.upcraftlp.playerluckyblocks.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * (c)2017 UpcraftLP
 */
public class ZipPlugin {

    private static final Logger log = Main.getLogger();
    private final ZipFile file;
    private ResourceLocation LUCKYBLOCK;

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

    public void registerCraftingRecipes() { //TODO lootplusplus recipes!
        Pattern CRAFTING_MATRIX = Pattern.compile("^(.?.?.,)?(.?.?.,)?(.?.?.,)");
        if(this.hasEntry("recipes.txt")) {
            String[] recipes = this.readLines("recipes.txt");
            recipes:
            for (String recipe : recipes) {
                Matcher mMatrix = CRAFTING_MATRIX.matcher(recipe);
                if(!mMatrix.find()) {
                    log.error("Exception registering crafting recipes for " + this.getFileName() + ": \"" + recipe + "\" is no valid recipe and will be ignored!");
                    continue;
                }
                String matrix = mMatrix.group();
                recipe = recipe.replace(matrix, "");
                String[] craftMatrix = matrix.substring(0, matrix.length() - 1).split(","); //this is the craft matrix

                String[] split = recipe.split(",");
                List<Object> recipeList = Lists.newArrayList(craftMatrix);
                for (int i = 0; i < split.length; i += 2){
                    recipeList.add(split[i].charAt(0));
                    String itemText = split[i + 1];
                    Item item = PluginUtils.getItemByText(itemText);
                    int meta = 0;
                    if(item == null && itemText.contains(":")) { //item has meta
                        String[] itemTextSplit = ResourceLocation.splitObjectName(itemText);
                        item = PluginUtils.getItemByText(itemTextSplit[0]);
                        try {
                            meta = Integer.parseInt(itemTextSplit[1]);
                        }
                        catch (NumberFormatException e) { //item doesn't exist at all.
                            log.error("Exception registering crafting recipes for " + this.getFileName() + ": \"" + meta + "\" is not a valid integer!");
                            meta = 0;
                        }
                    }
                    if(item == null) {
                        log.error("Exception registering crafting recipes for " + this.getFileName() + ": \"" + itemText + "\" is not a valid item, ignoring recipe!");
                        continue recipes;
                    }
                    else recipeList.add(new ItemStack(item, 1, meta));
                }
                Item item = Item.REGISTRY.getObject(this.LUCKYBLOCK);
                assert item != null;
                GameRegistry.addRecipe(new ItemStack(item), recipeList.toArray());
                log.info("successfully registered crafting recipe for " + this.LUCKYBLOCK);
            }
        }
    }

    private static final String PLUGIN_INIT_FILE = "plugin_init.txt";

    public void pluginInit() {
        //register lucky blocks
        Enumeration<? extends ZipEntry> en = this.file.entries();
        while (en.hasMoreElements()) {
            ZipEntry entry = en.nextElement();
            String name = entry.getName();
            if(entry.isDirectory() || name.startsWith("assets")) continue; //we don't want directories or assets

            if(name.equals(PLUGIN_INIT_FILE)) {
                Scanner sc = null;
                try {
                    sc = new Scanner(this.file.getInputStream(entry));
                    while (sc.hasNextLine()) {
                        String currentLine = sc.nextLine().trim();
                        if(currentLine.isEmpty()) continue;
                        if(currentLine.startsWith("block_id=")) {
                            Block luckyBlock = new AddonLuckyBlock(currentLine.substring(9));
                            Main.proxy.registerBlock(luckyBlock);
                            this.LUCKYBLOCK = luckyBlock.getRegistryName();
                            log.warn(this.LUCKYBLOCK);
                            log.warn(currentLine.substring(9));
                        }
                        else if(currentLine.startsWith("id=")) {
                            Block luckyBlock = new AddonLuckyBlock(currentLine.substring(3));
                            Main.proxy.registerBlock(luckyBlock);
                            this.LUCKYBLOCK = luckyBlock.getRegistryName();
                            log.warn(this.LUCKYBLOCK);
                            log.warn(currentLine.substring(9));
                        }
                        else if(currentLine.startsWith("sword_id=")) { //TODO lucky bow + potions + sword
                            String swordName = currentLine.substring(9);
                            Item sword = new AddonLuckySword(swordName);
                            Main.proxy.registerItem(sword);
                        }
                        else if(currentLine.startsWith("bow_id=")) {
                            String bowName = currentLine.substring(7);
                            Item bow = new AddonLuckyBow(bowName);
                            Main.proxy.registerItem(bow);
                        } else if (currentLine.startsWith("potion_id=")) {
                            String potionName = currentLine.substring(10);
                            //TODO potions
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    IOUtils.closeQuietly(sc);
                }
                break; //luckyblock found, parse no more.
                //TODO lootplusplus stuff
                //TODO parse /config/item_additions/*
            }
        }
    }

    private static final Pattern LUCK_REGEX = Pattern.compile("@luck=[+-]?\\d+"); //matches "@luck=" + [+/-] + Integer
    private static final Pattern CHANCE_REGEX = Pattern.compile("@chance=(\\d+\\.)?\\d+"); //matches "@chance=" + Float

    public void registerDrops() {
        if(!this.hasEntry("drops.txt")) return;
        String[] drops = this.readLines("drops.txt");
        for(int i = 0; i < drops.length; i++) {
            boolean shouldBreak = false;
            StringBuilder builder = new StringBuilder();
            do {
                String line = drops[i].trim();
                if(line.endsWith("\\")) {
                    line = line.substring(0, line.length() - 1);
                    i++;
                }
                else shouldBreak = true; //stop looping after this loop
                if(!line.isEmpty() && !line.startsWith("/")) {
                    builder.append(line);
                }
            } while (!shouldBreak && i < drops.length);
            String lineString = fixBackslash(builder.toString()).trim();
            if(lineString.isEmpty()) continue;

            float chance = 0.1F;
            int luck = 0;
            Matcher mLuck = LUCK_REGEX.matcher(lineString);
            if(mLuck.find()) {
                String found = mLuck.group();
                luck = Integer.parseInt(found.substring(6));
                lineString = lineString.replace(found, "");
            }

            Matcher mChance = CHANCE_REGEX.matcher(lineString);
            if(mChance.find()) {
                String found = mChance.group();
                chance = Float.parseFloat(found.substring(8));
                lineString = lineString.replace(found, "");
            }
            DropRegistry.registerDrop(new Drop(lineString, chance), this.LUCKYBLOCK, luck);
        }
    }

    public static String fixBackslash(String string) {
        string = string.replaceAll("\\\\t", "\t");
        string = string.replaceAll("\\\\b", "\b");
        string = string.replaceAll("\\\\n", "\n");
        string = string.replaceAll("\\\\r", "\r");
        string = string.replaceAll("\\\\f", "\f");
        return string;
    }

}
