package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

abstract class Shape {
    protected Color color;
    protected double x,y;

    abstract double area();
    abstract void draw(GraphicsContext gr);
    abstract String descriptor();

    public Shape(Color color){
        this.color = color;
    }

    public Shape(Color color, double x, double y){
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public abstract Shape clone();
}
