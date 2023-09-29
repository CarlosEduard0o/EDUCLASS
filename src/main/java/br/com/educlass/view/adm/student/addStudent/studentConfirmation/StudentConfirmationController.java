package br.com.educlass.view.adm.student.addStudent.studentConfirmation;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.service.admnistrator.AdmnistratorService;
import br.com.educlass.util.ContentContainer;
import br.com.educlass.util.TextFile;
import br.com.educlass.util.UserUtil;
import br.com.educlass.view.adm.student.StudentController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class StudentConfirmationController implements Initializable {
    @FXML
    private Text textTitle;

    @FXML
    private ImageView profilePicture;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Student student = AdmnistratorService.getNewStudent();
        textName.setText("Nome: " + student.getName());
        textCpf.setText("CPF: " + student.getCpf());
        ;
        textAddress.setText("Endereço: " + student.getAddress());
        ;
        textEmail.setText("Email: " + student.getEmail());
        ;
        textRegistration.setText("Matrícula: " + student.getRegistration());
        String year = student.getRegistration().substring(1, 5);
        String semester = student.getRegistration().substring(0, 1);
        String registration = student.getRegistration().substring(5);

        String path = "db/users/students/" + year + "/" + semester + "/" + registration + "/login.txt";
        TextFile textFile = new TextFile();
        ArrayList<String> fileLines = textFile.readTextFile(path);
        HashMap<String, String> result = new HashMap<>();

        for (String s : fileLines) {
            String[] lineSplited = s.split(":");
            result.put(lineSplited[0], lineSplited[1]);
        }

        textPassword.setText("Senha: " + result.get("password"));
        ;
    }
}
