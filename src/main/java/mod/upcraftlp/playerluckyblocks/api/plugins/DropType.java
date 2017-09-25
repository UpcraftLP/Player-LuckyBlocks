package mod.upcraftlp.playerluckyblocks.api.plugins;

import net.minecraft.util.IStringSerializable;

/**
 * @author UpcraftLP
 */
public enum DropType implements IStringSerializable {
    BLOCK("block"),
    COMMAND("command"),
    DIFFICULTY("difficulty"),
    EFFECT("effect"),
    ENTITY("entity"),
    EXPLOSION("explosion"),
    FILL("fill"),
    ITEM("item"),
    MESSAGE("message"),
    NONE("none"),
    PARTICLE("particle"),
    SOUND("sound"),
    STRUCTURE("structure");

    DropType(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String getName() {
        return name;
    }

    public static DropType byName(String name) {
        for (DropType type : values()) {
            if (type.getName().equalsIgnoreCase(name.trim())) return type;
        }
        throw new IllegalArgumentException("no such enum element: " + name);
    }
}
