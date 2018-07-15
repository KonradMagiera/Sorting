package algorithm;

import graphics.GuiController;
import java.util.List;

public class CoctailSort extends Algorithm {

    public CoctailSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        boolean swapped = true;
        int start = 0;
        int end = getSize();

        while (swapped == true) {
            swapped = false;

            for (int i = start; i < end - 1; ++i) {
                if (getItem(i) > getItem(i + 1)) {
                    swapValue(i, i + 1);
                    draw();
                    swapped = true;
                }
            }
            if (swapped == false) {
                break;
            }

            swapped = false;
            end = end - 1;

            for (int i = end - 1; i >= start; i--) {
                if (getItem(i) > getItem(i + 1)) {
                    swapValue(i, i + 1);
                    draw();
                    swapped = true;
                }
            }

            start = start + 1;
        }
    }

}
