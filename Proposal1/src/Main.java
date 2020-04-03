import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
		primaryStage.setTitle("Hello World");
		primaryStage.setScene(new Scene(root, 830, 380));
		primaryStage.show();
		TimeUnit.SECONDS.sleep(5);
		primaryStage.close();
		Parent root2 = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
		primaryStage.setTitle("Game");
		primaryStage.setScene(new Scene(root2, 830, 380));
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}
