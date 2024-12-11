package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Straight extends Shape {
    private final double length;

    public Straight(Color color, double x, double y, double length) {
        super(color, x, y);
        this.length = length;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.setLineWidth(2);
        gc.strokeLine(x, y, x + length, y);
    }

    @Override
    public boolean contains(double x, double y) {
        // Проверка близости точки к линии
        return Math.abs(y - this.y) < 5 &&
                x >= this.x && x <= this.x + length;
    }

    @Override
    public Straight clone() {
        return new Straight(color, x, y, length);
    }
}