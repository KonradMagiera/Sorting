package algorithm;

import graphics.GuiController;
import java.util.Collections;
import java.util.List;

public class HeapSort extends Algorithm {
    
    public HeapSort(GuiController gui, List<Double> list) {
        super(gui, list);
    }

    @Override
    protected void sort() {
        sort(getList());
    }
    
    public void sort(List<Double> list){
        int n = list.size();
        
        for(int i = n/2 - 1; i >= 0; i--){
            heapify(list, n, i);
        }
        
        for(int i =n-1; i >= 0; i--){
            Collections.swap(list, 0, i);
            draw();
            heapify(list, i, 0);
        }
    }
    
    private void heapify(List<Double> list, int n, int i){
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;
        
        if(left < n && list.get(left) > list.get(largest)){
            largest = left;
        }
        
        if(right < n && list.get(right) > list.get(largest)){
            largest = right;
        }
        
        if(largest != i){
            Collections.swap(list, i, largest);
            draw();
            heapify(list, n, largest);
        }
    }
}
