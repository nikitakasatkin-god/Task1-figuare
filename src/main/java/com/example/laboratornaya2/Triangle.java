package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape{
    private double x1, y1, x2, y2, x3, y3;

    public Triangle(Color color, double x1, double y1, double x2, double y2, double x3, double y3){
        super(color, (x1 + x2 + x3) / 3, (y1 + y2 + y3) / 3);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    boolean isInside(double clickX, double clickY) {
        double A = area();
        double A1 = Math.abs((clickX * (y2 - y3) + x2 * (y3 - clickY) + x3 * (clickY - y2)) / 2.0);
        double A2 = Math.abs((x1 * (clickY - y3) + clickX * (y3 - y1) + x3 * (y1 - clickY)) / 2.0);
        double A3 = Math.abs((x1 * (y2 - clickY) + x2 * (clickY - y1) + clickX * (y1 - y2)) / 2.0);
        return A == A1 + A2 + A3;
    }

    @Override
    double area(){
        double a = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double b = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double c = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    double perimeter(){
        return 0;
    }

    @Override
    public String toString(){
        return null;
    }

    @Override
    public void draw(GraphicsContext gr){
        gr.setFill(color);
        double[] xPoints = {x1, x2, x3};
        double[] yPoints = {y1, y2, y3};
        gr.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public String descriptor(){
        return "Треугольник";
    }
}
