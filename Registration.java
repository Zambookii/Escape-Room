package application;


import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Window;

public class Registration {
	private static boolean created = false;
	//private static ArrayList<Character> allCharacters = new ArrayList<Character>();
	public static Character ch;
	
	protected GridPane sendRegistrationForm() {
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(500, 200);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-background-image: url('file:images/gamebackground.png')");

        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }
	
	protected void UISetup(GridPane gridPane) {
        Label headerLabel = new Label("Registration");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Account username
        Label nameLabel = new Label("Username: ");
        nameLabel.setFont(Font.font("Courier", FontWeight.BOLD, 12));
        gridPane.add(nameLabel, 0,1);

        // Account username txt
        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1,1);

        // Account password
        Label passwordLabel = new Label("Password: ");
        passwordLabel.setFont(Font.font("Courier", FontWeight.BOLD, 12));
        gridPane.add(passwordLabel, 0, 2);

        // Account password txt
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 2);
        
        // Character name
        Label charLabel = new Label("Character Name: ");
        charLabel.setFont(Font.font("Courier", FontWeight.BOLD, 12));
        gridPane.add(charLabel, 0, 3);

        // Character name txt
        TextField charField = new TextField();
        charField.setPrefHeight(40);
        gridPane.add(charField, 1, 3);

        // Create button
        Button createButton = new Button("Create");
        createButton.setPrefHeight(40);
        createButton.setDefaultButton(true);
        createButton.setPrefWidth(90);
        gridPane.add(createButton, 0, 4, 2, 1);
        GridPane.setHalignment(createButton, HPos.CENTER);
        GridPane.setMargin(createButton, new Insets(20, 0,20,0));

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(nameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Invalid id", "Enter account username");
                    return;
                }
                if(passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Invalid pass", "Enter account password");
                    return;
                }
                if(charField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Invalid character name", "Enter a character name");
                    return;
                }
                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Character created!", "Welcome " + charField.getText() + "!");
                created = true;
                Character newChar = new Character(nameField.getText(), passwordField.getText(), charField.getText());
                LevelSelect.loadLevelSelect();
                ch = newChar;
                //allCharacters.add(newChar);
            }
        });
    }
	
	private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
	
	public static void loadRegistration() {
		if(created == false) {
			GameMenuDemo.window.setScene(GameMenuDemo.registerScene);
		} else {
			GameMenuDemo.window.setScene(GameMenuDemo.LevelSelectScene);
		}
    }
	
	/*public static ArrayList<Character> getAllCharacters() {
		return allCharacters;
	}*/
}
