package pl.szymza.world;
import pl.szymza.organisms.Organism;
import pl.szymza.math.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class World {
    private int max_population;
    private ArrayList<Organism> organisms;
    private ArrayList<Organism> queue;
    private Iterator<Organism> current;
    private HashMap<Position, Organism> field;
    private ArrayList<String> history;

    public World(int max_population) {
        this.max_population = max_population;
        organisms = new ArrayList<>();
        queue = new ArrayList<>();
        current = organisms.iterator();
        field = new HashMap<>();
        history = new ArrayList<>();
    }
    public Organism getField(Position position) {
        return field.get(position);
    }
    public HashMap<Position, Organism> getState() {
        return new HashMap<>(field);
    }
    public void setField(Position position, Organism organism) {
        removeField(position);
        field.put(position, organism);
    }
    public void removeField(Position position) {
        field.remove(position);
    }
    public void removeOrganism(Organism organism) {
        field.remove(organism.getPosition());
        organisms.remove(organism);
    }
    public void advance() {
        for (Organism organism: organisms) {
            if (organism.isAlive()) {
                organism.action();
            }
        }

        organisms.removeIf(organism -> organism.isDead());
        organisms.addAll(queue);

        Algorithms.insertionSort(organisms, (a, b) -> {
            if (a.getInitiative() != b.getInitiative())
                return a.getInitiative() > b.getInitiative();
            else
                return a.getAge() > b.getAge();
        });

        queue.clear();
        while (organisms.size() > max_population) {
            removeOrganism(getOldest());
        }

        field.clear();
        for (Organism organism: organisms)
            setField(organism.getPosition(), organism);
    }
    public boolean organismsExist() {
        return !organisms.isEmpty() || !queue.isEmpty();
    }
    public Organism getOldest() {
        Organism oldest = organisms.get(0);
        for (var organism: organisms)
            if (organism.getAge() > oldest.getAge())
                oldest = organism;

        return oldest;
    }

    public int organismCount() {
        return organisms.size();
    }

    public void add(Organism organism, Position position) {
        if (queue.size() < max_population)
            queue.add(organism);
        setField(position, organism);
    }

    public void forceAdd(Organism organism) {
        queue.add(organism);
    }

    public ArrayList<String> getHistory() { return history; }
    public void clearHistory() {
        if (history.size() > 50)
            history.clear();
    }
    public void clear() {
        clearHistory();
        field.clear();
        organisms.clear();
        queue.clear();
    }
}
