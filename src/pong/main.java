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

import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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
    
    // Declaramos la variable Texto.
    int TEXT_SIZE = 20;
    
    // Declaramos la variable score para la suma de puntos.
    int score = 0;
    
    // Declaramos la variable máxima puntuación.
    int highScore;
    
    // Declaramos la variable textScore. Para usarlo en "start" y en "resetGame".
    Text textScore;
    Text textTitleScore;
    Text textMaxScore;
    Text textTitleMaxScore;
    
    // Declaramos el metodo de reinicio del Juego.
        private void resetGame(){
            score = 0;
            textScore.setText(String.valueOf(score));
            ballCenterX = 10;
            ballCurrentSpeedY = 3;
    // Posicion de inicio de bola aleatoria en el eje Y.
            Random random = new Random();
            ballCenterY = random.nextInt(SCENES_TAM_Y);
        }
       
    
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
        
        // Creamos las lineas de nuetra mesa de juego Red y Linea Horizontal.
        for(int i=0; i<SCENES_TAM_Y; i+=30){
            Line line = new Line(SCENES_TAM_X/2, i, SCENES_TAM_X/2, i+10);
            line.setStroke(Color.WHITE);
            line.setStrokeWidth(4);
            root.getChildren().add(line);
        };
        
        // Creamos los marcadores de máxima puntuación y la puntuación de partida.
        // Creamos el primer LAYOUTS.
        HBox paneScores = new HBox();
        paneScores.setTranslateY(10);
        paneScores.setMinWidth(SCENES_TAM_X);
        paneScores.setAlignment(Pos.CENTER);
        paneScores.setSpacing(0);
        root.getChildren().add(paneScores);
                
        // Creamos el segundo LAYOUTS para la puntuación de partida.
        HBox paneCurrentScores = new HBox();
        paneCurrentScores.setSpacing(80);
        paneScores.getChildren().add(paneCurrentScores);
        
        // Creamos el tercer LAYOUTS para la puntuación máxima de partida.
        HBox paneHighScores = new HBox();
        paneHighScores.setSpacing(60);
        paneScores.getChildren().add(paneHighScores);
        
        // Creamos la Etiqueta texto para la puntuación de partida.
        textTitleScore = new Text("SCORE:");
        textTitleScore.setFont(Font.font(TEXT_SIZE));
        textTitleScore.setFill(Color.WHITE);
        
        // Creamos el Resultado de la puntuación de partida.
        textScore = new Text(String.valueOf(score));
        textScore.setFont(Font.font(TEXT_SIZE));
        textScore.setFill(Color.WHITE); 
       
        // Creamos la Etiqueta para Máxima Puntuación.
        textTitleMaxScore = new Text("MAX SCORE:");
        textTitleMaxScore.setFont(Font.font(TEXT_SIZE));
        textTitleMaxScore.setFill(Color.WHITE);
        
        // Creamos el Resultado de la puntación de Max Puntuación.
        textMaxScore = new Text(String.valueOf(highScore));
        textMaxScore.setFont(Font.font(TEXT_SIZE));
        textMaxScore.setFill(Color.WHITE);
        
        // Añadimos los textos a los LAYOUTS reservados para ellos.
        paneCurrentScores.getChildren().add(textTitleScore);
        paneCurrentScores.getChildren().add(textScore);
        paneCurrentScores.getChildren().add(textTitleMaxScore);
        paneCurrentScores.getChildren().add(textMaxScore);
        
        //Reset del Juego.
        resetGame();
                
        // Creamos la clase animación para el Movimiento de la bola.
        AnimationTimer animationBall = null;
        animationBall = new AnimationTimer(){
            
        @Override
        public void handle(long now) {
                     
        // Sentencia dentro de handle de la colisión.
                Shape shapeColision = Shape.intersect(circleBall, rectStick);
                boolean colisionVacia = shapeColision.getBoundsInLocal().isEmpty();
                    if(colisionVacia == false && ballCurrentSpeedX > 0){
                        //Colisión detectada. Mover Bola hacia la izquierda
                        ballCurrentSpeedX = -3;
                        // Incrememntamos la puntuación actual.
                        score++;
                        textScore.setText(String.valueOf(score));
                    }
                    
        // Comineza la sentencia de la animación de la bola.
                circleBall.setCenterX(ballCenterX);
                ballCenterX += ballCurrentSpeedX;
                if(ballCenterX >= SCENES_TAM_X){
                    if (score > highScore){
                        highScore = score;
                        textMaxScore.setText(String.valueOf(highScore));
                    }
                    resetGame();
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
        
        // Comienza la sentencia de los controles. Responde el movimiento al pulsar las teclas.
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
