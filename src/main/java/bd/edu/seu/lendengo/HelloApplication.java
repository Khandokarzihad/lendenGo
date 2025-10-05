package bd.edu.seu.lendengo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage stage;

    @Override
    public void init(){
        Font.loadFont(getClass().getResourceAsStream("/bd/edu/seu/lendengo/fonts/Roboto-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("/bd/edu/seu/lendengo/fonts/Montserrat-Regular.ttf"), 10);
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/bd/edu/seu/lendengo/fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1515, 800);
        scene.getStylesheets().add(getClass().getResource("/bd/edu/seu/lendengo/stylesheets/Login.css").toExternalForm());
        stage.setTitle("লেনদেনGO");
        stage.setScene(scene);
        stage.show();
    }


    public void changeScene(String fxml, String css){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/bd/edu/seu/lendengo/fxml/" + fxml + ".fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1500, 800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene.getStylesheets().add(getClass().getResource("/bd/edu/seu/lendengo/stylesheets/" + css + ".css").toExternalForm());
        stage.setTitle("লেনদেনGO");
        stage.setScene(scene);
        stage.show();
    }
}
