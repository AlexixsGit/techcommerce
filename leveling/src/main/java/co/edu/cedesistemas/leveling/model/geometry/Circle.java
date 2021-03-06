package co.edu.cedesistemas.leveling.model.geometry;

public class Circle extends Ellipse implements Scalable<Circle, Double> {
    public Circle(double radius) {
        super(radius, radius);
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * a;
    }

    public double getRadius() {
        return a;
    }

    @Override
    public Circle scale(Double percentage) {
        return new Circle(getRadius() * percentage / 100);
    }

    @Override
    public String toString() {
        return "circle {radius: " + getRadius() + "}";
    }
}
