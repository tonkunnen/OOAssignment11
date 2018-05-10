/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooass11;

import javafx.util.Duration;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static javafx.util.Duration.seconds;

/**
 *
 * @author Sem
 */
public class OOass11 extends Application {

    private Timeline timeline;
    private Label tl = new Label();
    private IntegerProperty ts = new SimpleIntegerProperty(0);
    private IntegerProperty init = new SimpleIntegerProperty(1); // starting value. Needed in order for the progressbar to empty instead of fill.
    //source: http://asgteach.com/2011/10/javafx-animation-and-binding-using-the-progressbar/. Used this as a base for our timeline binding as the code/hints in the exercise proved to be insufficient.

    public void start(Stage stage) {

        
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 350, 200);
        stage.setTitle("Time flies!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();

        Button start = new Button("Start");
        Button stop = new Button("Stop");
        Button quit = new Button("Quit");
        Label time = new Label("");
        ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefWidth(200);
        TextField input = new TextField();

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // decide placement
        GridPane.setConstraints(time, 5, 1);
        GridPane.setConstraints(progressBar, 5, 2);
        GridPane.setConstraints(input, 5, 3);
        GridPane.setConstraints(start, 6, 3);
        GridPane.setConstraints(stop, 5, 4);
        GridPane.setConstraints(quit, 6, 4);

        grid.getChildren().addAll(input, progressBar, stop, start, quit, time);

        start.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if (input.getText() != null && !input.getText().isEmpty()) {
                    time.setText("");
                    if (timeline != null) {
                        timeline.stop();
                        
                        
                    }
                    ts.set((Integer.parseInt(input.getText()) + 1) * 100);
                    timeline = new Timeline();
                    timeline.setOnFinished(e -> grid.setStyle("-fx-background-color: red;"));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(Integer.parseInt(input.getText())), new KeyValue(ts, 0)));
                    progressBar.progressProperty().bind(init.subtract(ts.divide(Integer.parseInt(input.getText()) * 100.0).subtract(1).multiply(-1)));
                    timeline.play();
                } else {
                    time.setText("Please enter a time first");
                }
            }
        });

        stop.setOnAction(e -> timeline.stop());
        quit.setOnAction(e -> System.exit(0));
        

        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}