package com.example.laboratornaya2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public abstract class Shape implements Cloneable {
    protected String type;
    protected double size;
    protected Paint color; // Изменено на Paint
    protected double x;
    protected double y;
    protected boolean hasAnimation; // Добавлено поле для хранения информации о наличии анимации

    public Shape(double size, Paint color) { // Изменено на Paint
        this.size = size;
        this.color = color;
    }

    public abstract void draw(GraphicsContext gr, double x, double y, double opacity);

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean hasAnimation() {
        return hasAnimation;
    }

    public void setHasAnimation(boolean hasAnimation) {
        this.hasAnimation = hasAnimation;
    }

    @Override
    public Shape clone() {
        try {
            Shape cloned = (Shape) super.clone();
            cloned.hasAnimation = this.hasAnimation; // Клонируем поле hasAnimation
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return type;
    }
}
