package br.com.educlass.view.teacher.template;

import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.service.teacher.TeacherService;
import br.com.educlass.util.CursorUtil;
import br.com.educlass.util.Language;
import br.com.educlass.util.SceneController;
import br.com.educlass.util.UserUtil;
import br.com.educlass.view.login.Controller;
import br.com.educlass.view.teacher.frequency.FrequencyTeacher;
import br.com.educlass.view.teacher.grades.GradesTeacher;
import br.com.educlass.view.teacher.template.informations.TeacherInformationsController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TemplateTeacher extends Application implements Initializable{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("template.fxml"));
        primaryStage.setTitle("EDU CLASS");

        Image icon = new Image("file:src/main/resources/br/com/educlass/images/icon.png");
        primaryStage.getIcons().add(icon);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    String pathUser;

    @FXML
    private AnchorPane templatePane;

    @FXML
    private Circle userImageContainer;

    @FXML
    private Text name;

    @FXML
    private Text course;

    @FXML
    private Text register;

    @FXML
    private Pane frequencyOptionContainer;

    @FXML
    private Pane gradesOptionContainer;

    @FXML
    private Text frequencyText;

    @FXML
    private Text gradesText;

    @FXML
    private AnchorPane contentContainer; //w=450 h=400


    private void setLanguage() {
        Teacher teacher = TeacherService.setTeacherInfo();
        HashMap<String, String> texts = Language.getTexts("src/main/resources/br/com/educlass/view/teacher/template/languages/");
        name.setText(texts.get("name")+": " + teacher.getName().split(" ")[0]);
//        course.setText(texts.get("course")+": " + teacher.getRegistration());
        register.setText(texts.get("register")+": " + teacher.getRegistration());
        frequencyText.setText(texts.get("frequencyText"));
        gradesText.setText(texts.get("gradesText"));

    }

    @FXML
    private void exitButtonPressed() {
        SceneController sceneController = new SceneController();

        sceneController.switchScene(templatePane.getScene().getWindow(),
                Controller.class.getResource("view.fxml"));
    }

    private void setUserPicture() {
        this.pathUser = UserUtil.getPathTeacher();

        try {
            Image image = new Image("file:"+pathUser+"photo.jpeg", false);
            userImageContainer.setFill(new ImagePattern(image));
        } catch (Exception e) {
            Image image = new Image("file:src/main/resources/br/com/educlass/images/userIcon.png", false);
            userImageContainer.setFill(new ImagePattern(image));
        }

    }

    @FXML
    protected void handleCursor() {
        CursorUtil.handleCursorType(Cursor.WAIT);
    }

    @FXML
    protected void frequencyOptionSelect() throws IOException {
        contentContainer.getChildren().clear();
        frequencyOptionContainer.setStyle("-fx-background-color: white");
        gradesOptionContainer.setStyle("-fx-background-color:hover: #D1D1D1");

        URL fxmlURl = FrequencyTeacher.class.getResource("frequency.fxml");
        if(fxmlURl != null) {
            Parent root = FXMLLoader.load(fxmlURl);
            contentContainer.getChildren().add(root);
        }
    }

    @FXML
    protected void notesOptionSelect() throws IOException{
        contentContainer.getChildren().clear();
        frequencyOptionContainer.setStyle("-fx-background-color:hover: #D1D1D1");
        gradesOptionContainer.setStyle("-fx-background-color: white");
        Parent root = FXMLLoader.load(GradesTeacher.class.getResource("grades.fxml"));
        contentContainer.getChildren().add(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CursorUtil.setMainPane(this.templatePane);
        setLanguage();
        setUserPicture();
        try {
            Parent root = FXMLLoader.load(TeacherInformationsController.class.getResource("informations.fxml"));
            contentContainer.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
