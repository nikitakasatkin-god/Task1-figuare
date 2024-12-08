package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape{
    private double radius;

    public Circle(Color color, double radius){
        super(color);
        this.radius = radius;
    }

    public Circle(Color color, double x, double y, double radius) {
        super(color, x, y);
        this.radius = radius;
    }

    boolean isInside(double clickX, double clickY) {
        double centerX = x + radius;
        double centerY = y + radius;
        return Math.pow(clickX - centerX, 2) + Math.pow(clickY - centerY, 2) <= Math.pow(radius, 2);
    }

    @Override
    double area(){
        return Math.PI * radius * radius;
    }

    @Override
    public String toString(){
        return "Цвет круга " + super.color + " и его площадь: " + Math.round(area())+ " и его периметр: " + Math.round(perimeter());
    }

    @Override
    double perimeter(){
        return 2 * Math.PI * radius;
    }

    @Override
    public void draw(GraphicsContext gr){
        gr.setFill(color);
        gr.fillOval(x, y, radius, radius);
    }

    @Override
    public String descriptor(){
        return "Круг";
    }
}
