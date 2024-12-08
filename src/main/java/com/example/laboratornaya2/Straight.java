package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Straight extends Shape{

    public Straight(Color color){
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
        gr.setStroke(color);
        gr.setLineWidth(5);
        gr.strokeLine(150,150,250,250);
    }

    @Override
    public String descriptor(){
        return "Отрезок";
    }
}
