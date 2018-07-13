package graphics;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SortingMain extends Application {
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/graphics/layout.fxml"));
        GridPane pane = loader.load();
        GuiController gui = loader.getController();
        gui.setStage(primaryStage);
        Scene scene = new Scene(pane, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.setTitle("Sorting");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
