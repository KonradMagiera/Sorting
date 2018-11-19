package graphics;

import java.io.IOException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Sorting extends Application {

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
        primaryStage.getIcons().add(new Image("graphics/logo.png"));
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue)
                -> {
            gui.calculateSizeMultipliers();
            gui.drawArray();
        };

        primaryStage.widthProperty().addListener(stageSizeListener);
        primaryStage.heightProperty().addListener(stageSizeListener);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
