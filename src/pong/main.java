/**
 *
 * @author Tomás Dominguez Gómez
 * 1º DAW - Programación 2º Trimestre.
 * IES Ntra. Sra. Los Remedios 
 */
package pong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       StackPane root = new StackPane();
       Scene scene = new Scene(root, 600, 400, Color.BLACK);
       primaryStage.setTitle("PongFX");
       primaryStage.setScene(scene);
       primaryStage.show();
       
       Circle circleBall = new Circle(10, 30, 7, Color.WHITE);
       root.getChildren().add(circleBall);
       circleBall.setCenterX(10);
       circleBall.setCenterY(30);
       circleBall.setRadius(7);
    }
}