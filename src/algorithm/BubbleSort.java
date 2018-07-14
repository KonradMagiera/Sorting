package algorithm;

import graphics.GuiController;
import java.util.List;

public class BubbleSort extends Algorithm {

    public BubbleSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize() - 1; j++) {
                if (getItem(j) > getItem(j + 1)) {
                    // swap j and (j+1) elements
                    swapValue(j, j + 1);
                    draw();
                }

            }
        }
    }
}
