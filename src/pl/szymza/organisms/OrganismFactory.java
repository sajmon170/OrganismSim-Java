package pl.szymza.organisms;
import pl.szymza.math.Position;
import pl.szymza.organisms.animals.*;
import pl.szymza.organisms.plants.*;
import pl.szymza.world.World;

public class OrganismFactory {
    private final World world;
    public OrganismFactory(World world) {
        this.world = world;
    }
    public Organism spawn(String organism, Position position) {
        String name = organism.toUpperCase();
        if (name.equals("ANTELOPE"))
            return new Antelope(position, world);
        if (name.equals("FOX"))
            return new Fox(position, world);
        if (name.equals("HUMAN"))
            return new Human(position, world);
        if (name.equals("SHEEP"))
            return new Sheep(position, world);
        if (name.equals("TURTLE"))
            return new Turtle(position, world);
        if (name.equals("WOLF"))
            return new Wolf(position, world);
        if (name.equals("BELLADONNA"))
            return new Belladonna(position, world);
        if (name.equals("DANDELION"))
            return new Dandelion(position, world);
        if (name.equals("GRASS"))
            return new Grass(position, world);
        if (name.equals("GUARANA"))
            return new Guarana(position, world);
        if (name.equals("SOSNOWSKY"))
            return new Sosnowsky(position, world);
        else
            return null;
    }
}
