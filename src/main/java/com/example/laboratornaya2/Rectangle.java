package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(Color color, double x, double y, double width, double height) {
        super(color, x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x - width/2, y - height/2, width, height);
    }

    @Override
    public boolean contains(double x, double y) {
        return x >= this.x - width/2 && x <= this.x + width/2 &&
                y >= this.y - height/2 && y <= this.y + height/2;
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(color, x, y, width, height);
    }
}