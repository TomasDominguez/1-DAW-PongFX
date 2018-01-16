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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;

public class main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       // Declaramos las variables de ejes de la bola.
          int ballCenterX = 10;
          int ballCurrentSpeedX = 3;
          int ballCenterY = 30;
          int ballCurrentSpeedY = 3 ; 
      
       // Declaramos dimensiones de pantalla de 800 x 600 px y color de fondo negro, titulo.
       StackPane root = new StackPane();
       Scene scene = new Scene(root, 800, 600, Color.BLACK);
       primaryStage.setTitle("PongFX");
       primaryStage.setScene(scene);
       primaryStage.show(); 
      
       // Creación de la Bola, Tamaño, Posicón y color.
       Circle circleBall = new Circle(ballCenterX, ballCenterY, 7, Color.WHITE);
       root.getChildren().add(circleBall);
       
       // Creamos la animación para el Movimiento de la bola.
       AnimationTimer animationBall = null;
       animationBall = new AnimationTimer(){
                 
            @Override
            public void handle(long now) {

                circleBall.setCenterX(ballCenterX);
                ballCenterX += ballCurrentSpeedX;
                if(ballCenterX >= 800) {
                        ballCurrentSpeedX = -3;
                }
                if(ballCenterX <= 0) {
                    ballCurrentSpeedX = 3;
                }
                
                circleBall.setCenterY(ballCenterY);
                ballCenterY += ballCurrentSpeedY;
                if(ballCenterY >= 600){
                    ballCurrentSpeedY = -3;
                }
                if(ballCenterY <= 0) {
                    ballCurrentSpeedY = 3;
                }
                
            };
        };
    }
