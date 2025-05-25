package com.example.laboratornaya2.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pentagon extends Shape {
    private final double side;

    public Pentagon(Color color, double x, double y, double side) {
        super(color, x, y);
        this.side = side;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        double[] xPoints = new double[5];
        double[] yPoints = new double[5];

        for (int i = 0; i < 5; i++) {
            double angle = 2 * Math.PI * i / 5 - Math.PI/2;
            xPoints[i] = x + side * Math.cos(angle);
            yPoints[i] = y + side * Math.sin(angle);
        }

        gc.fillPolygon(xPoints, yPoints, 5);
    }

    @Override
    public boolean contains(double x, double y) {
        // проверка - попадание в ограничивающий круг
        double radius = side * 0.85;
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2)) <= radius;
    }

    @Override
    public Pentagon clone() {
        return new Pentagon(color, x, y, side);
    }
}