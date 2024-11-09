package pl.szymza.organisms;
import pl.szymza.math.*;
import pl.szymza.world.*;
import java.util.ArrayList;

public abstract class Organism {
    private Position pos;
    private int strength;
    private final int initiative;
    private final String display;
    private static int organism_count;
    protected int count;
    protected World world;
    protected boolean isAlive;

    protected void unDie() {
        isAlive = true;
    }

    protected void setId() {
        organism_count++;
        this.count = organism_count;
    }

    public Organism(String display, int strength, int initiative,
                    Position pos, World world) {
        isAlive = true;
        this.display = display;
        this.strength = strength;
        this.initiative = initiative;
        this.pos = pos;
        setId();
        this.world = world;
    }
    public Position getPosition() { return pos; }
    public void setPosition(Position pos) { this.pos = pos; }
    public int getInitiative() { return initiative; }
    public int getStrength() { return strength; }
    public void increaseStrength(int inc) { strength += inc; }
    public int getAge() { return organism_count - count; }
    public boolean isAlive() { return isAlive; }
    public boolean isDead() { return !isAlive; }

    public String serialize() {
        return getPosition().getX() + " " + getPosition().getY() + " " + getDisplay();
    }

    public ArrayList<Organism> near() {
        var near = new ArrayList<Organism>();
        getPosition().near()
                     .forEach(point -> {
                         if(world.getField(point) != null)
                             near.add(world.getField(point));
                     });
        return near;
    }

    public String getDisplay() { return display; }

    public void die() {
        isAlive = false;
        //world.removeOrganism(this);
    }

    abstract public void action();
    abstract public boolean collision(Organism organism);
    abstract public Organism createChild(Position position);
}
