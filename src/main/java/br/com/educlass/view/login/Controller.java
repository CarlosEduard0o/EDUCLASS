package br.com.educlass.view.login;

import br.com.educlass.view.adm.template.AdmTemplateController;
import br.com.educlass.view.configuration.ConfigurationView;
import br.com.educlass.util.Language;
import br.com.educlass.util.TextFile;
import br.com.educlass.util.SceneController;
import br.com.educlass.view.student.template.Template;
import br.com.educlass.view.configuration.ConfigurationIcon;
import br.com.educlass.view.teacher.template.TemplateTeacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField user;

    @FXML
    private Text userText;

    @FXML
    private PasswordField password;

    @FXML
    private Text passwordText;

    @FXML
    private Pane statusPane;

    @FXML
    private Text notFoundText1;

    @FXML
    private Text notFoundText2;

    @FXML
    private Text notFoundText3;

    @FXML
    private Circle configurationContainer;

    @FXML
    private Button enterButton;

    private boolean isAdminUser(String username) {
        return username.equalsIgnoreCase("sadmin");

    }

    private void userNotFound() {
        statusPane.setStyle("-fx-opacity: 1");
    }

    private HashMap<String, String> getLoginObject() {
        HashMap<String, String> result = new HashMap<>();
        String userInput = user.getText();

        boolean userAdmin = isAdminUser(userInput);

        if ((userInput.length() < 6 && !userInput.contains("T") || userInput == null) &&
                !userAdmin) {
            return null;
        }

        String path = "";
        if (userAdmin) {
            path = "db/users/_school/login.txt";
            TextFile textFile = new TextFile();
            ArrayList<String> fileLines = textFile.readTextFile(path);

            if (fileLines != null) {
                for (String s : fileLines) {
                    String[] lineSplited = s.split(":");
                    result.put(lineSplited[0], lineSplited[1]);
                }
                return result;
            }
        } else if (userInput.toUpperCase().contains("T")) {
            String id = userInput;
            id = id.replace("T", "");
            path = "db/users/teachers/"+id+"/login.txt";
        }  else {
            String year = userInput.substring(1, 5);
            String semester = userInput.substring(0, 1);
            String registration = userInput.substring(5);
            path = "db/users/students/" + year + "/" + semester + "/" + registration + "/login.txt";
        }

        System.out.println(path);

        TextFile textFile = new TextFile();
        ArrayList<String> fileLines = textFile.readTextFile(path);

        if (fileLines != null) {
            for (String s : fileLines) {
                String[] lineSplited = s.split(":");
                result.put(lineSplited[0], lineSplited[1]);
            }
            return result;
        }
        return null;
    }

    private boolean verifyUserAndPassword() {
        HashMap<String, String> loginInformations = getLoginObject();
        if (loginInformations != null) {
            if (loginInformations.get("password").equals(password.getText()) &&
                    loginInformations.get("username").equalsIgnoreCase(user.getText())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private void loginUser() {
        boolean login = verifyUserAndPassword();
        String userInput = user.getText();
        if (login == false) {
            userNotFound();
        } else {
            TextFile textFile = new TextFile();

            String path = "db/cache/user";
            HashMap<String, String> loginInformations = getLoginObject();
            String username = loginInformations.get("username");
            String password = loginInformations.get("password");
            String content = "username:" + username + "\n" + "password:"
                    + password;
            textFile.writeTextFile(path, content);

            SceneController sceneController = new SceneController();

            if (userInput.toUpperCase().contains("T")) {
                sceneController.switchScene(user.getScene().getWindow(),
                        TemplateTeacher.class.getResource("template.fxml"));

            } else if (isAdminUser(username)) {
                sceneController.switchScene(user.getScene().getWindow(),
                        AdmTemplateController.class.getResource("template_adm.fxml"));
            } else {
                sceneController.switchScene(user.getScene().getWindow(),
                        Template.class.getResource("template.fxml"));
            }

        }
    }

    @FXML
    protected void keyEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loginUser();
        }
    }

    @FXML
    protected void buttonEnterPressed() {
        loginUser();
    }

    @FXML
    protected void openSettings() {
        ConfigurationView.openModalSettings();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ConfigurationIcon.insertConfigurationIcon(configurationContainer);
        HashMap<String, String> texts = Language.getTexts("src/main/resources/br/com/educlass/view/login/languages/");
        userText.setText(texts.get("userText"));
        passwordText.setText(texts.get("passwordText"));
        enterButton.setText(texts.get("enterButton"));
        notFoundText1.setText(texts.get("notFoundText1"));
        notFoundText2.setText(texts.get("notFoundText2"));
        notFoundText3.setText(texts.get("notFoundText3"));
    }

}
