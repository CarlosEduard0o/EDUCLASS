package br.com.educlass.view.adm.teacher.addTeacher.teacherConfirmation;

import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.service.admnistrator.AdmnistratorService;
import br.com.educlass.util.ContentContainer;
import br.com.educlass.util.TextFile;
import br.com.educlass.view.adm.teacher.TeacherController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TeacherConfirmationController implements Initializable {
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
        URL fxml = TeacherController.class.getResource("teacher.fxml");
        ContentContainer.setSceneContentContainer(fxml);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Teacher teacher = AdmnistratorService.getNewTeacher();
        textName.setText("Nome: " + teacher.getName());
        textCpf.setText("CPF: " + teacher.getCpf());
        ;
        textAddress.setText("Endereço: " + teacher.getAddress());
        ;
        textEmail.setText("Email: " + teacher.getEmail());
        ;
        textRegistration.setText("Matrícula: " + teacher.getRegistration());
        String registration = teacher.getRegistration().substring(1);

        String path = "db/users/teachers/" + registration + "/login.txt";
        TextFile textFile = new TextFile();
        ArrayList<String> fileLines = textFile.readTextFile(path);
        HashMap<String, String> result = new HashMap<>();


        try {
            String imagePath = "db/users/teachers/" + registration + "/photo.jpeg";
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


        for (String s : fileLines) {
            String[] lineSplited = s.split(":");
            result.put(lineSplited[0], lineSplited[1]);
        }

        textPassword.setText("Senha: " + result.get("password"));
        ;
    }
}
