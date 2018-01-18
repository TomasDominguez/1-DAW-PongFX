/**
 * @author Tomás Dominguez Gómez
 * 1º DAW - Programación. 
 * 2º Trimestre | Curso 2017/18.
 * IES Ntra. Sra. Los Remedios. 
 * Ubrique (Cádiz).
 */
package pong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;

public class main extends Application {
  
    // Declaramos las variables de ejes de la bola.
    int ballCenterX = 10;
    int ballCurrentSpeedX = 3;
    int ballCenterY = 30;
    int ballCurrentSpeedY = 3;
    
    // Declaramos las variables de Stick.
    int stickPosY = (400 - 50) / 2;
        
    @Override
    public void start(Stage primaryStage) {
        
        // Declaramos dimensiones de pantalla de 800 x 600 px y color de fondo negro, titulo.
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setTitle("PongFX");
        primaryStage.setScene(scene);
        primaryStage.show(); 
      
        // Creamos el objeto clase de la Bola, Tamaño, Posicón y color.
        Circle circleBall = new Circle(ballCenterX, ballCenterY, 7, Color.WHITE);
        root.getChildren().add(circleBall);
       
        // Creamos el objeto clase del Rectangulo.
        Rectangle rectStick = new Rectangle(500, stickPosY, 7, 50);
        rectStick.setFill(Color.WHITE);
        root.getChildren().add(rectStick);
       
        // Creamos la clase animación para el Movimiento de la bola.
        AnimationTimer animationBall = null;
        animationBall = new AnimationTimer(){
       
            @Override
            public void handle(long now) {
                
        // Comineza la sentencia de la animación de la bola.
                circleBall.setCenterX(ballCenterX);
                ballCenterX += ballCurrentSpeedX;
                if(ballCenterX >= 800){
                        ballCurrentSpeedX = -3;
                }
                if(ballCenterX <= 0){
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
        
        // Cominezo de la Animación de la bola del juego.
        animationBall.start();
    };

 
}
