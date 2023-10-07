package br.com.educlass.util;

import br.com.educlass.view.student.frequency.Frequency;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ContentContainer {
    private static AnchorPane contentContainer;

    public static void setContentContainer(AnchorPane pane){
        contentContainer = pane;
    }

    public static AnchorPane getContentContainer() {
        return contentContainer;
    }

    /**
     *
     * @param fxmlURl = Frequency.class.getResource("frequency.fxml");
     */
    public static void setSceneContentContainer (URL fxmlURl) throws IOException {
        contentContainer.getChildren().clear();
        if(fxmlURl != null) {
            Parent root = FXMLLoader.load(fxmlURl);
            contentContainer.getChildren().add(root);
        }
    }
}
