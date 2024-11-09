package pl.szymza.organisms.animals;
import pl.szymza.organisms.Organism;
import pl.szymza.math.*;
import pl.szymza.world.*;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Animal extends Organism {
    public Animal(String name, int strength, int initiative,
                  Position pos, World world) {
        super(name, strength, initiative, pos, world);
    }
    public void move(Position position) {
        var field = world.getField(position);
        if ((field == null) || (field.collision(this) && field.isDead())) {
            setPosition(position);
        }
    }
    @Override
    public void action() {
        var position = Algorithms.pickRandom(getPosition().near().toList());
        move(position);
    }
    @Override
    public boolean collision(Organism organism) {
        if (organism.getClass() == this.getClass() && organism.getAge() != this.getAge()) {
            offspring((Animal)organism);
        }

        if (organism.getStrength() < this.getStrength()) {
            organism.die();
            world.getHistory().add(
                organism.getDisplay() + " was killed by " + getDisplay()
            );
        }
        else {
            this.die();
            world.getHistory().add(
                getDisplay() + " was killed by " + organism.getDisplay()
            );
        }

        return true;
    }

    public void offspring(Animal parent) {
        var position = Algorithms.pickRandom(
            Stream.concat(this.getPosition().near(), parent.getPosition().near())
                  .filter(pos -> pos != this.getPosition()
                              && pos != parent.getPosition())
                  .collect(Collectors.toList())
        );
        world.add(createChild(position), position);
        //world.getHistory().add("A new " + getDisplay() + " offspring has been made.");
    }
}
