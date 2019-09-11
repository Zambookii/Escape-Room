package application;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;

public class MountainLevel {
	private Character ch;
	EasyGame1 startGame1;
	
    protected Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(800, 600);
        Image img_button1 = null;
        Image img_button2 = null;
        Image img_button3 = null;
        Image img_button4 = null;
        Image img_button5 = null;
        Image img_button6 = null;
        
        try (InputStream is_bg = Files.newInputStream(Paths.get("images/mtn_bg_2.jpg"));
        	 InputStream is_button1 = Files.newInputStream(Paths.get("images/reg_buttons/2.1.png"));
        	 InputStream is_button2 = Files.newInputStream(Paths.get("images/reg_buttons/4.1.png"));
        	 InputStream is_button3 = Files.newInputStream(Paths.get("images/reg_buttons/3.1.png"));
        	 InputStream is_button4 = Files.newInputStream(Paths.get("images/reg_buttons/7.1.png"));
        	 InputStream is_button5 = Files.newInputStream(Paths.get("images/reg_buttons/10.1.png"));
        	 InputStream is_button6 = Files.newInputStream(Paths.get("images/reg_buttons/8.1.png"))
        		) {
            ImageView img_bg = new ImageView(new Image(is_bg));
            img_bg.setFitWidth(860);
            img_bg.setFitHeight(600);
            img_button1 = new Image(is_button1);
            img_button2 = new Image(is_button2);
            img_button3 = new Image(is_button3);
            img_button4 = new Image(is_button4);
            img_button5 = new Image(is_button5);
            img_button6 = new Image(is_button6);
            root.getChildren().add(img_bg);
        }
        catch (IOException e) {
            System.out.println("Couldn't load image");
        }
        
        Button back = new Button("Back");
        back.setOnMouseClicked(event -> LevelSelect.loadLevelSelect());
        back.setTranslateX(15);
        back.setTranslateY(560);
        
        Button exit = new Button("Exit");
        exit.setOnMouseClicked(event -> System.exit(0));
        exit.setTranslateX(750);
        exit.setTranslateY(560);
        
        LevelMenuItem easy_1 = new LevelMenuItem("Level 1");
        easy_1.setTranslateX(75);
        easy_1.setTranslateY(75);
        easy_1.bg.setFill(new ImagePattern(img_button1));
        //EasyGame1 startGame1;// = new EasyGame1("Game1", 1, ch); // TODO: set which character it is. Not tracking right now!
        easy_1.setOnMouseClicked(event -> {
			try {
				startGame1 = new EasyGame1("Game1", 1, ch);
				startGame1.start(GameMenuDemo.window);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
        
        LevelMenuItem easy_2 = new LevelMenuItem("Level 2");
        easy_2.setTranslateX(297);
        easy_2.setTranslateY(75);
        easy_2.bg.setFill(new ImagePattern(img_button2));
        easy_2.setOnMouseClicked(event -> System.exit(0));
        
        LevelMenuItem easy_3 = new LevelMenuItem("Level 3");
        easy_3.setTranslateX(519);
        easy_3.setTranslateY(75);
        easy_3.bg.setFill(new ImagePattern(img_button3));
        easy_3.setOnMouseClicked(event -> System.exit(0));
        
        LevelMenuItem normal_1 = new LevelMenuItem("Level 4");
        normal_1.setTranslateX(180);
        normal_1.setTranslateY(200);
        normal_1.bg.setFill(new ImagePattern(img_button4));
        normal_1.setOnMouseClicked(event -> System.exit(0));
        
        LevelMenuItem normal_2 = new LevelMenuItem("Level 5");
        normal_2.setTranslateX(410);
        normal_2.setTranslateY(200);
        normal_2.bg.setFill(new ImagePattern(img_button5));
        normal_2.setOnMouseClicked(event -> System.exit(0));
        
        LevelMenuItem hard_1 = new LevelMenuItem("Final");
        hard_1.setTranslateX(290);
        hard_1.setTranslateY(325);
        hard_1.bg.setFill(new ImagePattern(img_button6));
        hard_1.setOnMouseClicked(event -> System.exit(0));

        root.getChildren().addAll(back, exit, easy_1, easy_2, easy_3, normal_1, normal_2, hard_1);
        return root;
    }
    
    private static class LevelMenuItem extends StackPane {
    	private Rectangle bg;
        public LevelMenuItem(String name) {
            bg = new Rectangle(187,85);
            
            Text text = new Text(name);
            text.setFill(Color.BLACK);
            text.setOpacity(0.5);
            text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 40));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
            	final Effect glow = new Glow(0.3);
            	bg.setEffect(glow);
                text.setFill(Color.RED);
                text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 40));
            });

            setOnMouseExited(event -> {
            	final Effect glow = new Glow(0.0);
            	bg.setEffect(glow);
                text.setFill(Color.BLACK);
                text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 40));
            });
        }
    }
    
    public void loadMountainScene(Character ch) {
    	this.ch = ch;
    	GameMenuDemo.window.setScene(GameMenuDemo.mountainScene);
    }
}
