package algorithm;

import graphics.GuiController;
import java.util.List;

public class CombSort extends Algorithm {

    public CombSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        // initialize gap
        int gap = getSize();

        // swapped = true to make sure that loop runs
        boolean swapped = true;

        while (gap != 1 || swapped == true) {
            gap = getNextGap(gap);
            // swapped = false - we can check if swap happened or not
            swapped = false;

            // compare all elements with current gap
            for (int i = 0; i < getSize() - gap; i++) {
                if (getItem(i) > getItem(i + gap)) {
                    swapValue(i, i + gap);
                    draw();
                    
                    // set swapped
                    swapped = true;
                }
            }
        }
    }

    // find gap between elements
    private int getNextGap(int gap) {
        // shrink gap by shrink factor
        gap = (gap * 10) / 13;
        if (gap < 1) {
            return 1;
        }
        return gap;
    }
}
