package br.com.educlass.view.adm.student.editStudent;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.service.admnistrator.AdmnistratorService;
import br.com.educlass.util.ContentContainer;
import br.com.educlass.util.TextFile;
import br.com.educlass.view.adm.student.InformationsStudent;
import br.com.educlass.view.adm.student.StudentController;
import br.com.educlass.view.adm.student.StudentSituationEnum;
import br.com.educlass.view.adm.student.addStudent.AddStudentService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EditStudentController implements Initializable {

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
    private ComboBox comboBoxSituation;

    private Student studentSelected;

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
    protected void buttonSavePressed() throws IOException{
        if  (inputsVerifications()) {
            HashMap<String,String> userInformations = new HashMap<>();
            userInformations.put("name", textFieldName.getText());
            userInformations.put("cpf", textFieldCpf.getText());
            userInformations.put("address", textFieldAddress.getText());
            userInformations.put("email", textFieldEmail.getText());
            userInformations.put("password", textFieldPassword.getText());
            userInformations.put("registration", studentSelected.getRegistration());
            userInformations.put("situation",(String) comboBoxSituation.getValue());
            EditStudentService.editStudent(userInformations);
        }
        URL fxml = StudentController.class.getResource("students.fxml");
        ContentContainer.setSceneContentContainer(fxml);
    }

    private void setAllInformations() {
        textFieldName.setText(studentSelected.getName());
        textFieldCpf.setText(studentSelected.getCpf());
        textFieldAddress.setText(studentSelected.getAddress());
        textFieldEmail.setText(studentSelected.getEmail());

        /** PASSWORD */
        String registration = studentSelected.getRegistration();
        String year = registration.substring(1,5);
        String semester = registration.substring(0,1);
        String id = registration.substring(5);

        String pathUsers = "db/users/students/"+year+"/"+semester+"/"+id+"/login.txt";
        String password = TextFile.readTextFileMapping(pathUsers).get("password");
        textFieldPassword.setText(password);
        /** END PASSWORD */

        for (StudentSituationEnum situation : StudentSituationEnum.values()) {
            comboBoxSituation.getItems().add(String.valueOf(situation));

        }
        comboBoxSituation.setValue(studentSelected.getSituation());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AdmnistratorService.setEditStudent(InformationsStudent.getStudentSelected());
        studentSelected = InformationsStudent.getStudentSelected();
        setAllInformations();
    }
}
