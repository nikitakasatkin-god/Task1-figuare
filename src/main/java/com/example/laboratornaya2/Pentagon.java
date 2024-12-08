package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pentagon extends Shape{
    public Pentagon(Color color){
        super(color);
    }

    @Override
    boolean isInside(double x, double y) {
        return false;
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
        double[] xPoints = { 200, 240, 220, 180, 160 };
        double[] yPoints = { 100, 150, 200, 200, 150 };
        gr.fillPolygon(xPoints, yPoints, 5);
    }

    @Override
    public String descriptor(){
        return "Пятиугольник";
    }
}
