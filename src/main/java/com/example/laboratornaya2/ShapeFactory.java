package com.example.laboratornaya2;

import javafx.scene.paint.Color;

public class ShapeFactory {
    public Shape createShape(String shapeName, Color color, double... params){
        switch (shapeName){
            case "Линия":
                return new Straight(color, params[0]);
            case "Круг":
                return new Circle(color, params[0]);
            case "Квадрат":
                return new Square(color, params[0]);
            case "Прямоугольник":
                return new Rectangle(color, params[0], params[1]);
            case "Пятиугольник":
                return new Pentagon(color, params[0]);
            case "Треугольник":
                return new Triangle(color, params[0], params[1]);
            default:
                throw new IllegalArgumentException("Такой фигуры нет");
        }
    }
}
