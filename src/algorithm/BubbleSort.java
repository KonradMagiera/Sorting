package algorithm;

import graphics.GuiController;
import java.util.List;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class BubbleSort extends Algorithm {

    public BubbleSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() {
                try {
                    sort();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            unlock();
                        }
                    });
                } catch (Exception e) {

                }

                return null;
            }
        };
    }

    @Override
    protected void sort() {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize() - 1; j++) {
                if (getItem(j) > getItem(j + 1)) {
                    swapValue(j, j + 1);
                    draw();
                }

            }
        }
    }
}
