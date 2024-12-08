package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape{

    public Triangle(Color color){
        super(color);
    }

    @Override
    double area(){
        return 0;
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
        double[] xPoints = { 200, 250, 150 };
        double[] yPoints = { 100, 200, 200 };
        gr.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public String descriptor(){
        return "Треугольник";
    }

}
