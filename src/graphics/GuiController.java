package graphics;

import algorithm.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GuiController implements Initializable {

    @FXML
    private Pane display;
    @FXML
    private Button loadB;
    @FXML
    private ChoiceBox<String> sortingAlgorithm;
    @FXML
    private Button startB;
    @FXML
    private Button restoreB;

    private List<Double> list;
    private double wid = 10;
    private double hei = 10;
    private Stage stage;
    private FileChooser fileChooser;
    private Algorithm algorithm;
    private double min;
    private final double multiplier = 1.5;
    private File openedFile;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sortingAlgorithm.getItems().addAll("Bitonic sort", "Bubble sort", "Coctail sort", "Comb sort", "Cycle sort", "Heap sort",
                "Insertion sort", "Iterative quick sort", "Merge sort", "Odd-Even sort", "Pancake sort",
                "Quick sort", "Recursive bubble sort", "Recursive insertion sort", "Recursive merge sort", "Recursive Quick sort",
                "Selection sort", "Shell sort", "Stooge sort");
//        sortingAlgorithm.getItems().add("Quick sort");
        sortingAlgorithm.setValue("Quick sort");
        startB.setDisable(true);
        list = new ArrayList<>();
    }

    public void calculateSizeMultipliers() {
        if (!list.isEmpty()) {
            min = Collections.min(list);
            if (min >= 0) {
                min = 0;
            } else {
                min = Math.abs(min);
            }
            wid =  display.getWidth() / list.size();
            hei =  display.getHeight() / ((double) Collections.max(list) + multiplier * min);
        }
    }

    public void drawArray() {
        display.getChildren().clear();
        for (int i = 0; i < list.size(); i++) {
            double posHeight = (display.getHeight()) - hei * (list.get(i) + multiplier * min);
            double height = hei * (list.get(i) + multiplier * min);
            Rectangle r = new Rectangle(wid * i, posHeight, wid, height);
            r.setFill(Color.RED);
            r.setStroke(Color.YELLOW);
            display.getChildren().add(r);
            Label label = new Label(list.get(i).toString());
            label.setFont(new Font(16));
            label.setLayoutX(wid * i);
            label.setLayoutY((display.getHeight()) - (hei * (list.get(i) + multiplier * min)) / 2);
            label.setPrefWidth(wid);
            label.setAlignment(Pos.CENTER);
            display.getChildren().add(label);
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public List<Double> getList() {
        return list;
    }

    public void unlockButtons() {
        loadB.setDisable(false);
        sortingAlgorithm.setDisable(false);
        startB.setDisable(false);
        restoreB.setDisable(false);
    }

    public void lockButtons() {
        startB.setDisable(true);
        sortingAlgorithm.setDisable(true);
        loadB.setDisable(true);
        restoreB.setDisable(true);
    }

    private void loadFromFile() {
        if (openedFile != null) {
            try {
                FileReader fr = new FileReader(openedFile);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                if (line != null) {
                    list.clear();
                    String[] doubles = line.split("\\s+");
                    // try to load values to array
                    for (String s : doubles) {
                        try {
                            list.add(Double.parseDouble(s));
                        } catch (NumberFormatException e) {
                            System.out.println("wrong value: " + s);
                        }
                    }
                    // calculate multipier to fit rectangles to gui
                    calculateSizeMultipliers();
                    drawArray();
                    startB.setDisable(false);
                }
            } catch (IOException e) {

            }
        }
    }
    
    @FXML
    private void loadArray(ActionEvent event) {
        // create fileChooser to open txt file
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT files", "*.txt"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setTitle("Load array");
        fileChooser.setInitialFileName("SortThisArray");
        openedFile = fileChooser.showOpenDialog(stage);
        // if chosen file is not null read array
        loadFromFile();
    }

    @FXML
    private void restoreArray(ActionEvent event) {
        list.removeAll(list);
        loadFromFile();
    }

    @FXML
    private void sortArray(ActionEvent event) {
        if (!list.isEmpty()) {
            if ("Bubble sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new BubbleSort(this, list);
                algorithm.restart();
            } else if ("Merge sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new MergeSort(this, list);
                algorithm.restart();
            } else if ("Insertion sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new InsertionSort(this, list);
                algorithm.restart();
            } else if ("Selection sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new SelectionSort(this, list);
                algorithm.restart();
            } else if ("Shell sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new ShellSort(this, list);
                algorithm.restart();
            } else if ("Cycle sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new CycleSort(this, list);
                algorithm.restart();
            } else if ("Recursive bubble sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new RecursiveBubbleSort(this, list);
                algorithm.restart();
            } else if ("Recursive insertion sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new RecursiveInsertionSort(this, list);
                algorithm.restart();
            } else if ("Comb sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new CombSort(this, list);
                algorithm.restart();
            } else if ("Bitonic sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new BitonicSort(this, list);
                algorithm.restart();
            } else if ("Coctail sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new CoctailSort(this, list);
                algorithm.restart();
            } else if ("Pancake sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new PancakeSort(this, list);
                algorithm.restart();
            } else if ("Recursive merge sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new RecursiveMergeSort(this, list);
                algorithm.restart();
            } else if ("Stooge sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new StoogeSort(this, list);
                algorithm.restart();
            } else if ("Odd-Even sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new OddEvenSort(this, list);
                algorithm.restart();
            } else if ("Iterative quick sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new IterativeQuickSort(this, list);
                algorithm.restart();
            } else if ("Heap sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new HeapSort(this, list);
                algorithm.restart();
            } else if ("Recursive Quick sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new RecursiveQuickSort(this, list);
                algorithm.restart();
            } else if ("Quick sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new QuickSort(this, list);
                algorithm.restart();
            }
        }
    }
}
