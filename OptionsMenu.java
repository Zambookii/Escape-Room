package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OptionsMenu {
	public static void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Options");
		window.setMinWidth(500);
		Label label = new Label();
		label.setText("Add options / other thing?");
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label);
		layout.setAlignment(Pos.CENTER);
		layout.setMinSize(300, 300);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}

}
