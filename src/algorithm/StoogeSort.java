package algorithm;

import graphics.GuiController;
import java.util.List;

public class StoogeSort extends Algorithm {

    public StoogeSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        stoogeSort(getList(), 0, getSize() - 1);
    }

    private void stoogeSort(List<Double> list, int l, int h) {
        if (l >= h) {
            return;
        }

        if (getItem(l) > getItem(h)) {
            swapValue(l, h);
            draw();
        }

        if (h - l + 1 > 2) {
            int t = (h - l + 1) / 3;
            stoogeSort(list, l, h - t);
            stoogeSort(list, l + t, h);
            stoogeSort(list, l, h - t);
        }
    }

}
