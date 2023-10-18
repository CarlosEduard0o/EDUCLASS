package br.com.educlass.view.adm.student.addStudent.studentConfirmation;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.service.admnistrator.AdmnistratorService;
import br.com.educlass.util.ContentContainer;
import br.com.educlass.util.Language;
import br.com.educlass.util.TextFile;
import br.com.educlass.util.UserUtil;
import br.com.educlass.view.adm.student.StudentController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class StudentConfirmationController implements Initializable {
    @FXML
    private Text textTitle;

    @FXML
    private Circle profilePicture;

    @FXML
    private Text textName;
    @FXML
    private Text textCpf;
    @FXML
    private Text textAddress;
    @FXML
    private Text textEmail;
    @FXML
    private Text textRegistration;
    @FXML
    private Text textPassword;

    @FXML
    private void okButtonPressed() throws IOException {
        URL fxml = StudentController.class.getResource("students.fxml");
        ContentContainer.setSceneContentContainer(fxml);
    }

    private void setLanguage(Student student, ArrayList<String> fileLines, HashMap<String, String> result ) {
        HashMap<String, String> texts = Language
                .getTexts("src/main/resources/br/com/educlass/view/adm/student/addStudent/studentConfirmation/languages/");
        textTitle.setText(texts.get("textTitle"));

        textName.setText(texts.get("textName")+": " + student.getName());
        textCpf.setText(texts.get("textCpf")+": " + student.getCpf());;
        textAddress.setText(texts.get("textAddress")+": " + student.getAddress());;
        textEmail.setText(texts.get("textEmail")+": " + student.getEmail());
        textRegistration.setText(texts.get("textRegistration")+ ": "+ student.getRegistration());

        for (String s : fileLines) {
            String[] lineSplited = s.split(":");
            result.put(lineSplited[0], lineSplited[1]);
        }

        textPassword.setText(texts.get("textPassword") + ": " + result.get("password"));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Student student = AdmnistratorService.getNewStudent();

        String year = student.getRegistration().substring(1, 5);
        String semester = student.getRegistration().substring(0, 1);
        String registration = student.getRegistration().substring(5);

        String path = "db/users/students/" + year + "/" + semester + "/" + registration + "/login.txt";
        TextFile textFile = new TextFile();
        ArrayList<String> fileLines = textFile.readTextFile(path);
        HashMap<String, String> result = new HashMap<>();


        try {
            String imagePath = "db/users/students/" + year + "/" + semester + "/" + registration + "/photo.jpeg";
            File file = new File(imagePath);
            if(file.exists()) {
                Image image = new Image("file:"+imagePath, false);
                profilePicture.setFill(new ImagePattern(image));
            } else {
                Image image = new Image(
                        "file:src/main/resources/br/com/educlass/images/userIcon.png",
                        false);
                profilePicture.setFill(new ImagePattern(image));
            }
        } catch (Exception e) {
            Image image = new Image("file:src/main/resources/br/com/educlass/images/userIcon.png",
                    false);
            profilePicture.setFill(new ImagePattern(image));
        }

        setLanguage(student, fileLines, result);

    }
}
