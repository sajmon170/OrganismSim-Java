package pl.szymza.organisms.plants;
import pl.szymza.organisms.*;
import pl.szymza.math.*;
import pl.szymza.world.*;
import java.util.stream.*;

public class Dandelion extends Plant {
    public Dandelion(Position pos, World world) {
        super("Dandelion", 0, pos, world);
    }
    public Organism createChild(Position position) {
        return new Dandelion(position, world);
    }

    @Override
    public void action() {
        IntStream.range(0, 3).forEach(iter -> super.action());
        if (Algorithms.chance(0.3))
            die();
    }
}