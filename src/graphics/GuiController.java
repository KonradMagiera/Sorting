package graphics;

import algorithm.Algorithm;
import algorithm.BitonicSort;
import algorithm.BubbleSort;
import algorithm.CoctailSort;
import algorithm.CombSort;
import algorithm.CycleSort;
import algorithm.InsertionSort;
import algorithm.MergeSort;
import algorithm.QuickSort;
import algorithm.RecursiveBubbleSort;
import algorithm.RecursiveInsertionSort;
import algorithm.SelectionSort;
import algorithm.ShellSort;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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

    private List<Double> list;
    private int wid = 10;
    private int hei = 10;
    private Stage stage;
    private FileChooser fileChooser;
    private Algorithm algorithm;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sortingAlgorithm.getItems().addAll("Bubble sort", "Recursive bubble sort", "Quick sort",
                "Merge sort", "Insertion sort", "Recursive insertion sort", "Selection sort", "Shell sort", "Cycle sort",
                "Comb sort", "Bitonic sort", "Coctail sort");
        startB.setDisable(true);
        list = new ArrayList<>();
    }

    private void CalculateSizeMultipliers(List array) {
        if (!array.isEmpty()) {
            wid = (int) (display.getWidth() / array.size());
            hei = (int) (display.getHeight() / (double) Collections.max(array));
        }
    }

    public void drawArray() {
        display.getChildren().clear();
        CalculateSizeMultipliers(list);
        for (int i = 0; i < list.size(); i++) {
            Rectangle r = new Rectangle(wid * i, (display.getHeight()) - hei * list.get(i), wid, hei * list.get(i));
            r.setFill(Color.RED);
            r.setStroke(Color.YELLOW);
            display.getChildren().add(r);
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
    }

    public void lockButtons() {
        startB.setDisable(true);
        sortingAlgorithm.setDisable(true);
        loadB.setDisable(true);
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
        File openedFile = fileChooser.showOpenDialog(stage);
        // if chosen file is not null read array
        if (openedFile != null) {
            try {
                FileReader fr = new FileReader(openedFile);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                if (line != null) {
                    list.clear();
                    String[] doubles = line.split("\\s");
                    // try to load values to array
                    for (String s : doubles) {
                        try {
                            // only positive numbers are supported by gui at the moment
                            if (Double.parseDouble(s) >= 0) {
                                list.add(Double.parseDouble(s));
                            } else {
                                System.out.println("Negative number skipped: " + s);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("wrong value: " + s);
                        }
                    }
                    // calculate multipier to fit rectangles to gui
                    drawArray();
                    startB.setDisable(false);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void sortArray(ActionEvent event) {
        if (!list.isEmpty()) {
            if ("Bubble sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new BubbleSort(this, list);
                algorithm.restart();
            } else if ("Quick sort".equals(sortingAlgorithm.getValue())) {
                lockButtons();
                algorithm = new QuickSort(this, list);
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
            }
        }
    }
}
