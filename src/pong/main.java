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
import javafx.stage.Stage;

public class main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       StackPane root = new StackPane();
       Scene scene = new Scene(root, 300, 250);
       primaryStage.setTitle("PongFX");
       primaryStage.setScene(scene);
       primaryStage.show();
    }
}