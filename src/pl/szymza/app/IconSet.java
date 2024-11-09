package pl.szymza.app;

import javax.swing.*;
import java.util.HashMap;

public final class IconSet {
    private static ImageIcon makeIcon(String file) {
        return new ImageIcon(IconSet.class.getResource(
            "resources/" + file + ".png"
        ), file);
    }
    private static final HashMap<String, ImageIcon> ICONS = new HashMap<>() {{
        put("Empty", makeIcon("Empty"));
        put("Antelope", makeIcon("Antelope"));
        put("Belladonna", makeIcon("Belladonna"));
        put("Dandelion", makeIcon("Dandelion"));
        put("Fox", makeIcon("Fox"));
        put("Grass", makeIcon("Grass"));
        put("Guarana", makeIcon("Guarana"));
        put("Human", makeIcon("Human"));
        put("Sheep", makeIcon("Sheep"));
        put("Sosnowsky", makeIcon("Sosnowsky"));
        put("Turtle", makeIcon("Turtle"));
        put("Wolf", makeIcon("Wolf"));
    }};

    public static ImageIcon get(String name) {
        return ICONS.get(name);
    }
}
