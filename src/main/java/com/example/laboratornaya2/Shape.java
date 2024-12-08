package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

abstract class Shape {
    protected Color color;
    protected double x,y;

    abstract double area();
    abstract double perimeter();
    abstract void draw(GraphicsContext gr);
    public abstract String toString();
    abstract String descriptor();

    public Shape(){

    }

    public Shape(Color color){
        this.color = color;
    }
    public void setColor(Color color){
        this.color = color;
    }
}
