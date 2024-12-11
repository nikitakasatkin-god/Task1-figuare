package com.example.laboratornaya2;

import javafx.scene.paint.Color;

public class ShapeFactory {
    public static Shape createShape(String shapeName, Color color, double x, double y, double... params) {
        switch (shapeName) {
            case "Линия": return new Straight(color, x, y, params[0]);
            case "Круг": return new Circle(color, x, y, params[0]);
            case "Квадрат": return new Square(color, x, y, params[0]);
            case "Прямоугольник": return new Rectangle(color, x, y, params[0], params[1]);
            case "Пятиугольник": return new Pentagon(color, x, y, params[0]);
            case "Треугольник": return new Triangle(color, x, y, params[0], params[1]);
            default: return null;
        }
    }
}