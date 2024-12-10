package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape{
    private double base, height;

    public Triangle(Color color, double base, double height){
        super(color);
        this.base = base;
        this.height = height;
    }

    @Override
    double area(){
        return 0.5 * base * height;
    }

    @Override
    public void draw(GraphicsContext gr){
        gr.setFill(color);
        double[] xPoints = {x - base / 2, x, x + base / 2};
        double[] yPoints = {y + height, y, y + height};
        gr.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public String descriptor(){
        return "Треугольник";
    }

    @Override
    public Shape clone() {
        return new Triangle(color, base, height);
    }
}
