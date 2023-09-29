package br.com.educlass.view.adm.student.addStudent;

import br.com.educlass.util.ContentContainer;
import br.com.educlass.view.adm.student.StudentController;
import br.com.educlass.view.adm.student.addStudent.studentConfirmation.StudentConfirmationController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AddStudentController implements Initializable {
    @FXML
    private Text textTitle;
    @FXML
    private Text textName;
    @FXML
    private Text textCpf;
    @FXML
    private Text textAddress;
    @FXML
    private Text textEmail;
    @FXML
    private Text textPassword;

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldCpf;
    @FXML
    private TextField textFieldAddress;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldPassword;

    @FXML
    protected void buttonCancelPressed() throws IOException {
        URL fxml = StudentController.class.getResource("students.fxml");
        ContentContainer.setSceneContentContainer(fxml);
    }

    private boolean inputsVerifications() {
        return textFieldName.getText().length() > 0 &&
                textFieldCpf.getText().length() >0 &&
                textFieldAddress.getText().length() > 0 &&
                textFieldEmail.getText().length() > 0 &&
                textFieldPassword.getText().length() > 0;
    }

    @FXML
    protected void buttonSavePressed() {
        if( inputsVerifications()) {
            HashMap<String,String> userInformations = new HashMap<>();
            userInformations.put("name", textFieldName.getText());
            userInformations.put("cpf", textFieldCpf.getText());
            userInformations.put("address", textFieldAddress.getText());
            userInformations.put("email", textFieldEmail.getText());
            userInformations.put("password", textFieldPassword.getText());
            AddStudentService.addStudent(userInformations);
        }
    }

    @FXML
    protected void buttonSaveReleased() throws IOException{
        if( inputsVerifications()) {
            URL fxml = StudentConfirmationController.class.getResource("studentConfirmation.fxml");
            ContentContainer.setSceneContentContainer(fxml);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
