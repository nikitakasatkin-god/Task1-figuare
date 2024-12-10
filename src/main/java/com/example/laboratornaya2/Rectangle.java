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

    @Override
    double area(){
        return length * width;
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

    @Override
    public Shape clone() {
        return new Rectangle(color, length, width);
    }
}
