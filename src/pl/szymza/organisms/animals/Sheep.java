package pl.szymza.organisms.animals;
import pl.szymza.organisms.*;
import pl.szymza.math.*;
import pl.szymza.world.*;

public class Sheep extends Animal {
    public Sheep(Position pos, World world) {
        super("Sheep", 4, 4, pos, world);
    }
    public Organism createChild(Position position) {
        return new Sheep(position, world);
    }
}
