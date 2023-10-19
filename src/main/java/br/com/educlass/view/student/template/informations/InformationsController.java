package br.com.educlass.view.student.template.informations;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.util.Language;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class InformationsController  implements Initializable {
    @FXML
    private Text titleText;
    @FXML
    private Text frequencyTitle;
    @FXML
    private Text gradesTitle;
    @FXML
    private Text extraTitle;
    @FXML
    private Text frequencyContent;
    @FXML
    private Text gradesContent;
    @FXML
    private Text extraContent;

    private void setLanguage() {
        HashMap<String, String> texts = Language
                .getTexts("src/main/resources/br/com/educlass/view/student/template/informations/languages/");
        titleText.setText(texts.get("titleText"));
        frequencyTitle.setText(texts.get("frequencyTitle"));
        gradesTitle.setText(texts.get("gradesTitle"));
        extraTitle.setText(texts.get("extraTitle"));
        frequencyContent.setText(texts.get("frequencyContent"));
        gradesContent.setText(texts.get("gradesContent"));
        extraContent.setText(texts.get("extraContent"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLanguage();

    }
}
