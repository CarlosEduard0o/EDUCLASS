package br.com.educlass.view.configuration;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfigurationView {
    public static void openModalSettings() {
        try {
            FXMLLoader loader = new FXMLLoader(ConfigurationController.class.getResource("configurationView.fxml"));
            Parent root = loader.load();

            Stage telaSecundaria = new Stage();
            telaSecundaria.setTitle("EDU CLASS");
            telaSecundaria.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(root);
            telaSecundaria.setScene(scene);

            telaSecundaria.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
