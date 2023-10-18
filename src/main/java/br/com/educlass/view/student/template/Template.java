package br.com.educlass.view.student.template;

import br.com.educlass.view.student.extra.ExtraController;
import br.com.educlass.view.student.grades.Grades;
import br.com.educlass.util.CursorUtil;
import br.com.educlass.util.Language;
import br.com.educlass.util.UserUtil;
import br.com.educlass.model.person.student.Student;
import br.com.educlass.service.student.StudentService;
import br.com.educlass.view.student.frequency.Frequency;
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

public class Template extends Application implements Initializable {

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
    private Pane extraOptionContainer;

    @FXML
    private Text frequencyText;

    @FXML
    private Text gradesText;

    @FXML
    private Text extraText;

    @FXML
    private AnchorPane contentContainer; // w=450 h=400

    private void setUserPicture() {
        this.pathUser = UserUtil.getPathUser();

        try {
            Image image = new Image("file:" + pathUser + "photo.jpeg", false);
            userImageContainer.setFill(new ImagePattern(image));
        } catch (Exception e) {
            Image image = new Image("file:src/main/resources/br/com/educlass/images/userIcon.png", false);
            userImageContainer.setFill(new ImagePattern(image));
        }

    }

    private void setLanguage() {
        Student student = StudentService.setStudentInfo();
        HashMap<String, String> texts = Language
                .getTexts("src/main/resources/br/com/educlass/view/student/template/languages/");

        name.setText(texts.get("name") + ": " + student.getName());

        course.setText(texts.get("course") + ": " + student.getCourseId().toUpperCase());
        register.setText(texts.get("register") + ": " + student.getRegistration());
        frequencyText.setText(texts.get("frequencyText"));
        gradesText.setText(texts.get("gradesText"));

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
        extraOptionContainer.setStyle("-fx-background-color:hover: #D1D1D1");

        URL fxmlURl = Frequency.class.getResource("frequency.fxml");

        if (fxmlURl != null) {
            Parent root = FXMLLoader.load(fxmlURl);
            contentContainer.getChildren().add(root);
        }
    }

    @FXML
    protected void notesOptionSelect() throws IOException {
        contentContainer.getChildren().clear();
        frequencyOptionContainer.setStyle("-fx-background-color:hover: #D1D1D1");
        gradesOptionContainer.setStyle("-fx-background-color: white");
        extraOptionContainer.setStyle("-fx-background-color:hover: #D1D1D1");

        Parent root = FXMLLoader.load(Grades.class.getResource("grades.fxml"));
        contentContainer.getChildren().add(root);
    }

    @FXML
    protected void extraOptionSelect() throws IOException {
        contentContainer.getChildren().clear();
        extraOptionContainer.setStyle("-fx-background-color: white");
        gradesOptionContainer.setStyle("-fx-background-color:hover: #D1D1D1");
        frequencyOptionContainer.setStyle("-fx-background-color:hover: #D1D1D1");

        URL fxmlURl = ExtraController.class.getResource("extra.fxml");

        if (fxmlURl != null) {
            Parent root = FXMLLoader.load(fxmlURl);
            contentContainer.getChildren().add(root);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CursorUtil.setMainPane(this.templatePane);
        setUserPicture();
        setLanguage();
    }

}
