package algorithm;

import graphics.GuiController;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class QuickSort extends Algorithm{

    private final Stack<Integer> stack;
    
    public QuickSort(GuiController gui, List<Double> list) {
        super(gui, list);
        stack = new Stack<>();
    }
    
    @Override
    protected void sort() {
        sort(getList());
    }
    
    private void sort(List<Double> list){
        if(list.size() > 1){
            stack.push(0);
            stack.push(list.size() -1);
            while(!stack.isEmpty()){
                int end = stack.pop();
                int start = stack.pop();
                int mid = partition(list, start, end);
                if (mid - 1 > start) {
                    stack.push(start);
                    stack.push(mid - 1);
                }
                if (mid + 1 < end) {
                    stack.push(mid + 1);
                    stack.push(end);
                }
            }
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
                Collections.swap(list, i, j);
                draw();
            }
        }
        // swap (i+1) and high elements
        Collections.swap(list, i + 1, high);
        draw();
        return i + 1;
    }  
}
