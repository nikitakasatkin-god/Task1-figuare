package com.example.laboratornaya2;

import javafx.scene.paint.Color;

public class Memento {
    private Shape shape;
    private double x, y;
    private Color color;

    public Memento(Shape shape) {
        this.shape = shape;
        this.x = shape.x;
        this.y = shape.y;
        this.color = shape.color;
    }

    // Метод выделения фигуры
    public Shape initState() {
        shape.setColor(Color.RED); // Изменяем цвет фигуры для выделения
        return shape;
    }

    // Восстанавливаем исходное состояние фигуры
    public void restore() {
        shape.setColor(color); // Возвращаем цвет
    }

    public Shape getShape() {
        return shape;
    }
}
