package br.com.educlass.view.adm.teacher.addTeacher;

import br.com.educlass.util.ContentContainer;
import br.com.educlass.view.adm.teacher.TeacherController;
import br.com.educlass.view.adm.teacher.addTeacher.teacherConfirmation.TeacherConfirmationController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class AddTeacherController implements Initializable {
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

    private File profilePicture;

    @FXML
    protected void buttonCancelPressed() throws IOException {
        URL fxml = TeacherController.class.getResource("teacher.fxml");
        ContentContainer.setSceneContentContainer(fxml);
    }

    private void profilePictureHasUploaded(File file) throws IOException {
        if(file != null) {
            String path = file.getPath();
            Image image = new Image("file:"+path, false);
            profilePictureContainer.setFill(new ImagePattern(image));
        }
    }

    private boolean inputsVerifications() {
        return textFieldName.getText().length() > 0 &&
                textFieldCpf.getText().length() >0 &&
                textFieldAddress.getText().length() > 0 &&
                textFieldEmail.getText().length() > 0 &&
                textFieldPassword.getText().length() > 0;
    }

    @FXML
    protected void buttonSavePressed() throws IOException {
        if( inputsVerifications()) {
            HashMap<String,String> userInformations = new HashMap<>();
            userInformations.put("name", textFieldName.getText());
            userInformations.put("cpf", textFieldCpf.getText());
            userInformations.put("address", textFieldAddress.getText());
            userInformations.put("email", textFieldEmail.getText());
            userInformations.put("password", textFieldPassword.getText());
            AddTeacherService.addTeacher(userInformations, profilePicture);
        }
    }

    @FXML
    protected void buttonUploadProfilePicturePressed() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("photo",   "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        this.profilePicture = selectedFile;
        profilePictureHasUploaded(selectedFile);
    }



    @FXML
    protected void buttonSaveReleased() throws IOException{
        if( inputsVerifications()) {
            URL fxml = TeacherConfirmationController.class.getResource("TeacherConfirmation.fxml");
            ContentContainer.setSceneContentContainer(fxml);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("file:src/main/resources/br/com/educlass/images/userIcon.png",
                false);
        profilePictureContainer.setFill(new ImagePattern(image));
    }

}
