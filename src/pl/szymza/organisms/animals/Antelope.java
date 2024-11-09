package pl.szymza.organisms.animals;
import pl.szymza.organisms.*;
import pl.szymza.math.*;
import pl.szymza.world.*;
import java.util.Objects;
import java.util.stream.*;

public class Antelope extends Animal {
    public Antelope(Position pos, World world) {
        super("Antelope", 4, 4, pos, world);
    }
    public Organism createChild(Position position) {
        return new Antelope(position, world);
    }

    @Override
    public void action() {
        IntStream.range(0, 2).forEach(iter -> super.action());
    }
    @Override
    public boolean collision(Organism organism) {
        var available = getPosition().near().filter(
            position -> Objects.nonNull(world.getField(position))
        ).toList();

        if (Algorithms.chance(0.5) && !available.isEmpty()) {
            move(Algorithms.pickRandom(available));
            return false;
        } else
            return super.collision(organism);
    }
}