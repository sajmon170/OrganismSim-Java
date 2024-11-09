package pl.szymza.organisms.plants;
import pl.szymza.organisms.Organism;
import pl.szymza.math.*;
import pl.szymza.world.*;

public abstract class Plant extends Organism {
    public Plant(String name, int strength, Position pos, World world) {
        super(name, strength, 0, pos, world);
    }
    private void offspring() {
        var position = Algorithms.pickRandom(getPosition().near().toList());
        world.add(createChild(position), position);
    }
    @Override
    public void action() {
        if (Algorithms.chance(0.25)) {
            //world.getHistory().add(getDisplay() + " made an offspring.");
            offspring();
        }
    }
    @Override
    public boolean collision(Organism organism) {
        world.getHistory().add(organism.getDisplay() + " ate " + getDisplay());
        die();
        return true;
    }
}
