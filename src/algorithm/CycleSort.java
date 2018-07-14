package algorithm;

import graphics.GuiController;
import java.util.List;

public class CycleSort extends Algorithm {

    public CycleSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        // count number of memory writes
        int writes = 0;

        for (int cycleStart = 0; cycleStart <= getSize() - 2; cycleStart++) {
            //initialize item as starting position
            double item = getItem(cycleStart);

            int pos = cycleStart;
            for (int i = cycleStart + 1; i < getSize(); i++) {
                if (getItem(i) < item) {
                    pos++;
                }
            }

            // if item is already in correct position
            if (pos == cycleStart) {
                continue;
            }
            //ignore all duplicate elements
            while (item == getItem(pos)) {
                pos++;
            }

            //put the item to it's right position
            if (pos != cycleStart) {
                double temp = item;
                item = getItem(pos);
                setItem(pos, temp);
                writes++;
                draw();
            }

            // rotate rest of the cycle
            while (pos != cycleStart) {
                pos = cycleStart;

                //find position where we put the element
                for (int i = cycleStart + 1; i < getSize(); i++) {
                    if (getItem(i) < item) {
                        pos++;
                    }
                }

                // ignore all duplicate elements
                while (item == getItem(pos)) {
                    pos++;
                }

                // put the item to it's right position
                if (item != getItem(pos)) {
                    double temp = item;
                    item = getItem(pos);
                    setItem(pos, temp);
                    writes++;
                    draw();
                }
            }
        }
    }
}
