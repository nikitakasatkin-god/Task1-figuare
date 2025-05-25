package com.example.laboratornaya2.controller;

import com.example.laboratornaya2.composite.CompositeShape;
import com.example.laboratornaya2.model.Shape;
import com.example.laboratornaya2.factory.ShapeFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.*;

public class HelloController {
    private enum Mode { DRAW, SELECT, MOVE }

    @FXML private Canvas canvas;
    @FXML private ListView<String> shapeListView;
    @FXML private TextField sizeInp;
    @FXML private ColorPicker colorPicker;
    @FXML private Button selectButton;
    @FXML private Button combineButton;

    private GraphicsContext gc;
    private final List<Shape> allShapes = new ArrayList<>();
    private final Stack<List<Shape>> undoStack = new Stack<>();
    private final Stack<List<Shape>> redoStack = new Stack<>();

    private Mode currentMode = Mode.DRAW;
    private double startX, startY;
    private CompositeShape currentGroup;
    private final List<Shape> selectedShapes = new ArrayList<>();

    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        shapeListView.setItems(FXCollections.observableArrayList(
                "Линия", "Круг", "Квадрат", "Прямоугольник",
                "Пятиугольник", "Треугольник"
        ));
        saveState();
    }

    @FXML
    private void onSelectButtonClick() {
        currentMode = Mode.SELECT;
        selectedShapes.clear();
        currentGroup = null;
    }

    @FXML
    private void onCombineButtonClick() {
        if (!selectedShapes.isEmpty()) {
            currentGroup = new CompositeShape();
            selectedShapes.forEach(currentGroup::addChild);
            allShapes.removeAll(selectedShapes);
            allShapes.add(currentGroup);
            selectedShapes.clear();
            saveState();

            // Убираем выделение и переключаемся в режим перемещения
            currentMode = Mode.MOVE;
            redrawCanvas(); // Просто перерисовываем без каких-либо рамок
        }
    }

    @FXML
    private void onMousePressed(MouseEvent event) {
        startX = event.getX();
        startY = event.getY();

        switch (currentMode) {
            case SELECT:
                // Начало выделения области
                break;

            case MOVE:
                // Проверяем, кликнули ли на группу для перемещения
                if (currentGroup != null && currentGroup.contains(startX, startY)) {
                    break;
                }
                // Если кликнули не на группу, переключаемся в режим рисования
                currentMode = Mode.DRAW;

            case DRAW:
                startDrawing(startX, startY);
                break;
        }
    }

    @FXML
    private void onMouseDragged(MouseEvent event) {
        double currentX = event.getX();
        double currentY = event.getY();

        switch (currentMode) {
            case SELECT:
                drawSelectionRect(startX, startY, currentX, currentY);
                break;

            case MOVE:
                if (currentGroup != null) {
                    moveGroup(currentX, currentY);
                }
                break;

            case DRAW:
                continueDrawing(currentX, currentY);
                break;
        }
    }

    @FXML
    private void onMouseReleased(MouseEvent event) {
        switch (currentMode) {
            case SELECT:
                completeSelection(startX, startY, event.getX(), event.getY());
                break;

            case MOVE:
                saveState();
                redrawCanvas();
                break;

            case DRAW:
                saveState();
                redrawCanvas();
                break;
        }
    }

    private void startDrawing(double x, double y) {
        String shapeType = shapeListView.getSelectionModel().getSelectedItem();
        Color color = colorPicker.getValue();
        double size = Double.parseDouble(sizeInp.getText());

        Shape shape = ShapeFactory.createShape(shapeType, color, x, y, size);
        if (shape != null) {
            allShapes.add(shape);
            redrawCanvas();
        }
    }

    private void continueDrawing(double x, double y) {
        if (!allShapes.isEmpty()) {
            Shape lastShape = allShapes.get(allShapes.size() - 1);
            lastShape.setPosition(x, y);
            allShapes.add(lastShape.clone());
            redrawCanvas();
        }
    }

    private void drawSelectionRect(double x1, double y1, double x2, double y2) {
        redrawCanvas(); // Сначала рисуем все фигуры
        gc.setStroke(Color.BLUE);
        gc.setLineDashes(5);
        gc.strokeRect(
                Math.min(x1, x2), Math.min(y1, y2),
                Math.abs(x2 - x1), Math.abs(y2 - y1)
        );
        gc.setLineDashes(null);
    }

    private void completeSelection(double x1, double y1, double x2, double y2) {
        double minX = Math.min(x1, x2);
        double maxX = Math.max(x1, x2);
        double minY = Math.min(y1, y2);
        double maxY = Math.max(y1, y2);

        selectedShapes.clear();
        allShapes.forEach(shape -> {
            if (shape.getX() >= minX && shape.getX() <= maxX &&
                    shape.getY() >= minY && shape.getY() <= maxY) {
                selectedShapes.add(shape);
            }
        });

        // Перерисовываем без прямоугольника выделения
        redrawCanvas();
    }

    private void moveGroup(double x, double y) {
        double dx = x - startX;
        double dy = y - startY;
        startX = x;
        startY = y;
        currentGroup.setPosition(currentGroup.getX() + dx, currentGroup.getY() + dy);
    }

    private void saveState() {
        List<Shape> state = new ArrayList<>();
        allShapes.forEach(shape -> state.add(shape.clone()));
        undoStack.push(state);
        redoStack.clear();
    }

    @FXML
    private void onUndo() {
        if (undoStack.size() > 1) {
            redoStack.push(undoStack.pop());
            allShapes.clear();
            undoStack.peek().forEach(shape -> allShapes.add(shape.clone()));
            currentGroup = null;
            selectedShapes.clear();
            redrawCanvas();
        }
    }

    @FXML
    private void onRedo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(redoStack.pop());
            allShapes.clear();
            undoStack.peek().forEach(shape -> allShapes.add(shape.clone()));
            currentGroup = null;
            selectedShapes.clear();
            redrawCanvas();
        }
    }

    private void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Рисуем все фигуры (включая группы, но без специальных рамок)
        allShapes.forEach(shape -> shape.draw(gc));
    }

    @FXML
    private void clearCanvas() {
        allShapes.clear();
        undoStack.clear();
        redoStack.clear();
        currentGroup = null;
        selectedShapes.clear();
        saveState();
        redrawCanvas();
    }
}