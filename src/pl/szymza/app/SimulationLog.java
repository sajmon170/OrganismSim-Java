package pl.szymza.app;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SimulationLog extends JFrame {
    private static final int WINDOW_WIDTH  = 300;
    private static final int WINDOW_HEIGHT = 1000;

    private boolean need_save;
    private boolean need_load;

    private class Log extends JPanel {
        private final JTextArea logging_area;
        public Log() {
            logging_area = new JTextArea();
            add(logging_area);
        }
        public void append(String text) {
            if (logging_area.getText().length() > 10000)
                logging_area.setText("");

            logging_area.setText(
                logging_area.getText() + text + "\n"
            );
        }

        public void clear() {
            logging_area.setText("");
        }
    }
    private final Log content;

    public SimulationLog() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        content = new Log();
        var scrolling = new JScrollPane(
            content,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        var save = new JButton("Save");
        save.addActionListener(e -> need_save = true);
        var load = new JButton("Load");
        load.addActionListener(e -> need_load = true);

        add(scrolling);
        add(save);
        add(load);
        pack();
    }

    public void display() {
        setVisible(true);
    }
    public void print(ArrayList<String> input) {
        content.clear();
        for (var text: input)
            content.append(text+"\n");
    }

    public boolean pressedSave() {
        boolean result = need_save;
        need_save = false;

        return result;
    }

    public boolean pressedLoad() {
        boolean result = need_load;
        need_load = false;

        return result;
    }
}
