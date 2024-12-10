package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Straight extends Shape{
    private double length;

    public Straight(Color color, double length){
        super(color);
        this.length = length;
    }

    @Override
    double area(){
        return 0;
    }

    @Override
    public void draw(GraphicsContext gr){
        gr.setStroke(color);
        gr.setLineWidth(5);
        gr.strokeLine(x,y,x + length,y);
    }

    @Override
    public String descriptor(){
        return "Отрезок";
    }

    @Override
    public Shape clone() {
        return new Straight(color, length);
    }
}
