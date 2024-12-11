package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class CompositeShape extends Shape {
    private final List<Shape> children = new ArrayList<>();

    public CompositeShape() {
        super(null, 0, 0);
    }

    @Override
    public void draw(GraphicsContext gc) {
        children.forEach(child -> child.draw(gc));
    }

    @Override
    public boolean contains(double x, double y) {
        return x >= getMinX() && x <= getMaxX() &&
                y >= getMinY() && y <= getMaxY();
    }

    public void addChild(Shape shape) {
        children.add(shape);
        updatePosition();
    }

    public List<Shape> getChildren() {
        return new ArrayList<>(children);
    }

    private void updatePosition() {
        if (!children.isEmpty()) {
            x = getMinX() + (getMaxX() - getMinX()) / 2;
            y = getMinY() + (getMaxY() - getMinY()) / 2;
        }
    }

    double getMinX() {
        return children.stream().mapToDouble(Shape::getX).min().orElse(0);
    }

    double getMinY() {
        return children.stream().mapToDouble(Shape::getY).min().orElse(0);
    }

    double getMaxX() {
        return children.stream().mapToDouble(Shape::getX).max().orElse(0);
    }

    double getMaxY() {
        return children.stream().mapToDouble(Shape::getY).max().orElse(0);
    }



    @Override
    public void setPosition(double newX, double newY) {
        double dx = newX - x;
        double dy = newY - y;
        children.forEach(child ->
                child.setPosition(child.getX() + dx, child.getY() + dy));
        x = newX;
        y = newY;
    }

    @Override
    public CompositeShape clone() {
        CompositeShape clone = new CompositeShape();
        children.forEach(child -> clone.addChild(child.clone()));
        return clone;
    }
}