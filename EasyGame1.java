package application;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import com.sun.glass.ui.MenuBar;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EasyGame1 extends Application implements GameHub {
	private String gameName;
	private int keyRequirement;
	private Scene easyGame1;
	private Character ch;
	private Image img = null;
	private Stage newEasyGame1Stage;
	private Stage prevStage;
	private AnimationTimer timer;
	private static final double W = 600, H = 800;
    private Image CHAR_IMAGE = null;
    private Node  hero;

    boolean running, goEast, goWest, endGame;
	
	public EasyGame1(String gameName, int keyRequirement, Character ch) {
		this.ch = ch;
		this.keyRequirement = keyRequirement;
		this.gameName = gameName;
	}
	
	public int getKeyRequirement() {
		return keyRequirement;
	}


	public void start(Stage primaryStage) throws Exception {
		prevStage = primaryStage;
		newEasyGame1Stage = new Stage();
		newEasyGame1Stage.initModality(Modality.APPLICATION_MODAL);
		newEasyGame1Stage.setTitle("Options");
		
		//createWindow();
        //initButtons();
        //newGame();
        //newgamebutton.setOnAction(event -> newGame());
        //back.setOnAction(event -> rewind());
        //exitbutton.setOnAction(event -> primaryStage.close());
		try (InputStream is = Files.newInputStream(Paths.get("images/mtn_gamebg_2.jpg"));
			 InputStream is_CHAR_IMAGE = Files.newInputStream(Paths.get("images/Player_Images/Knight1/_WALK_002_V1.png"))	
		    ) {
            img = new Image(is);
            CHAR_IMAGE = new Image(is_CHAR_IMAGE);
        }
        catch (IOException e) {
            System.out.println("Couldn't load image");
        }
		
		Button back = new Button("Back");
		back.setOnMouseClicked(event -> stopGame()); //save first 
		back.setTranslateX(15);
		back.setTranslateY(760);
		
        hero = new ImageView(CHAR_IMAGE);

        Group root = new Group(hero, back);
		easyGame1 = new Scene(root, 600, 800);
		easyGame1.setFill(new ImagePattern(img));

        moveHeroBy(0,710);
        
        easyGame1.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT:  goWest  = true; break;
                    case RIGHT: goEast  = true; break;
                    case SHIFT: running = true; break;
				default:
					break;
                }
            }
        });

        easyGame1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT:  goWest  = false; break;
                    case RIGHT: goEast  = false; break;
                    case SHIFT: running = false; break;
				default:
					break;
                }
            }
        });
        
        newEasyGame1Stage.setScene(easyGame1);
        prevStage.hide();
        newEasyGame1Stage.show();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;
                if(endGame == true) {
                	stopGame();
                } else {
                if (goEast)  dx += 2;
                if (goWest)  dx -= 2;
                if (running) {
                	dx *= 5; dy *= 5; 
                }
                
                moveHeroBy(dx, dy);
                }
            }
        };
        
        timer.start();
	}
	
	private void stopGame() {
		System.out.println("Ending game");
		timer.stop();
		moveHeroBy(0,0);
		running = false; goEast = false; goWest = false; endGame = false;
		GameMenuDemo.window.setScene(GameMenuDemo.mountainScene);
		newEasyGame1Stage.close();
		prevStage.show();
	}
	
	private void moveHeroBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = hero.getBoundsInLocal().getWidth()  / 2;
        final double cy = hero.getBoundsInLocal().getHeight() / 2;

        double x = cx + hero.getLayoutX() + dx;
        double y = cy + hero.getLayoutY() + dy;
        moveHeroTo(x, y);
    }

    private void moveHeroTo(double x, double y) {
        final double cx = hero.getBoundsInLocal().getWidth()  / 2;
        final double cy = hero.getBoundsInLocal().getHeight() / 2;
        hero.relocate(x - cx, y - cy);
        System.out.println(hero.getLayoutX());
        if (x - cx >= 0 &&
            x + cx <= W-200 &&
            y - cy >= 0 &&
            y + cy <= H-200) {
            /*if(hero.getLayoutX() >= 150.0) {
            	endGame = true;
            }*/
        }
    }

}
