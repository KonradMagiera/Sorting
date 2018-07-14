package algorithm;

import graphics.GuiController;
import java.util.ArrayList;
import java.util.List;

public class MergeSort extends Algorithm {

    public MergeSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        sort(getList(), 0, getSize() - 1);
    }

    private void sort(List<Double> list, int l, int r) {
        if (l < r) {
            // middle point
            int m = (l + r) / 2;

            // sortint first and second halves
            sort(list, l, m);
            sort(list, m + 1, r);

            // megre sorted halves
            merge(list, l, m, r);
        }
    }

    private void merge(List<Double> list, int l, int m, int r) {

        // sizes of two subarrays
        int n1 = m - l + 1;
        int n2 = r - m;

        // temp arrays
        List<Double> li = new ArrayList<>();
        List<Double> ri = new ArrayList<>();

        // copy data
        for (int i = 0; i < n1; ++i) {
            li.add(list.get(l + i));
        }
        for (int j = 0; j < n2; ++j) {
            ri.add(list.get(m + 1 + j));
        }

        // merge the temp arrays
        int i = 0, j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            if (li.get(i) <= ri.get(j)) {
                list.set(k, li.get(i));
                i++;
                draw();
            } else {
                list.set(k, ri.get(j));
                j++;
                draw();
            }
            k++;
        }

        // copy remaining elements of arrays if any
        while (i < n1) {
            list.set(k, li.get(i));
            i++;
            k++;
            draw();
        }
        while (j < n2) {
            list.set(k, ri.get(j));
            j++;
            k++;
            draw();
        }
    }
}
