package pl.szymza.organisms.plants;
import pl.szymza.organisms.*;
import pl.szymza.math.*;
import pl.szymza.world.*;

public class Belladonna extends Plant {
    public Belladonna(Position pos, World world) {
        super("Belladonna", 99, pos, world);
    }
    public Organism createChild(Position position) {
        return new Belladonna(position, world);
    }

    @Override
    public boolean collision(Organism organism) {
        organism.die();
        die();
        return true;
    }
}