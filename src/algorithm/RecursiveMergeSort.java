package algorithm;

import graphics.GuiController;
import java.util.ArrayList;
import java.util.List;

public class RecursiveMergeSort extends Algorithm {

    public RecursiveMergeSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        mergeSort(getList());
    }

    private void mergeSort(List<Double> list) {
        if (list == null) {
            return;
        }

        if (list.size() > 1) {
            int mid = list.size() / 2;

            // Split left part
            List<Double> left = new ArrayList<>();
            for (int i = 0; i < mid; i++) {
                left.add(list.get(i));
            }

            List<Double> right = new ArrayList<>();
            for (int i = mid; i < list.size(); i++) {
                right.add(list.get(i));
            }
            mergeSort(left);
            mergeSort(right);

            int i = 0;
            int j = 0;
            int k = 0;

            // Merge left and right arrays
            while (i < left.size() && j < right.size()) {
                if (left.get(i) < right.get(j)) {
                    list.set(k, left.get(i));
                    draw();
                    i++;
                } else {
                    list.set(k, right.get(j));
                    draw();
                    j++;
                }
                k++;
            }

            // collect remaining elements
            while (i < left.size()) {
                list.set(k, left.get(i));
                draw();
                i++;
                k++;
            }
            while (j < right.size()) {
                list.set(k, right.get(j));
                draw();
                j++;
                k++;
            }
        }
    }
}
