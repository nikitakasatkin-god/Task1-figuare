package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Rectangle extends Shape{
    private double length;
    private double width;

    public Rectangle(Color color, double length, double width){
        super(color);
        this.length = length;
        this.width = width;
    }

    public Rectangle(Color color, double x, double y, double width, double height) {
        super(color, x, y);
        this.width = width;
        this.length = height;
    }

    boolean isInside(double clickX, double clickY) {
        return clickX >= x && clickX <= x + width && clickY >= y && clickY <= y + length;
    }

    @Override
    double area(){
        return length * width;
    }

    @Override
    double perimeter(){
        return length * 2 + width * 2;
    }



    @Override
    public String toString(){
        return "Цвет прямоугольника " + super.color + " и его площадь: " + Math.round(area()) + " и его периметр: " + Math.round(perimeter());
    }

    @Override
    public void draw(GraphicsContext gr){
        gr.setFill(color);
        gr.fillRect(x, y, length, width);
    }

    @Override
    public String descriptor(){
        return null;
    }
}
