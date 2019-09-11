package application;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;

public class GameMenuDemo extends Application {
	
	static Stage window;
	static Scene MainMenuScene, registerScene, LevelSelectScene, Options, mountainScene, easyGame1;
	
	public static void main(String[] args) {
        launch(args);
    }

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(800, 600);
        
        try (InputStream is = Files.newInputStream(Paths.get("images/gamebackground.png"))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(860);
            img.setFitHeight(600);
            root.getChildren().add(img);
        }
        catch (IOException e) {
            System.out.println("Couldn't load image");
        }

        Title title = new Title("Escape Room");
        title.setTranslateX(280);
        title.setTranslateY(50);

        MenuItem Exit_Menu = new MenuItem("Exit");
        Exit_Menu.setOnMouseClicked(event -> System.exit(0));
        
        MenuItem SinglePlayer_Menu = new MenuItem("Single Player");
        SinglePlayer_Menu.setOnMouseClicked(event -> Registration.loadRegistration());

        MenuItem MultiPlayer_Menu = new MenuItem("Multi Player");
        MenuItem Options_Menu = new MenuItem("Options");
        
        Options_Menu.setOnMouseClicked(event -> {
        	OptionsMenu.display();
        });
     

        MenuBox menu = new MenuBox(SinglePlayer_Menu, MultiPlayer_Menu, Options_Menu, Exit_Menu);
        menu.setTranslateX(325);
        menu.setTranslateY(180);

        root.getChildren().addAll(title, menu);
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	window = primaryStage;
    	MainMenuScene = new Scene(this.createContent());
    	
    	LevelSelect selx = new LevelSelect();
    	LevelSelectScene = new Scene(selx.createContent());
    	
    	Registration register = new Registration();
    	GridPane gridPane = register.sendRegistrationForm();
    	register.UISetup(gridPane);
    	registerScene = new Scene(gridPane);
    	
    	MountainLevel mtn_level = new MountainLevel();
    	mountainScene = new Scene(mtn_level.createContent());
    	
    	
        primaryStage.setTitle("Escape Room Release 1");
        primaryStage.setScene(mountainScene);
        primaryStage.show();
    }

    private static class Title extends StackPane {
        public Title(String name) {
        	Ellipse bg = new Ellipse(30, 30, 130, 30);
            bg.setStroke(Color.WHITE);
            bg.setStrokeWidth(3);
            bg.setFill(Color.LIGHTGRAY);

            Text text = new Text(name);
            text.setFill(Color.BLACK);
            text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 50));
            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);
        }
    }

    private static class MenuBox extends VBox {
        public MenuBox(MenuItem... items) { //Putting in how many menuitems you have.
            getChildren().add(createSeparator());

            for (MenuItem item : items) {
                getChildren().addAll(item, createSeparator());
            }
        }

        private Line createSeparator() {
            Line sep = new Line();
            sep.setEndX(175);
            sep.setStroke(Color.WHITE);
            return sep;
        }
    }

    private static class MenuItem extends StackPane {
        public MenuItem(String name) {
            Rectangle bg = new Rectangle(175, 30);
            bg.setOpacity(0.5);

            Text text = new Text(name);
            text.setFill(Color.DARKGREY);
            text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 22));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                bg.setFill(Color.YELLOW);
                text.setFill(Color.BLACK);
            });

            setOnMouseExited(event -> {
                bg.setFill(Color.BLACK);
                text.setFill(Color.DARKGREY);
            });

            setOnMousePressed(event -> {
                bg.setFill(Color.GREEN);
            });

            setOnMouseReleased(event -> {
                bg.setFill(Color.YELLOW);
            });
        }
    }
    
    public static void loadMainMenu() {
    	window.setScene(MainMenuScene);
    }
}