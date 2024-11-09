package pl.szymza.organisms.plants;
import pl.szymza.organisms.*;
import pl.szymza.math.*;
import pl.szymza.world.*;

public class Grass extends Plant {
    public Grass(Position pos, World world) {
        super("Grass", 0, pos, world);
    }
    public Organism createChild(Position position) {
        return new Grass(position, world);
    }
}