package mod.upcraftlp.playerluckyblocks.api.plugins;

import com.google.common.collect.Lists;
import core.upcraftlp.craftdev.API.structures.StructureLoaderSchematic;
import mod.upcraftlp.playerluckyblocks.Main;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * legacy lucky block adapter for MC1.8
 * TODO more legacy version support!
 */
public class LegacyPluginAdapter {

    public static final File PLUGIN_DIR = new File("addons/lucky_block_compat");
    private static final Logger log = Main.getLogger();
    private static final List<ZipPlugin> PLUGIN_LIST = Lists.newArrayList();

    public static File getDirectoryFor(String mcversion) {
        File dir = new File(PLUGIN_DIR, mcversion);
        if(!dir.exists()) dir.mkdirs();
        return dir;
    }

    public static List<File> getAllPlugins(String mcversion) {
        File[] files = getDirectoryFor(mcversion).listFiles();
        List<File> ret = Lists.newArrayList();
        for(File f : files) {
            if(!f.isDirectory()) {
                String name = f.getName().toLowerCase(Locale.ROOT);
                if(name.endsWith(".zip") || name.endsWith(".jar")) ret.add(f);
                else log.warn("Ignoring non-zip file " + name);
            }
        }
        return ret;
    }

    @SideOnly(Side.CLIENT)
    public static void initPluginResourcePacks() {
        for (ZipPlugin plugin : PLUGIN_LIST) {
            Minecraft.getMinecraft().defaultResourcePacks.add(new PluginResourcePack(plugin.getFile()));
        }
    }

    public static void discoverPlugins() { //TODO read data from zip!
        List<File> files = getAllPlugins("1.8");
        File structureDir = new File(StructureLoaderSchematic.getStructureDir(), "lucky_block_compat");
        if(!structureDir.exists()) structureDir.mkdirs();
        for(File f : files) {
            try {
                ZipFile zip = new ZipFile(f);
                PLUGIN_LIST.add(new ZipPlugin(zip));
                Enumeration<? extends ZipEntry> en = zip.entries();
                while (en.hasMoreElements()) {
                    ZipEntry entry = en.nextElement();
                    if(entry.getName().startsWith("structures/") && !entry.isDirectory() && entry.getName().endsWith(".schematic")) {
                        File out = new File(structureDir, new File(entry.getName()).getName());
                        InputStream stream = null;
                        FileOutputStream outStream = null;
                        try {
                            if(out.createNewFile()) { //only override if the destination file doesn't exist already.
                                stream = zip.getInputStream(entry);
                                outStream = new FileOutputStream(out);
                                IOUtils.copy(stream, outStream, stream.available());
                            }
                        }
                        catch (IOException e) {
                            log.catching(e);
                        }
                        finally {
                            IOUtils.closeQuietly(outStream);
                            IOUtils.closeQuietly(stream);
                        }






                    }
                }

            }
            catch (IOException e) {
                log.catching(e);
            }
        }
    }

    public static void initCrafting() {
        for (ZipPlugin plugin : PLUGIN_LIST) {
            plugin.registerCraftingRecipes();
        }
    }

    public static void registerDrops() {
        for (ZipPlugin plugin : PLUGIN_LIST) {
            plugin.registerDrops();
        }
    }
}
