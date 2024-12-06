package com.example.laboratornaya2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    private Canvas canvas;
    @FXML
    private Label rec;
    @FXML
    private Label cir;
    @FXML
    private Label ell;
    @FXML
    private Label roRec;
    @FXML
    private Label squ;


    @FXML
    public void m_rectangle(ActionEvent actionEvent){
        GraphicsContext context = canvas.getGraphicsContext2D();
        Rectangle rectangle = new Rectangle(Color.RED, 100, 50);
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        rectangle.draw(context);
        rec.setText(rectangle.toString());
    }

    @FXML
    public void m_circle(ActionEvent actionEvent){
        GraphicsContext context = canvas.getGraphicsContext2D();
        Circle circle = new Circle(Color.BLUE, 100);
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        circle.draw(context);
        cir.setText(circle.toString());
    }

    @FXML
    public void m_ellipse(ActionEvent actionEvent){
        GraphicsContext context = canvas.getGraphicsContext2D();
        Ellipse ellipse = new Ellipse(Color.BLACK, 200, 100);
        context.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        ellipse.draw(context);
        ell.setText(ellipse.toString());
    }

    @FXML
    public void m_roundRectangle(ActionEvent actionEvent){
        GraphicsContext context = canvas.getGraphicsContext2D();
        RoundRectangle roundRectangle = new RoundRectangle(Color.YELLOW, 100, 200, 30, 30);
        context.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        roundRectangle.draw(context);
        roRec.setText(roundRectangle.toString());
    }

    @FXML
    public void m_square(ActionEvent actionEvent){
        GraphicsContext context = canvas.getGraphicsContext2D();
        Square square = new Square(Color.VIOLET, 100,100);
        context.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        square.draw(context);
        squ.setText(square.toString());
    }
}