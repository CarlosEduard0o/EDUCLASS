package br.com.educlass.view.adm.teacher.editTeacher;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.service.admnistrator.AdmnistratorService;
import br.com.educlass.service.student.StudentService;
import br.com.educlass.util.ContentContainer;
import br.com.educlass.util.Language;
import br.com.educlass.util.TextFile;
import br.com.educlass.view.adm.teacher.InformationsTeacher;
import br.com.educlass.view.adm.teacher.editTeacher.teacherEditConfirmation.TeacherEditConfirmation;
import br.com.educlass.view.adm.teacher.TeacherController;
import br.com.educlass.view.adm.teacher.TeacherSituationEnum;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EditTeacherController implements Initializable {

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
    private Text textPassword1;
    @FXML
    private Circle profilePictureContainer;

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
    private Button buttonUploadPicture;
    @FXML
    private Button buttonCancel;
    @FXML
    private Button buttonSave;



    @FXML
    private ComboBox comboBoxSituation;

    private Teacher teacherSelected;

    private File profilePicture = null;

    @FXML
    protected void buttonCancelPressed() throws IOException {
        URL fxml = TeacherController.class.getResource("teacher.fxml");
        ContentContainer.setSceneContentContainer(fxml);
    }

    private boolean inputsVerifications() {
        return textFieldName.getText().length() > 0 &&
                textFieldCpf.getText().length() >0 &&
                textFieldAddress.getText().length() > 0 &&
                textFieldEmail.getText().length() > 0 &&
                textFieldPassword.getText().length() > 0;
    }

    private void profilePictureHasUploaded(File file) throws IOException {
        if(file != null) {
            String path = file.getPath();
            Image image = new Image("file:"+path, false);
            profilePictureContainer.setFill(new ImagePattern(image));
        }
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
            userInformations.put("registration", teacherSelected.getRegistration());
            userInformations.put("situation",(String) comboBoxSituation.getValue());

            EditTeacherService.editTeacher(userInformations, profilePicture);
        }
    }

    @FXML
    protected void buttonSaveReleased() throws IOException {
        URL fxml = TeacherEditConfirmation.class.getResource("TeacherConfirmation.fxml");
        ContentContainer.setSceneContentContainer(fxml);
    }

    @FXML
    protected void buttonEditProfilePicturePressed() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("photo",   "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        this.profilePicture = selectedFile;
        profilePictureHasUploaded(selectedFile);
    }

    private void setAllInformations() {
        textFieldName.setText(teacherSelected.getName());
        textFieldCpf.setText(teacherSelected.getCpf());
        textFieldAddress.setText(teacherSelected.getAddress());
        textFieldEmail.setText(teacherSelected.getEmail());

        /** PASSWORD */
        String registration = teacherSelected.getRegistration();
        String id = registration.substring(1);

        String pathUsers = "db/users/teachers/"+id+"/login.txt";
        String password = TextFile.readTextFileMapping(pathUsers).get("password");
        textFieldPassword.setText(password);
        /** END PASSWORD */
//
        for (TeacherSituationEnum situation : TeacherSituationEnum.values()) {
            comboBoxSituation.getItems().add(String.valueOf(situation));
        }
//
        this.profilePicture = EditTeacherService.verifyIfUserHasProfilePicture(
                teacherSelected.getRegistration()
        );

        String path = profilePicture.getPath();
        Image image = new Image("file:"+path, false);
        profilePictureContainer.setFill(new ImagePattern(image));

        comboBoxSituation.setValue(teacherSelected.getSituation());
    }

    private void setLanguage() {
        HashMap<String, String> texts = Language
                .getTexts("src/main/resources/br/com/educlass/view/adm/teacher/editTeacher/languages/");

        textTitle.setText(texts.get("textTitle"));
        textName.setText(texts.get("textName"));
        textCpf.setText(texts.get("textCpf"));
        textAddress.setText(texts.get("textAddress"));
        textEmail.setText(texts.get("textEmail"));
        textPassword.setText(texts.get("textPassword"));
        textPassword1.setText(texts.get("textPassword1"));
        buttonUploadPicture.setText(texts.get("buttonUploadPicture"));
        buttonCancel.setText(texts.get("buttonCancel"));
        buttonSave.setText(texts.get("buttonSave"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLanguage();
        AdmnistratorService.setEditTeacher(InformationsTeacher.getTeacherSelected());
        teacherSelected = InformationsTeacher.getTeacherSelected();
        setAllInformations();
    }
}
