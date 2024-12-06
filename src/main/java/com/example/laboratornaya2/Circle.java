package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape{
    private double radius;

    public Circle(Color color, double radius){
        super(color);
        this.radius = radius;
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
}
