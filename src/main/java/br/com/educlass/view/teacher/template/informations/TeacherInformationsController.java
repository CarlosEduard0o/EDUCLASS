package br.com.educlass.view.teacher.template.informations;

import br.com.educlass.util.Language;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TeacherInformationsController implements Initializable {
    @FXML
    private Text titleText;
    @FXML
    private Text frequencyTitle;
    @FXML
    private Text gradesTitle;
    @FXML
    private Text frequencyContent;
    @FXML
    private Text gradesContent;

    private void setLanguage() {
        HashMap<String, String> texts = Language
                .getTexts("src/main/resources/br/com/educlass/view/teacher/template/informations/languages/");
        titleText.setText(texts.get("titleText"));
        frequencyTitle.setText(texts.get("frequencyTitle"));
        gradesTitle.setText(texts.get("gradesTitle"));
        frequencyContent.setText(texts.get("frequencyContent"));
        gradesContent.setText(texts.get("gradesContent"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLanguage();

    }
}
