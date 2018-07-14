package algorithm;

import graphics.GuiController;
import java.util.List;

public class QuickSort extends Algorithm {

    public QuickSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        sort(getList(), 0, getSize() - 1);
    }

    private void sort(List<Double> list, int low, int high) {
        if (low < high) {
            // partitioning index
            int pi = partition(list, low, high);

            // recursively sort elements
            sort(list, low, pi - 1);
            sort(list, pi + 1, high);
        }
    }

    private int partition(List<Double> list, int low, int high) {
        double pivot = list.get(high);

        // index of smaller element
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j) <= pivot) {
                i++;
                // swap i and j elements
                swapValue(i, j);
                draw();
            }
        }
        // swap (i+1) and high elements
        swapValue(i + 1, high);
        draw();
        return i + 1;
    }
}
