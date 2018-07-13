package algorithm;

import graphics.GuiController;
import java.util.Collections;
import java.util.List;
import javafx.concurrent.Service;
import javafx.concurrent.Task;


public abstract class Algorithm extends Service<Void> {

    private GuiController  gui;
    private List<Double> list;
    
    public Algorithm(GuiController gui, List<Double> list){
        this.gui = gui;
        this.list = list;
    }
    
    public void draw(){
        gui.drawArray();
    }
    
    public double getItem(int i){
        return list.get(i);
    }
    
    public void setValue(int i, int j){
        list.set(i, list.get(j));
    }
    
    public void swapValue(int i, int j){
        Collections.swap(list, i, j);
    }
    
    public int getSize(){
        return list.size();
    }
    public void write(){
        System.out.println(list);
    }
    @Override
    protected abstract Task<Void> createTask();
}
