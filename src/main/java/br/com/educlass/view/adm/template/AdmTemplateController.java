package br.com.educlass.view.adm.template;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdmTemplateController extends Application implements Initializable {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("template_adm.fxml"));
        primaryStage.setTitle("EDU CLASS");

        Image icon = new Image("file:src/main/resources/br/com/educlass/images/icon.png");
        primaryStage.getIcons().add(icon);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @FXML
    private Circle userImageContainer;

    @FXML
    private Text schoolNameText;

    @FXML
    private Text registrationText;

    @FXML
    private Pane teacherOptionContainer;

    @FXML
    private Text teatchersTexts;

    @FXML
    private Pane studentsOptionContainer;

    @FXML
    private Text studentsTexts;

    @FXML
    private Pane subjectsOptionContainer;

    @FXML
    private Text subjectsTexts;

    @FXML
    private Button exitButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
