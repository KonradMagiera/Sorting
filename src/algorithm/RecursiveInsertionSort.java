package algorithm;

import graphics.GuiController;
import java.util.List;

public class RecursiveInsertionSort extends Algorithm {

    public RecursiveInsertionSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        sort(getSize());
    }

    private void sort(int n) {
        if (n <= 1) {
            return;
        }

        // sort first (n-1) elements
        sort(n - 1);

        double last = getItem(n - 1);
        int j = n - 2;

        while (j >= 0 && getItem(j) > last) {
            setItem(j + 1, getItem(j));
            j--;
            draw();
        }
        setItem(j + 1, last);
        draw();
    }

}
