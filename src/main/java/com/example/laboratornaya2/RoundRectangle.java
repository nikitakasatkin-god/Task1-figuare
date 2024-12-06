package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RoundRectangle extends Shape{
    private double width;
    private double length;
    private double arcHeight;
    private double arcWidth;

    public RoundRectangle(Color color, double width, double length, double arcHeight, double arcWidth){
        super(color);
        this.width = width;
        this.length = length;
        this.arcHeight = arcHeight;
        this.arcWidth = arcWidth;
    }

    @Override
    double area(){
        return width * length;
    }

    @Override
    double perimeter(){
        return length * 2 + width * 2;
    }

    @Override
    public String toString(){
        return "Цвет закругленного прямоугольника " + super.color + " и его площадь: " + Math.round(area()) + "\n и его периметр: " + Math.round(perimeter());
    }

    @Override
    public void draw(GraphicsContext gr){
        gr.setFill(color);
        gr.fillRoundRect(x, y, length, width, arcWidth, arcHeight);
    }
}
