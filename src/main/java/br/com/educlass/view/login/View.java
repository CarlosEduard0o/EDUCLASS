package br.com.educlass.view.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class View extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        primaryStage.setTitle("EDU CLASS");

        Image icon = new Image("file:src/main/resources/br/com/educlass/images/icon.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
//        TextFile textFile = new TextFile();
//        String path = "db/cache/user";
//        String content = "";
//        textFile.writeTextFile(path, content);
    }
}
