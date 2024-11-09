package pl.szymza.organisms.animals;
import pl.szymza.organisms.*;
import pl.szymza.math.*;
import pl.szymza.world.*;

public class Human extends Animal {
    private int cooldown;
    private int counter = 5;
    private Position destination;

    public Human(Position pos, World world) {
        super("Human", 5, 4, pos, world);
        cooldown = 0;
        destination = new Position(0, 0);
    }

    public Organism createChild(Position position) {
        return new Human(position, world);
    }

    public void setDestination(Position position) {
        destination = position;
    }

    public void activateAbility() {
        if (cooldown != 0) {
           return;
        }
        cooldown = 10;
        counter = 10;
        world.getHistory().add(
            getDisplay() + " activated Alzur's Shield!"
        );
    }

    @Override
    public void action() {
        setId();

        if (!destination.equals(Position.EMPTY)) {
            move(getPosition().add(destination));
            destination = new Position(Position.EMPTY);
        }

        if (counter != 0) {
            counter--;
            if (counter == 0)
                world.getHistory().add(getDisplay() + "'s ability has worn off!");
        }

        if (counter == 0) {
            if (cooldown > 0) {
                cooldown--;
                if (cooldown == 0) {
                    world.getHistory().add(getDisplay() + " can use its ability again.");
                }
            }
        }
    }

    @Override
    public boolean collision(Organism organism) {
        if (cooldown > 0) {
            if (organism instanceof Animal) {
                Animal animal = (Animal)organism;
                animal.move(Algorithms.pickRandom(
                    getPosition().near().toList())
                );
                world.getHistory().add("-----Human evaded " + animal.getDisplay());
            }
            return false;
        }
        else
            return super.collision(organism);
    }

    public void setAlive() {
        super.unDie();
    }
}