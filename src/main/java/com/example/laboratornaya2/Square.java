package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape{
    private double side;

    public Square(Color color, double side){
        super(color);
        this.side = side;
    }

    @Override
    double area(){
        return side * side;
    }

    @Override
    public void draw(GraphicsContext gr){
        gr.setFill(color);
        gr.fillRect(x, y, side, side);
    }

    @Override
    public String descriptor(){
        return "Квадрат";
    }

    @Override
    public Shape clone() {
        return new Square(color, side);
    }
}
