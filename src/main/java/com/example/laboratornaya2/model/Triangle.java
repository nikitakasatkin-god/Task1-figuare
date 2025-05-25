package com.example.laboratornaya2.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {
    private final double base;
    private final double height;

    public Triangle(Color color, double x, double y, double base, double height) {
        super(color, x, y);
        this.base = base;
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        double[] xPoints = {x, x - base/2, x + base/2};
        double[] yPoints = {y - height/2, y + height/2, y + height/2};
        gc.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public boolean contains(double x, double y) {
        // Проверка попадания точки в треугольник (метод площадей)
        double areaTotal = 0.5 * base * height;
        double area1 = area(x, y, x - base/2, y + height/2, x + base/2, y + height/2);
        double area2 = area(this.x, this.y - height/2, x, y, x + base/2, y + height/2);
        double area3 = area(this.x, this.y - height/2, x - base/2, y + height/2, x, y);
        return Math.abs(areaTotal - (area1 + area2 + area3)) < 1.0;
    }

    private double area(double x1, double y1, double x2, double y2, double x3, double y3) {
        return Math.abs((x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2))/2.0);
    }

    @Override
    public Triangle clone() {
        return new Triangle(color, x, y, base, height);
    }
}