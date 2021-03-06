package algorithm;

import graphics.GuiController;
import java.util.Collections;
import java.util.List;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public abstract class Algorithm extends Service<Void> {

    private GuiController gui;
    private List<Double> list;

    public Algorithm(GuiController gui, List<Double> list) {
        this.gui = gui;
        this.list = list;
    }

    public void draw() {
        Platform.runLater(() -> {
            gui.drawArray();
            // write() is for testing
            // write();   
        });
        try {
            Thread.sleep(175);
        } catch (InterruptedException ex) {

        }
    }

    public void setItem(int i, double d) {
        list.set(i, d);
    }

    public double getItem(int i) {
        return list.get(i);
    }

    public void swapValue(int i, int j) {
        Collections.swap(list, i, j);
    }

    public int getSize() {
        return list.size();
    }

    public void write() {
        System.out.println(list);
    }

    public void unlock() {
        gui.unlockButtons();
    }

    public List<Double> getList() {
        return list;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() {
                try {
                    sort();
                    Platform.runLater(() -> {
                        unlock();
                    });
                } catch (Exception e) {

                }

                return null;
            }
        };
    }

    protected abstract void sort();
}
