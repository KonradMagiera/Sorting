package algorithm;

import graphics.GuiController;
import java.util.List;

public class OddEvenSort extends Algorithm {

    public OddEvenSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;

            for (int i = 1; i <= getSize() - 2; i = i + 2) {
                if (getItem(i) > getItem(i + 1)) {
                    swapValue(i, i + 1);
                    draw();
                    isSorted = false;
                }
            }

            for (int i = 0; i <= getSize() - 2; i = i + 2) {
                if (getItem(i) > getItem(i + 1)) {
                    swapValue(i, i + 1);
                    isSorted = false;
                }
            }
        }
    }

}
