package com.example.laboratornaya2;

import javafx.scene.paint.Color;

public class ShapeFactory {
    public Shape createShape(int numberOfSides){
        if(numberOfSides==5){
            return new Pentagon(Color.VIOLET);
        }
        else if(numberOfSides==4){
            return new Square(Color.RED, 100, 100);
        }
        else if(numberOfSides==3){
            return new Triangle(Color.BLUE, 200, 100, 250, 200, 150, 200);
        }
        else if(numberOfSides==2){
            return new Angle(Color.YELLOW);
        }
        else if(numberOfSides==1){
            return new Straight(Color.GREEN, 50, 20, 60, 10);
        }
        else if(numberOfSides==0){
            return new Circle(Color.BLACK, 100);
        }else{
            return null;
        }
    }
}
