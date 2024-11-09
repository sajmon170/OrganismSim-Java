package pl.szymza.simulation;
import pl.szymza.app.Packet;
import pl.szymza.math.Algorithms;
import pl.szymza.math.Position;
import pl.szymza.organisms.*;
import pl.szymza.organisms.animals.Human;
import pl.szymza.world.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Simulation {
    private final World world;
    private Human player;
    private final OrganismFactory spawner;
    private final String[] organisms = {
        /*Animals*/    /*Plants*/
        "Antelope",    "Belladonna",
        "Fox",         "Dandelion",
        "Sheep",       "Grass",
        "Turtle",      "Guarana",
        "Wolf",        "Sosnowsky"
    };

    private final String saveFile = "save.txt";

    private void populate(int population, int radius) {
        var available = Arrays.stream(organisms).toList();
        for (int i=0; i<population; i++) {
            Position position = Position.generate(radius);
            String species = Algorithms.pickRandom(available);
            Organism organism = spawner.spawn(species, position);
            world.add(organism, position);
        }
    }

    public Simulation(int initial_population, int radius,
                      int max_population, Position initial_position) {
        world = new World(max_population);
        spawner = new OrganismFactory(world);
        populate(initial_population, radius);
        player = new Human(initial_position, world);
        world.add(player, initial_position);
        world.advance();
    }

    public boolean isFinished() {
        return !world.organismsExist();
    }

    public int organismCount() {
        return world.organismCount();
    }
    public HashMap<Position, String> getState() {
        HashMap<Position, String> state = new HashMap<>();
        for (var entry: world.getState().entrySet())
            state.put(entry.getKey(), entry.getValue().getDisplay());

        return state;
    }
    public void run(Packet input) {
        player.setDestination(input.getPosition());
        if (player.isAlive()) {
            if (input.isActivated())
                player.activateAbility();
            player.action();
        }

        world.advance();
    }

    public void add(String name, Position position) {
        if (Objects.nonNull(position))
            world.add(spawner.spawn(name, position), position);
    }

    public ArrayList<String> getInfo() {
        return world.getHistory();
    }

    public void clearInfo() {
        world.clearHistory();
    }

    public void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));
            if (player.isAlive()) {
                writer.append(player.serialize() + "\n");
            }

            for (var entry: world.getState().entrySet()) {
                writer.append(entry.getValue().serialize() + "\n");
            }

            writer.close();
        }
        catch (IOException ex) {
            System.out.println("Could not save file.");
        }
    }

    public void load() {
        try {
            Scanner scanner = new Scanner(new File(saveFile));
            world.clear();

            String organism;
            while (scanner.hasNext()) {
                int x = Integer.parseInt(scanner.next());
                int y = Integer.parseInt(scanner.next());
                Position position = new Position(x, y);
                organism = scanner.next();

                if (!organism.equals("Human")) {
                    Organism o = spawner.spawn(organism, position);
                    world.add(o, position);
                }
                else {
                    player.setPosition(position);
                    player.setAlive();
                    world.add(player, position);
                }
            }

            world.advance();
        }
        catch (IOException ex) {
            System.out.println("Could not load file.");
        }
    }
}