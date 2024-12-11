package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {
    private final double side;

    public Square(Color color, double x, double y, double side) {
        super(color, x, y);
        this.side = side;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x - side/2, y - side/2, side, side);
    }

    @Override
    public boolean contains(double x, double y) {
        return x >= this.x - side/2 && x <= this.x + side/2 &&
                y >= this.y - side/2 && y <= this.y + side/2;
    }

    @Override
    public Square clone() {
        return new Square(color, x, y, side);
    }
}