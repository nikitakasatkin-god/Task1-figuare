package com.example.laboratornaya2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

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
    public GraphicsContext gc;
    private List<Momento> selectedShapes = new ArrayList<>();  // Список выделенных фигур
    private Caretaker caretaker = new Caretaker();
    private List<Shape> shapes = new ArrayList<>();  // Список всех фигур
    private boolean isShiftPressed = false;  // Проверка на множественный выбор
    private double initialClickX;  // Начальная координата X при нажатии
    private double initialClickY;  // Начальная координата Y при нажатии

    @FXML
    public void m_rectangle(ActionEvent actionEvent){
        gc = canvas.getGraphicsContext2D();
        Rectangle rectangle = new Rectangle(Color.RED, 100, 50);
        shapes.add(rectangle);
        //context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        rectangle.draw(gc);
        figure_firstlab.setText(rectangle.toString());
    }

    @FXML
    public void m_circle(ActionEvent actionEvent){
        gc = canvas.getGraphicsContext2D();
        Circle circle = new Circle(Color.BLUE, 100);
        shapes.add(circle);
        //context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        circle.draw(gc);
        figure_firstlab.setText(circle.toString());
    }

    @FXML
    public void m_ellipse(ActionEvent actionEvent){
        gc = canvas.getGraphicsContext2D();
        Ellipse ellipse = new Ellipse(Color.BLACK, 200, 100);
        shapes.add(ellipse);
        //context.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        ellipse.draw(gc);
        figure_firstlab.setText(ellipse.toString());
    }

    @FXML
    public void m_roundRectangle(ActionEvent actionEvent){
        GraphicsContext context = canvas.getGraphicsContext2D();
        RoundRectangle roundRectangle = new RoundRectangle(Color.YELLOW, 100, 200, 30, 30);
        shapes.add(roundRectangle);
        //context.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        roundRectangle.draw(context);
        figure_firstlab.setText(roundRectangle.toString());
    }

    @FXML
    public void m_square(ActionEvent actionEvent){
        GraphicsContext context = canvas.getGraphicsContext2D();
        Square square = new Square(Color.VIOLET, 100,100);
        shapes.add(square);
        //context.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        square.draw(context);
        figure_firstlab.setText(square.toString());
    }

    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();

        ObservableList<String> shapes = FXCollections.observableArrayList(
                "Пятиугольник", "Квадрат", "Треугольник", "Угол", "Прямая", "Круг"
        );
        shapeListView.setItems(shapes);

        shapeListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            drawShape(newValue);
        });

        // Добавляем обработчики событий мыши и клавиатуры
        canvas.setOnMousePressed(this::onBegin);
        canvas.setOnMouseDragged(this::onDrag);
        canvas.setOnMouseReleased(this::onEnd);
        canvas.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SHIFT) {
                isShiftPressed = true;  // Shift зажат, можно выбирать несколько фигур
            }
        });
        canvas.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.SHIFT) {
                isShiftPressed = false;  // Shift отпущен
            }
        });

        // Устанавливаем фокус на Canvas, чтобы обработчики клавиш работали
        canvas.setFocusTraversable(true);
        canvas.requestFocus();
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
              // Добавляем фигуру в список фигур
            shape.draw(gc);
            shapes.add(shape);
            proverka_storon.setText("Это " + shape.descriptor());
        } else {
            proverka_storon.setText("Такого выбора нет");
            System.out.println("Такого выбора нет");
        }
    }

    @FXML
    public void clearCanvas(ActionEvent actionEvent) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        shapes.clear();
        figure_firstlab.setText(null);
        proverka_storon.setText(null);
    }

    @FXML
    public void onBegin(MouseEvent event) {
        // Логика выбора фигуры
        initialClickX = event.getX();
        initialClickY = event.getY();

        Shape selectedShape = getShapeAt(initialClickX, initialClickY);
        if (selectedShape != null) {
            Momento temp = new Momento(selectedShape);  // Создаем снимок

            if (isShiftPressed) {
                // Если Shift зажат, добавляем фигуру в список выделенных
                selectedShapes.add(temp);
            } else {
                // Если Shift не зажат, очищаем список и добавляем одну фигуру
                selectedShapes.clear();
                selectedShapes.add(temp);
            }

            caretaker.saveState(temp);  // Сохраняем состояние
            temp.initState();  // Выделяем фигуру
            redraw();
        }
    }

    @FXML
    public void onDrag(MouseEvent event) {
        // Вычисляем смещение по X и Y для корректного перетаскивания
        double offsetX = event.getX() - initialClickX;
        double offsetY = event.getY() - initialClickY;

        // Перемещаем все выделенные фигуры
        if (!selectedShapes.isEmpty()) {
            for (Momento temp : selectedShapes) {
                Shape draggedShape = temp.getShape();
                draggedShape.relocate(draggedShape.getX() + offsetX, draggedShape.getY() + offsetY);  // Перемещаем фигуру
            }
            redraw();
        }

        // Обновляем координаты клика для плавного перетаскивания
        initialClickX = event.getX();
        initialClickY = event.getY();
    }

    @FXML
    public void onEnd(MouseEvent event) {
        // Восстанавливаем состояние фигур после завершения перетаскивания
        if (!selectedShapes.isEmpty()) {
            for (Momento temp : selectedShapes) {
                temp.restore();
                caretaker.saveState(temp);  // Сохраняем текущее состояние
            }
            redraw();
        }
    }

    private Shape getShapeAt(double x, double y) {
        // Проверка на попадание клика по любой фигуре
        for (Shape shape : shapes) {
            if (shape.isInside(x, y)) {
                return shape;
            }
        }
        return null;  // Если ни одна фигура не была выбрана
    }

    private void redraw() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Shape shape : shapes) {
            shape.draw(gc);  // Перерисовываем все фигуры
        }
    }
}