package com.quackiq.drawrandom.Subsystems;

import com.quackiq.drawrandom.ColorPalette;
import com.quackiq.drawrandom.Utils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainCanvas extends Subsystem {
    private final GraphicsContext gc;
    private final Pane canvasPane;
    private final InputManager inputManager;
    private final ProgressBar drawProgress;
    private List<Color> colors;
    private int lineCount;
    private int maxLineSeg;
    private Long LineWait;

    public MainCanvas(HashMap<String, Object> UiComponents, HashMap<String, Object> Subsystems) {
        gc = ((Canvas) UiComponents.get("Canvas")).getGraphicsContext2D();
        canvasPane = (Pane) UiComponents.get("DrawPane");
        inputManager = (InputManager) Subsystems.get("InputManager");
        drawProgress = (ProgressBar) UiComponents.get("DrawProgress");
        gc.clearRect(0, 0, canvasPane.getWidth(), canvasPane.getHeight());
        colors = ColorPalette.getPalette("ui");
        bind();
    }

    public void getColors() {
        //noinspection
        colors = ColorPalette.getPalette(inputManager.getColorPaletteVal());
    }

    public void updateSettings() {
        getColors();
        lineCount = inputManager.getLineCountSpinnerValue();
        maxLineSeg = inputManager.getSegmentSpinnerValue();
        LineWait = ((long) lineCount * maxLineSeg) / 100; // dumb way to get around floating point errors
        if (LineWait == 0) {
            LineWait = 1L;
        }
        System.out.println("LineWait: " + LineWait);

    }

    private Color getRandomColor() {
        return colors.get((int) (Math.random() * colors.size()));
    }

    private int getRandomX() {
        return (int) (Math.random() * canvasPane.getWidth());
    }

    private int getRandomY() {
        return (int) (Math.random() * canvasPane.getHeight());
    }

    private void setBackgroundColor() {
        Color base = Utils.getBaseColor(colors);
        if (!colors.remove(base)) {
            throw new RuntimeException("Base color not found in color list");
        }
        gc.setFill(base);
        gc.fillRect(0, 0, canvasPane.getWidth(), canvasPane.getHeight());


    }

    private void drawLineSegment(Color color) {
        gc.setStroke(color);
        gc.setLineWidth((Math.random() * 5) + 1);
        gc.beginPath();
        AtomicBoolean finished = new AtomicBoolean(false);
        Thread thread = new Thread(() -> {
            for (int i = 0; i < maxLineSeg; i++) {
                gc.lineTo(getRandomX(), getRandomY());
                gc.stroke();
                try {
                    Thread.sleep(LineWait * 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            finished.set(true);
        });
        thread.start();
        while (!finished.get()) {
            try {
                Thread.sleep(LineWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void draw() {
        updateSettings();
        setBackgroundColor();
        System.out.println("Got button press");
        Thread drawThread = new Thread(() -> {
            System.out.println("Starting draw thread");
            for (int i = 0; i < lineCount; i++) {
                drawLineSegment(getRandomColor());
                drawProgress.setProgress((double) i / lineCount);
                try {
                    Thread.sleep(LineWait * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        drawThread.start();
    }

    public void bind() {
        gc.getCanvas().widthProperty().bind(canvasPane.widthProperty());
        gc.getCanvas().heightProperty().bind(canvasPane.heightProperty());
    }


}
