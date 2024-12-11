package com.example.laboratornaya2;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.*;

public class HelloController {
    @FXML
    private Canvas canvas;
    @FXML
    private ListView<String> shapeListView;
    @FXML
    private TextField sizeInp;
    @FXML
    private ColorPicker colorPicker;

    private ShapeFactory shapeFactory = new ShapeFactory();
    public GraphicsContext gc;
    private List<Shape> shapes = new ArrayList<>();  // Список всех фигур
    private Stack<Shape> undoStack = new Stack<>();
    private Stack<Shape> redoStack = new Stack<>();
    private PriorityQueue<String> shapeQueue = new PriorityQueue<>();
    private Map<String, Integer> shapeCountMap = new HashMap<>();

    private boolean isDrawing = false;
    private Shape currentShape = null;

    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        shapeListView.setItems(FXCollections.observableArrayList(
                "Линия", "Круг", "Квадрат", "Прямоугольник", "Пятиугольник", "Треугольник"
        ));
    }

    @FXML
    private Shape drawShape(String shapeName, Color color, double size) {
        switch (shapeName) {
            case "Линия":
                return shapeFactory.createShape("Линия", color, size);
            case "Круг":
                return shapeFactory.createShape("Круг", color, size);
            case "Квадрат":
                return shapeFactory.createShape("Квадрат", color, size);
            case "Прямоугольник":
                return shapeFactory.createShape("Прямоугольник", color, size*2, size);
            case "Пятиугольник":
                return shapeFactory.createShape("Пятиугольник", color, size);
            case "Треугольник":
                return shapeFactory.createShape("Треугольник", color, size, size);
            default:
                return null;
        }
    }

    @FXML
    public void clearCanvas(ActionEvent actionEvent) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        shapes.clear();
        undoStack.clear();
        redoStack.clear();
        shapeQueue.clear();
        shapeCountMap.clear();
    }

    @FXML
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Обработчик для нажатия мыши
    @FXML
    private void onMousePressed(MouseEvent event) {
        isDrawing = true;
        redoStack.clear(); // Очищаем redoStack при начале нового действия
        onMouseDragged(event);
    }

    // Обработчик для отпускания мыши
    @FXML
    private void onMouseReleased(MouseEvent event) {
        isDrawing = false;
        currentShape = null;
    }

    // Обработчик для движения мыши при зажатой клавише
    @FXML
    private void onMouseDragged(MouseEvent event) {
        if (isDrawing) {
            String shapeName = shapeListView.getSelectionModel().getSelectedItem(); // Получаем выбранное название фигуры
            Color color = colorPicker.getValue(); // Получаем цвет
            double size = Double.parseDouble(sizeInp.getText()); // Получаем размер фигуры

            if (currentShape == null) {
                currentShape = drawShape(shapeName, color, size);
            }

            if (currentShape != null) {
                // Устанавливаем позицию фигуры на место курсора
                currentShape.setPosition(event.getX(), event.getY());
                currentShape.draw(gc);

                // Добавляем фигуру в список и стек для отмены
                shapes.add(currentShape);
                undoStack.push(currentShape);
                redoStack.push(currentShape);

                // Обновляем статистику
                shapeQueue.add(shapeName);
                shapeCountMap.put(shapeName, shapeCountMap.getOrDefault(shapeName, 0) + 1);

                // Создаем новую фигуру для следующего рисования
                currentShape = drawShape(shapeName, color, size);
            } else {
                showAlert("Ошибка", "Неверное название фигуры.");
            }
        }
    }

    @FXML
    public void onUndo() {
        if (!undoStack.isEmpty()) {
            Shape lastShape = undoStack.pop();
            shapes.remove(lastShape);
            redoStack.push(lastShape); // Добавляем в redoStack
            redraw();
        }
    }

    @FXML
    public void redo() {
        if (!redoStack.isEmpty()) {
            Shape lastShape = redoStack.pop();
            shapes.add(lastShape);
            undoStack.push(lastShape); // Возвращаем в undoStack
            redraw();
        }
    }

    @FXML
    private void redraw() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Shape shape : shapes) {
            shape.draw(gc);
        }
    }
}