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
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LevelSelect {
	private Character ch = Registration.ch;
    protected Parent createContent() {
    	Image img_mtn = null;
    	Image img_frs = null;
    	Image img_des = null;
    	Image img_snw = null;
    	
        Pane root = new Pane();
        root.setPrefSize(800, 600);

        try (   InputStream is_bg = Files.newInputStream(Paths.get("images/gamebackground.png"));
        		InputStream is_mtn = Files.newInputStream(Paths.get("images/mtn2.jpg"));
        		InputStream is_frs = Files.newInputStream(Paths.get("images/frs1.png"));
        		InputStream is_des = Files.newInputStream(Paths.get("images/des1.jpg"));
        		InputStream is_snw = Files.newInputStream(Paths.get("images/snw1.jpg"))
        		) {
            ImageView img_bg = new ImageView(new Image(is_bg));
            img_bg.setFitWidth(800);
            img_bg.setFitHeight(600);
            img_mtn = new Image(is_mtn);
            img_frs = new Image(is_frs);
            img_des = new Image(is_des);
            img_snw = new Image(is_snw);
            root.getChildren().add(img_bg);
        }
        catch (IOException e) {
            System.out.println("Couldn't load image");
        }

        Button back = new Button("Back");
        back.setOnMouseClicked(event -> GameMenuDemo.loadMainMenu());
        back.setTranslateX(15);
        back.setTranslateY(560);
        
        Button exit = new Button("Exit");
        exit.setOnMouseClicked(event -> System.exit(0));
        exit.setTranslateX(750);
        exit.setTranslateY(560);
        
        LevelMenuItem MOUNTAINLevel = new LevelMenuItem("M O U N T A I N     P E A K");
        MOUNTAINLevel.bg.setFill(new ImagePattern(img_mtn));
        MountainLevel mt = new MountainLevel();
        MOUNTAINLevel.setOnMouseClicked(event -> mt.loadMountainScene(ch));
        
        LevelMenuItem FORESTLevel = new LevelMenuItem("L E A F Y     W O O D L A N D");
        FORESTLevel.bg.setFill(new ImagePattern(img_frs));
        FORESTLevel.setOnMouseClicked(event -> System.exit(0));
        
        LevelMenuItem DESERTLevel = new LevelMenuItem("B A R R E N     D E S E R T");
        DESERTLevel.bg.setFill(new ImagePattern(img_des));
        DESERTLevel.setOnMouseClicked(event -> System.exit(0));
        
        LevelMenuItem SNOWLevel = new LevelMenuItem("A R C T I C     T U N D R A");
        SNOWLevel.bg.setFill(new ImagePattern(img_snw));
        SNOWLevel.setOnMouseClicked(event -> System.exit(0));
        
        LevelMenuBox level_menu = new LevelMenuBox(MOUNTAINLevel, FORESTLevel, DESERTLevel, SNOWLevel);
        level_menu.setTranslateX(100);
        level_menu.setTranslateY(50);
        
        root.getChildren().addAll(exit, back, level_menu);
        return root;
    }
    
    private static class LevelMenuItem extends StackPane {
    	private Rectangle bg;
        public LevelMenuItem(String name) {
            bg = new Rectangle(600, 75);
            bg.setOpacity(0.7);
            bg.setArcWidth(50);
            bg.setArcHeight(50);
            
            Text text = new Text(name);
            text.setFill(Color.BLACK);
            text.setOpacity(0.7);
            text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 38));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
            	final Effect glow = new Glow(1.0);
                text.setEffect(glow);
                text.setFill(Color.RED);
                text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 45));
            });

            setOnMouseExited(event -> {
            	final Effect glow = new Glow(0.0);
                text.setEffect(glow);
                text.setFill(Color.BLACK);
                text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 38));
            });

            setOnMousePressed(event -> {
            	DropShadow glowBorder = new DropShadow();
            	glowBorder.setColor(Color.LIGHTGRAY);
            	glowBorder.setSpread(0.7);
            	this.setEffect(glowBorder);
            });
            
            

            setOnMouseReleased(event -> {
                this.setEffect(null);
            });
        }
    }
    
    private static class LevelMenuBox extends VBox {
        public LevelMenuBox(LevelMenuItem... items) {
            getChildren().add(createSeparator());

            for (LevelMenuItem item : items) {
                getChildren().addAll(item, createSeparator());
            }
        }

        private Line createSeparator() {
            Line sep = new Line();
            sep.setEndX(600);
            sep.setEndY(15);
            sep.setVisible(false);
            return sep;
        }
    }

    public static void loadLevelSelect() {
    	GameMenuDemo.window.setScene(GameMenuDemo.LevelSelectScene);
    }
}

