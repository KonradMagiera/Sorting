package algorithm;

import graphics.GuiController;
import java.util.List;

public class ShellSort extends Algorithm {

    public ShellSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        int n = getSize();

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                double temp = getItem(i);
                int j;
                for (j = i; j >= gap && getItem(j - gap) > temp; j -= gap) {
                    setItem(j, getItem(j - gap));
                    draw();
                }
                setItem(j, temp);
                draw();
            }
        }
    }
}
