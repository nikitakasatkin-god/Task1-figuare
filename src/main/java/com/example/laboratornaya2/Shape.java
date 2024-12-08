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

    public Shape(Color color, double x, double y){
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Метод для проверки, находится ли точка внутри фигуры
    abstract boolean isInside(double x, double y);

    public void relocate(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }

    public void setColor(Color color){
        this.color = color;
    }
}
