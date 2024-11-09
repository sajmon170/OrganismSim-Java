package pl.szymza.organisms.animals;
import pl.szymza.organisms.*;
import pl.szymza.math.*;
import pl.szymza.world.*;

public class Fox extends Animal {
    public Fox(Position pos, World world) {
        super("Fox", 3, 7, pos, world);
    }
    public Organism createChild(Position position) {
        return new Fox(position, world);
    }

    @Override
    public void action() {
        var available = getPosition().near()
        .filter(position -> {
            var field = world.getField(position);
            return (field == null || field.getStrength() < this.getStrength());
        }).toList();

        if (!available.isEmpty())
            move(Algorithms.pickRandom(available));
    }
}