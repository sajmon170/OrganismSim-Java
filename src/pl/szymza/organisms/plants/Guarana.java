package pl.szymza.organisms.plants;
import pl.szymza.organisms.*;
import pl.szymza.math.*;
import pl.szymza.world.*;

public class Guarana extends Plant {
    public Guarana(Position pos, World world) {
        super("Guarana", 0, pos, world);
    }
    public Organism createChild(Position position) {
        return new Guarana(position, world);
    }

    @Override
    public boolean collision(Organism organism) {
        organism.increaseStrength(3);
        die();
        return true;
    }
}