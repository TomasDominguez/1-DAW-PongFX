/*
 *
 * @author Tomás Dominguez Gómez
 * 1º DAW - Programación. 
 * 2º Trimestre | Curso 2017/18.
 * IES Ntra. Sra. Los Remedios. 
 * Ubrique (Cádiz).
 * 
 */
package pong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class main extends Application {
  
    // Declaramos las variables de ejes de la bola.
    int ballCenterX = 10;
    int ballCurrentSpeedX = 3;
    int ballCenterY = 30;
    int ballCurrentSpeedY = 3;
      
    // Declaramos las constantes de la dimensión de la ventana.
    final int SCENES_TAM_X = 800;
    final int SCENES_TAM_Y = 600;
    
    // Declaramos las constantes de la pala stick.
    final int STICK_WIDTH = 7;
    final int STICK_HEIGHT = 50;
    
    // Declaramos la variable de la pala stick.
    int stickPosY = (SCENES_TAM_Y - STICK_HEIGHT) / 2;
    
    // Declaramos la variable de la posición Y de la pala stick.
    int stickCurrentSpeed = 0;
    
    @Override
    public void start(Stage primaryStage) {
        
        // Declaramos dimensiones de pantalla de 800 x 600 px y color de fondo negro, titulo.
        Pane root = new Pane();
        Scene scene = new Scene(root, SCENES_TAM_X, SCENES_TAM_Y, Color.BLACK);
        primaryStage.setTitle("PongFX");
        primaryStage.setScene(scene);
        primaryStage.show(); 
      
        // Creamos el objeto clase de la Bola, Tamaño, Posicón y color.
        Circle circleBall = new Circle(ballCenterX, ballCenterY, 7, Color.WHITE);
        root.getChildren().add(circleBall);
       
        // Creamos el objeto clase del Rectangulo.
        Rectangle rectStick = new Rectangle(SCENES_TAM_X*0.95, stickPosY, STICK_WIDTH, STICK_HEIGHT);
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
                if(ballCenterX >= SCENES_TAM_X){
                        ballCurrentSpeedX = -3;
                }
                if(ballCenterX <= 0){
                    ballCurrentSpeedX = 3;
                }
                
                circleBall.setCenterY(ballCenterY);
                ballCenterY += ballCurrentSpeedY;
                if(ballCenterY >= SCENES_TAM_Y){
                    ballCurrentSpeedY = -3;
                }
                if(ballCenterY <= 0) {
                    ballCurrentSpeedY = 3;
                }
        // Comineza la sentencia de la playa stick.
                stickPosY += stickCurrentSpeed;
                if(stickPosY < 0){
                    //No Pasar Borde Superior de ventana.
                    stickPosY = 0;
                }
                else {
                    // No Pasar Borde Inferior de ventana.
                    if(stickPosY > SCENES_TAM_Y - STICK_HEIGHT){
                        stickPosY = SCENES_TAM_Y - STICK_HEIGHT;
                    }
                }
                rectStick.setY(stickPosY);
            };
        };
        
        // Comienza la sentencia de los controles.
        // Con esta sentencia responde el movimiento al pulsar las teclas.
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch(event.getCode()){
                case UP:
                    // Pulsa tecla arriba.
                    stickCurrentSpeed = -6;
                    break;
                case DOWN:
                    // Pulsa tecla abajo.
                    stickCurrentSpeed = 6;
                    break;
            }
        });
        
        // Con esta sentencia se para el movimiento al dejar de pulsar las teclas.
        scene.setOnKeyReleased((KeyEvent event) -> {
            // No se pulsa ninguna tecla.
            stickCurrentSpeed = 0;
            
        });

        // Cominezo de la Animación de la bola del juego.
        animationBall.start();
    };

 
}
