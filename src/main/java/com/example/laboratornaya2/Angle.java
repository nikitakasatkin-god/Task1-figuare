package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Angle extends Shape{

    public Angle(Color color){
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
        gr.setStroke(color);
        gr.setLineWidth(10);
        gr.strokePolygon(new double[]{25, 250}, new double[]{22,25},2);
        gr.strokePolygon(new double[]{30,30}, new double[]{25,250},2);
    }

    @Override
    public String descriptor(){
        return "Прямой угол";
    }

}
