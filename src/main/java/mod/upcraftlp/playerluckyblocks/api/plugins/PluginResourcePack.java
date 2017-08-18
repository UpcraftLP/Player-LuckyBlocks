package mod.upcraftlp.playerluckyblocks.api.plugins;

import com.google.common.base.Charsets;
import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import mod.upcraftlp.playerluckyblocks.Main;
import mod.upcraftlp.playerluckyblocks.Reference;
import mod.upcraftlp.playerluckyblocks.util.Utils;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.MetadataSerializer;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.compress.utils.IOUtils;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Enumeration;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * (c)2017 UpcraftLP
 */
public class PluginResourcePack implements IResourcePack {

    private final ZipFile file;
    private final Set<String> domains = Sets.newHashSet();
    private final String name;

    public PluginResourcePack(ZipFile zip) {
        this.file = zip;
        this.name = Utils.filterChars(new File(zip.getName()).getName());
        Enumeration<? extends ZipEntry> en = zip.entries();
        while (en.hasMoreElements()) {
            ZipEntry entry = en.nextElement();
            if(entry.isDirectory() && entry.getName().startsWith("assets/")) {
                String[] split = entry.getName().split("/");
                if(split.length == 3 && !domains.contains(split[1])) {
                    domains.add(split[1]);
                    Main.getLogger().info("Successfully registered resourcedomain " + split[1] + " for PluginFile " + this.getName()); //cache the resource domains for efficiency
                }
            }
        }

    }

    private InputStream getInputStreamInternal(String domain, String path) throws IOException {
        ZipEntry entry;
        if(domain == null) {
            entry = this.file.getEntry(path);
        }
        else entry = this.file.getEntry("assets/" + domain + "/" + path);
        return entry != null ? this.file.getInputStream(entry) : null;
    }

    @Override
    public InputStream getInputStream(ResourceLocation location) throws IOException {
        return getInputStreamInternal(location.getResourceDomain(), location.getResourcePath());
    }

    @Override
    public boolean resourceExists(ResourceLocation location) {
        return this.file.getEntry("assets/" + location.getResourceDomain() + "/" + location.getResourcePath()) != null;
    }

    @Override
    public Set<String> getResourceDomains() {
        return this.domains;
    }

    @Nullable
    @Override
    public <T extends IMetadataSection> T getPackMetadata(MetadataSerializer metadataSerializer, String metadataSectionName) throws IOException {
        InputStream stream = getInputStreamInternal(null, "pack.mcmeta");
        if(stream == null) { //substitute default pack.mcmeta
            Main.getLogger().warn("pack.mcmeta file missing for " + this.getPackName() + ", substituting default one.");
            stream = new ByteArrayInputStream(("{\n" +
                    " \"pack\": {\n" +
                    "   \"description\": \"dummy Plugin pack info for " + this.getName() + "\",\n" +
                    "   \"pack_format\": 2\n" +
                    "}\n" +
                    "}").getBytes(Charsets.UTF_8));
        }

        JsonObject jsonobject;
        BufferedReader bufferedreader = null;
        try
        {
            bufferedreader = new BufferedReader(new InputStreamReader(stream, Charsets.UTF_8));
            jsonobject = new JsonParser().parse(bufferedreader).getAsJsonObject();
        }
        catch (RuntimeException runtimeexception)
        {
            throw new JsonParseException(runtimeexception);
        }
        finally
        {
            IOUtils.closeQuietly(bufferedreader);
        }
        return metadataSerializer.parseMetadataSection(metadataSectionName, jsonobject);
    }

    @Override
    public BufferedImage getPackImage() throws IOException {
        InputStream stream = getInputStreamInternal(null, "pack.png");
        return stream == null ? null : ImageIO.read(stream);
    }

    private String getName() {
        return this.name;
    }

    @Override
    public String getPackName() {
        return Reference.MODID + ":PluginAdapter:" + this.getName();
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

    private void close() {
        IOUtils.closeQuietly(this.file);
    }


}
