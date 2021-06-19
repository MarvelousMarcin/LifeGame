import controllers.Menu;
import controllers.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu.fxml"));

        try {
            Parent root = loader.load();

            Player player = new Player();
            Menu menu = loader.getController();
            menu.setPlayer(player);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/stylesheets/styles.css").toExternalForm());
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Game");

            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
