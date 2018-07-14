package algorithm;

import graphics.GuiController;
import java.util.List;

public class SelectionSort extends Algorithm {

    public SelectionSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        int n = getSize();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (getItem(j) < getItem(minIndex)) {
                    minIndex = j;
                }
            }
            swapValue(i, minIndex);
            draw();
        }
    }
}
