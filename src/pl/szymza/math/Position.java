package pl.szymza.math;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.*;
import java.lang.Math;

public class Position {
    private int x;
    private int y;

    public static final Position EMPTY = new Position(0, 0);

    public Position(int x, int y) {
        setX(x);
        setY(y);
    }
    public Position(Position pos) {
        setX(pos.getX());
        setY(pos.getY());
    }
    // replace position with native Vector2D OR find a bijection from Z² to Z
    public int  getX()      { return x; }
    public void setX(int x) { this.x = x; }
    public int  getY()      { return y; }
    public void setY(int y) { this.y = y; }

    public Stream<Position> near(int radius) {
        var posX = Algorithms.range(x-radius, x+radius);
        var posY = Algorithms.range(y-radius, y+radius);
        var near = new ArrayList<Position>();
        for (var x: posX)
            for (var y: posY)
                near.add(new Position(x, y));
        near.remove(this);
        return near.stream();
    }
    public Stream<Position> near() {
        return near(1);
    }
    public static Position generate(int radius) {
        double angle = Math.random() * Math.PI;
        double r = Algorithms.random(0, radius);
        int x = (int)(r * Math.cos(angle));
        int y = (int)(r * Math.sin(angle));
        return new Position(x, y);
    }

    public boolean isBound(Position begin, Position end) {
        return begin.getX() <= this.getX() && this.getX() < end.getX()
            && begin.getX() <= this.getY() && this.getY() < end.getY();
    }

    public Position add(Position pos) {
        return new Position(
            this.getX() + pos.getX(),
            this.getY() + pos.getY()
        );
    }
    public Position add(int x, int y) {
        return add(new Position(x, y));
    }
    public Position sub(Position pos) {
        return new Position(
            this.getX() - pos.getX(),
            this.getY() - pos.getY()
        );
    }
    public Position sub(int x, int y) {
        return sub(new Position(x, y));
    }
    public Position mul(double factor) {
        return new Position(
            (int)(this.getX() * factor),
            (int)(this.getY() * factor)
        );
    }
    public Position div(double factor) {
        return new Position(
            (int)(this.getX() / factor),
            (int)(this.getY() / factor)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Position))
            return false;

        Position position = (Position)object;
        return this.getX() == position.getX() && this.getY() == position.getY();
    }
}
