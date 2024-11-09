package pl.szymza.organisms.plants;
import pl.szymza.organisms.*;
import pl.szymza.math.*;
import pl.szymza.world.*;

public class Sosnowsky extends Plant {
    public Sosnowsky(Position pos, World world) {
        super("Sosnowsky", 10, pos, world);
    }
    public Organism createChild(Position position) {
        return new Sosnowsky(position, world);
    }

    @Override
    public void action() {
        for (var organism: near())
            organism.die();
        super.action();
    }
}