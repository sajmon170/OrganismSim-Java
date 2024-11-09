package pl.szymza.app;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import pl.szymza.math.Position;

public class SimulationView extends JFrame {
    private static final int WINDOW_WIDTH  = 1000;
    private static final int WINDOW_HEIGHT = 1000;

    private Position user_input = new Position(0, 0);
    private Position clicked;
    private boolean activate_action = false;
    private boolean will_advance = false;

    private class UI extends JPanel {
        private final Position SIZE = new Position(20, 20);
        private final Position ORIGIN = new Position(0, 0);
        private Position camera_offset = new Position(0, 0);
        private boolean was_clicked;
        private final JButton[][] display = new JButton[SIZE.getY()][SIZE.getX()];
        public Position getOrigin() { return ORIGIN; }
        public Position getViewportSize() { return SIZE; }
        public Position getOffset() { return camera_offset; }
        public void setOffset(Position offset) { camera_offset = offset; }
        private void setClicked(Position position) {
            clicked = position.sub(camera_offset);
        }

        public UI() {
            was_clicked = false;
            setLayout(new GridLayout(SIZE.getX(), SIZE.getY()));
            setKeys();

            for (int y=0; y<SIZE.getY(); y++)
                for (int x=0; x<SIZE.getX(); x++) {
                    display[y][x] = new JButton();
                    display[y][x].setContentAreaFilled(false);
                    display[y][x].setFocusable(false);

                    Position current = new Position(x, y);
                    display[y][x].addActionListener(
                        e -> {
                            setClicked(current);
                            was_clicked = true;
                        }
                    );

                    add(display[y][x]);
                }
        }

        public void setField(Position pos, String name) {
            display[pos.getY()][pos.getX()].setIcon(IconSet.get(name));
        }
        public void clear() {
            for (var line: display)
                for (var field: line)
                    field.setIcon(IconSet.get("Empty"));
        }
        public void refresh() {
            SwingUtilities.updateComponentTreeUI(this);
        }

        public boolean wasClicked() {
            if (was_clicked) {
                was_clicked = false;
                return true;
            }
            return false;
        }

        public void setKeys() {
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("W"), "moveUP");
            getActionMap().put("moveUP", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    user_input = new Position(0, -1);
                    will_advance = true;
                }
            });

            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("S"), "moveDOWN");
            getActionMap().put("moveDOWN", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    user_input = new Position(0, 1);
                    will_advance = true;
                }
            });

            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("A"), "moveLEFT");
            getActionMap().put("moveLEFT", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    user_input = new Position(-1, 0);
                    will_advance = true;
                }
            });

            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("D"), "moveRIGHT");
            getActionMap().put("moveRIGHT", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    user_input = new Position(1, 0);
                    will_advance = true;
                }
            });

            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("UP"), "cameraUP");
            getActionMap().put("cameraUP", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    setOffset(getOffset().add(0, 1));
                }
            });

            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("DOWN"), "cameraDOWN");
            getActionMap().put("cameraDOWN", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    setOffset(getOffset().add(0, -1));
                }
            });

            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("LEFT"), "cameraLEFT");
            getActionMap().put("cameraLEFT", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    setOffset(getOffset().add(1, 0));
                }
            });

            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("RIGHT"), "cameraRIGHT");
            getActionMap().put("cameraRIGHT", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    setOffset(getOffset().add(-1, 0));
                }
            });

            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(' '), "special");
            getActionMap().put("special", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    activate_action = true;
                    will_advance = true;
                }
            });
        }
    }

    private UI view;
    public SimulationView() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Symulator organizmów");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        view = new UI();
        add(view);
    }

    public boolean wasClicked() {
        return view.wasClicked();
    }
    public Position getCenter() {
        return new Position(view.getViewportSize().div(2));
    }
    public void display() {
        setVisible(true);
    }
    public void draw(HashMap<Position, String> screen) {
        view.clear();

        for (int y = 0; y < view.getViewportSize().getY(); y++)
            for (int x = 0; x < view.getViewportSize().getX(); x++) {
                Position current = new Position(x, y);
                Position offset  = current.sub(view.getOffset());
                view.setField(current, screen.get(offset));
            }

        view.refresh();
    }

    public Position getClicked() {
        return clicked;
    }
    public void resetUserInput() {
        user_input = new Position(0, 0);
        clicked = null;
        activate_action = false;
        will_advance = false;
    }
    public Packet getUserInput() {
        return new Packet(user_input, activate_action, will_advance);
    }
}