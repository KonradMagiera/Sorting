package algorithm;

import graphics.GuiController;
import java.util.List;

public class RecursiveBubbleSort extends Algorithm {

    public RecursiveBubbleSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        sort(getSize());
    }

    private void sort(int n) {
        if (n == 1) {
            return;
        }

        for (int i = 0; i < n - 1; i++) {
            if (getItem(i) > getItem(i + 1)) {
                swapValue(i, i + 1);
                draw();
            }
        }
        sort(n - 1);
    }

}
