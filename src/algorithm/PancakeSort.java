package algorithm;

import graphics.GuiController;
import java.util.List;

public class PancakeSort extends Algorithm {

    public PancakeSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        for (int currSize = getSize(); currSize > 1; --currSize) {
            int mi = findMax(currSize);

            if (mi != currSize - 1) {
                flip(mi);
                flip(currSize - 1);
            }
        }
    }

    private void flip(int i) {
        int start = 0;
        while (start < i) {
            swapValue(start, i);
            draw();
            start++;
            i--;
        }
    }

    private int findMax(int n) {
        int mi, i;
        for (mi = 0, i = 0; i < n; ++i) {
            if (getItem(i) > getItem(mi)) {
                mi = i;
            }
        }
        return mi;
    }
}
