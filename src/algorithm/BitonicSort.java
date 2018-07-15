package algorithm;

import graphics.GuiController;
import java.util.List;

public class BitonicSort extends Algorithm {

    public BitonicSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        bitonicSort(0, getSize(), true);
    }

    private void bitonicSort(int lo, int n, boolean dir) {
        if (n > 1) {
            int m = n / 2;
            bitonicSort(lo, m, !dir);
            bitonicSort(lo + m, n - m, dir);
            bitonicMerge(lo, n, dir);
        }
    }

    private void bitonicMerge(int lo, int n, boolean dir) {
        if (n > 1) {
            int m = greatestPowerOfTwoLessThan(n);
            for (int i = lo; i < lo + n - m; i++) {
                compare(i, i + m, dir);
            }
            bitonicMerge(lo, m, dir);
            bitonicMerge(lo + m, n - m, dir);
        }
    }

    private void compare(int i, int j, boolean dir) {
        if (dir == (getItem(i) > getItem(j))) {
            swapValue(i, j);
            draw();
        }
    }

    private int greatestPowerOfTwoLessThan(int n) {
        int k = 1;
        while (k > 0 && k < n) {
            k = k << 1;
        }
        return k >>> 1;
    }
}
