package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Straight extends Shape{
    private double x1, y1, x2, y2;

    public Straight(Color color, double x1, double y1, double x2, double y2){
        super(color, (x1 + x2) / 2, (y1 + y2) / 2);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    boolean isInside(double clickX, double clickY) {
        // Проверка, находится ли точка на отрезке
        double dx = x2 - x1;
        double dy = y2 - y1;
        double length = Math.sqrt(dx * dx + dy * dy);

        // Проверка, находится ли точка на прямой
        double dotProduct = (clickX - x1) * dx + (clickY - y1) * dy;
        if (dotProduct < 0 || dotProduct > length * length) {
            return false;
        }

        // Проверка, находится ли точка между начальной и конечной точками отрезка
        double distance = Math.abs((clickX - x1) * dy - (clickY - y1) * dx) / length;
        return distance <= 5; // Допустимая погрешность
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
