package com.example.laboratornaya2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    private Canvas canvas;
    @FXML
    private Label figure_firstlab;
    @FXML
    private Label proverka_storon;
    @FXML
    private ListView<String> shapeListView;

    private ShapeFactory shapeFactory = new ShapeFactory();

    @FXML
    public void m_rectangle(ActionEvent actionEvent){
        GraphicsContext context = canvas.getGraphicsContext2D();
        Rectangle rectangle = new Rectangle(Color.RED, 100, 50);
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        rectangle.draw(context);
        figure_firstlab.setText(rectangle.toString());
    }

    @FXML
    public void m_circle(ActionEvent actionEvent){
        GraphicsContext context = canvas.getGraphicsContext2D();
        Circle circle = new Circle(Color.BLUE, 100);
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        circle.draw(context);
        figure_firstlab.setText(circle.toString());
    }

    @FXML
    public void m_ellipse(ActionEvent actionEvent){
        GraphicsContext context = canvas.getGraphicsContext2D();
        Ellipse ellipse = new Ellipse(Color.BLACK, 200, 100);
        context.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        ellipse.draw(context);
        figure_firstlab.setText(ellipse.toString());
    }

    @FXML
    public void m_roundRectangle(ActionEvent actionEvent){
        GraphicsContext context = canvas.getGraphicsContext2D();
        RoundRectangle roundRectangle = new RoundRectangle(Color.YELLOW, 100, 200, 30, 30);
        context.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        roundRectangle.draw(context);
        figure_firstlab.setText(roundRectangle.toString());
    }

    @FXML
    public void m_square(ActionEvent actionEvent){
        GraphicsContext context = canvas.getGraphicsContext2D();
        Square square = new Square(Color.VIOLET, 100,100);
        context.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        square.draw(context);
        figure_firstlab.setText(square.toString());
    }

    @FXML
    public void initialize() {
        ObservableList<String> shapes = FXCollections.observableArrayList(
                "Пятиугольник", "Квадрат", "Треугольник", "Угол", "Прямая", "Круг"
        );
        shapeListView.setItems(shapes);

        shapeListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            drawShape(newValue);
        });
    }

    @FXML
    private void drawShape(String shapeName) {
        Shape shape = null;
        switch (shapeName) {
            case "Пятиугольник":
                shape = shapeFactory.createShape(5);
                break;
            case "Квадрат":
                shape = shapeFactory.createShape(4);
                break;
            case "Треугольник":
                shape = shapeFactory.createShape(3);
                break;
            case "Угол":
                shape = shapeFactory.createShape(2);
                break;
            case "Прямая":
                shape = shapeFactory.createShape(1);
                break;
            case "Круг":
                shape = shapeFactory.createShape(0);
                break;
        }

        if (shape != null) {
            GraphicsContext context = canvas.getGraphicsContext2D();
            context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            shape.draw(context);
            proverka_storon.setText("Это " + shape.descriptor());
        } else {
            proverka_storon.setText("Такого выбора нет");
            System.out.println("Такого выбора нет");
        }
    }
}