package br.com.educlass.view.configuration;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class ConfigurationIcon {
    public static void insertConfigurationIcon(Circle configurationContainer) {
        try {
            Image image = new Image("file:src/main/resources/br/com/educlass/view/configuration/configuration_icon.png",
                    false);
            configurationContainer.setFill(new ImagePattern(image));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
