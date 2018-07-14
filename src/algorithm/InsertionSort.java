package algorithm;

import graphics.GuiController;
import java.util.List;

public class InsertionSort extends Algorithm {

    public InsertionSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        for (int i = 1; i < getSize(); ++i) {
            double key = getItem(i);
            int j = i - 1;
            while (j >= 0 && getItem(j) > key) {
                setItem(j + 1, getItem(j));
                draw();
                j = j - 1;
            }
            setItem(j + 1, key);
            draw();
        }
    }
}
