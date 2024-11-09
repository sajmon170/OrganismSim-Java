package pl.szymza.app;
import pl.szymza.math.Position;

public final class Packet {
    private final Position position;
    private final boolean activated;
    private final boolean will_advance;

    public Packet(Position position, boolean activated, boolean will_advance) {
        this.position = position;
        this.activated = activated;
        this.will_advance = will_advance;
    }
    public Position getPosition() {
        return position;
    }
    public boolean isActivated() {
        return activated;
    }

    public boolean nextTurn() {
        return will_advance;
    }
}
