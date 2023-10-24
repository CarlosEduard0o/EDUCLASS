package br.com.educlass.view.adm.template.informations;

import br.com.educlass.util.Language;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class InformationsController implements Initializable {
    @FXML
    private Text titleText;
    @FXML
    private Text studentTitle;
    @FXML
    private Text teacherTitle;
    @FXML
    private Text subjectTitle;
    @FXML
    private Text studentContent;
    @FXML
    private Text teacherContent;
    @FXML
    private Text subjectContent;

    private void setLanguage() {
        HashMap<String, String> texts = Language
                .getTexts("src/main/resources/br/com/educlass/view/adm/template/informations/languages/");
        titleText.setText(texts.get("titleText"));
        studentTitle.setText(texts.get("studentTitle"));
        teacherTitle.setText(texts.get("teacherTitle"));
        subjectTitle.setText(texts.get("subjectTitle"));
        studentContent.setText(texts.get("studentContent"));
        teacherContent.setText(texts.get("teacherContent"));
        subjectContent.setText(texts.get("subjectContent"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLanguage();

    }
}
