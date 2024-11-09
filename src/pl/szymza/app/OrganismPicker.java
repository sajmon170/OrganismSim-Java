package pl.szymza.app;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OrganismPicker extends JFrame {
    private static final int WINDOW_WIDTH  = 100;
    private static final int WINDOW_HEIGHT = 1000;
    private String current_organism = "Antelope";
    private class Picker extends JPanel {
        private void setCurrent(String organism) {
            current_organism = organism;
        }

        public Picker() {
            setLayout(new GridLayout(10, 1));
            var available = new ArrayList<ImageIcon>() {{
                add(IconSet.get("Antelope"));
                add(IconSet.get("Belladonna"));
                add(IconSet.get("Dandelion"));
                add(IconSet.get("Grass"));
                add(IconSet.get("Guarana"));
                add(IconSet.get("Sosnowsky"));
                add(IconSet.get("Fox"));
                add(IconSet.get("Sheep"));
                add(IconSet.get("Turtle"));
                add(IconSet.get("Wolf"));
            }};

            for (var icon: available) {
                var button = new JButton();
                button.setContentAreaFilled(false);
                button.setIcon(icon);
                button.setFocusable(false);
                button.addActionListener(
                    e -> {
                        setCurrent(icon.getDescription());
                    }
                );
                add(button);
            }
        }
    }
    private Picker content;

    public OrganismPicker() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        content = new Picker();
        add(content);
        pack();
    }

    public void display() {
        setVisible(true);
    }

    public String getCurrent() {
        return current_organism;
    }

}
