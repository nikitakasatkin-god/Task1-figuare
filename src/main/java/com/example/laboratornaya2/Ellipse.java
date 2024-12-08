package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ellipse extends Shape{
    private double a;
    private double b;

    public Ellipse(Color color, double a, double b){
        super(color);
        this.a = a;
        this.b = b;
    }

    @Override
    double area(){
        return Math.PI * a * b;
    }

    @Override
    double perimeter(){
        return 2 * Math.PI * Math.sqrt(((a * 2 - b * 2) / 2));
    }

    @Override
    public String toString(){
        return "Цвет эллипса " + super.color + " и его площадь: " + Math.round(area()) + " и его периметр: " + Math.round(perimeter());
    }

    @Override
    public void draw(GraphicsContext gr){
        gr.setFill(color);
        gr.fillOval(x, y, a, b);
    }

    @Override
    public String descriptor(){
        return null;
    }
}
