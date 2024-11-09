package pl.szymza.organisms.animals;
import pl.szymza.organisms.*;
import pl.szymza.math.*;
import pl.szymza.world.*;

public class Turtle extends Animal {
    public Turtle(Position pos, World world) {
        super("Turtle", 2, 1, pos, world);
    }
    public Organism createChild(Position position) {
        return new Turtle(position, world);
    }

    @Override
    public void action() {
        if(Algorithms.chance(0.75))
            super.action();
    }
    @Override
    public boolean collision(Organism organism) {
        if (organism.getStrength() < 5)
            return false;
        else
            return super.collision(organism);
    }
}