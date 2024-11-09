package pl.szymza.organisms.animals;
import pl.szymza.organisms.*;
import pl.szymza.math.*;
import pl.szymza.world.*;

public class Wolf extends Animal {
    public Wolf(Position pos, World world) {
        super("Wolf", 9, 5, pos, world);
    }
    public Organism createChild(Position position) {
        return new Wolf(position, world);
    }
}
