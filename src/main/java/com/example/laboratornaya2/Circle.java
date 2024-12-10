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
    public void draw(GraphicsContext gr){
        gr.setFill(color);
        gr.fillOval(x - radius, y - radius, 2 * radius, radius * 2);
    }

    @Override
    public String descriptor(){
        return "Круг";
    }

    @Override
    public Shape clone() {
        return new Circle(color, radius);
    }
}
