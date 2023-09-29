package br.com.educlass.view.adm.template;

import br.com.educlass.model.institution.Institution;
import br.com.educlass.service.admnistrator.AdmnistratorService;
import br.com.educlass.util.ContentContainer;
import br.com.educlass.util.CursorUtil;
import br.com.educlass.util.UserUtil;
import br.com.educlass.view.adm.student.StudentController;
import br.com.educlass.view.adm.student.editStudent.EditStudentController;
import br.com.educlass.view.adm.teacher.TeacherController;
import br.com.educlass.view.student.frequency.Frequency;
import br.com.educlass.view.student.grades.Grades;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOError;
import java.io.IOException;
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

    private String pathUser;

    @FXML
    private AnchorPane templatePane;

    @FXML
    private Circle userImageContainer;

    @FXML
    private Text schoolNameText;

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

    @FXML
    private AnchorPane contentContainer; // w=450 h=400

    private Institution institution;

    @FXML
    private void handleCursor() {
        CursorUtil.handleCursorType(Cursor.WAIT);
    }

    @FXML
    private void teacherOptionSelect() throws IOException {
        contentContainer.getChildren().clear();
        studentsOptionContainer.setStyle("-fx-background-color:hover: #D1D1D1");
        subjectsOptionContainer.setStyle("-fx-background-color:hover: #D1D1D1");
        teacherOptionContainer.setStyle("-fx-background-color: white");

        URL fxml = TeacherController.class.getResource("teacher.fxml");
        ContentContainer.setSceneContentContainer(fxml);
    }

    @FXML
    private void studentOptionSelect() throws IOException {
        studentsOptionContainer.setStyle("-fx-background-color: white");
        subjectsOptionContainer.setStyle("-fx-background-color:hover: #D1D1D1");
        teacherOptionContainer.setStyle("-fx-background-color:hover: #D1D1D1");

        URL fxml = StudentController.class.getResource("students.fxml");
        ContentContainer.setSceneContentContainer(fxml);
    }

    @FXML
    private void subjectOptionSelect() {
        contentContainer.getChildren().clear();
        studentsOptionContainer.setStyle("-fx-background-color:hover: #D1D1D1");
        subjectsOptionContainer.setStyle("-fx-background-color: white");
        teacherOptionContainer.setStyle("-fx-background-color:hover: #D1D1D1");
    }

    private void setAdmnistratorInfo() {
        this.pathUser = "db/users/_school/";

        try {
            Image image = new Image("file:" + pathUser + "photo.jpeg", false);
            userImageContainer.setFill(new ImagePattern(image));
        } catch (Exception e) {
            Image image = new Image("file:src/main/resources/br/com/educlass/images/userIcon.png", false);
            userImageContainer.setFill(new ImagePattern(image));
        }

        this.institution = AdmnistratorService.setAdmnistratorInfo();

        schoolNameText.setText("Nome : " + institution.getName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ContentContainer.setContentContainer(contentContainer);
        CursorUtil.setMainPane(this.templatePane);
        setAdmnistratorInfo();
    }
}
