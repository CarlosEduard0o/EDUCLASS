package br.com.educlass.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.net.URL;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchScene(Window window, URL urlFXML) {
        FXMLLoader loader = new FXMLLoader(urlFXML);
        try {
            root = loader.load();
            stage = (Stage) window;
            stage.setTitle("EDU CLASS");
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
