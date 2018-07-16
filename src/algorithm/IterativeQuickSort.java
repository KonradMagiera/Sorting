package algorithm;

import graphics.GuiController;
import java.util.Collections;
import java.util.List;

public class IterativeQuickSort extends Algorithm {

    public IterativeQuickSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        sort(getList(), 0, getSize() - 1);
    }

    private void sort(List<Double> list, int low, int high) {
        int[] stack = new int[high - low + 1];

        int top = -1;
        stack[++top] = low;
        stack[++top] = high;

        while (top >= 0) {
            high = stack[top--];
            low = stack[top--];

            int p = partition(list, low, high);

            if (p - 1 > low) {
                stack[++top] = low;
                stack[++top] = p - 1;
            }

            if (p + 1 < high) {
                stack[++top] = p + 1;
                stack[++top] = high;
            }
        }
    }

    private int partition(List<Double> list, int low, int high) {
        double pivot = list.get(high);
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (list.get(j) <= pivot) {
                i++;
                Collections.swap(list, i, j);
                draw();
            }
        }
        Collections.swap(list, i + 1, high);
        draw();
        return i + 1;

    }

}
