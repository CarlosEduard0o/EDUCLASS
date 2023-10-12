package br.com.educlass.view.configuration;

import br.com.educlass.util.TextFile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ConfigurationController implements Initializable {
    @FXML
    private Text languageText;

    @FXML
    private Text informationText;

    @FXML
    private ComboBox selectLanguage;

    String prevLanguageSelected;
    String nowLanguageSelected;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String languageConfigurationsInDb = "db/configurations/language.txt";
        HashMap<String, String> languageSelected = TextFile.readTextFileMapping(languageConfigurationsInDb);
        String selected = languageSelected.get("selected");
        prevLanguageSelected = selected;
        String pathLanguageSelected = "src/main/resources/br/com/educlass/view/configuration/languages/" + selected
                + ".txt";

        HashMap<String, String> allTexts = TextFile.readTextFileMapping(pathLanguageSelected);
        selectLanguage.setValue(selected);
        selectLanguage.getItems().addAll("Portugues", "English");

        languageText.setText(allTexts.get("languageText"));
        informationText.setText(allTexts.get("informationText"));
    }

    @FXML
    protected void onSavePressed() {
        nowLanguageSelected = (String) selectLanguage.getValue();
        TextFile.writeTextFile("db/configurations/language", "selected:" + nowLanguageSelected);

        if (!prevLanguageSelected.equalsIgnoreCase(nowLanguageSelected)) {
            System.exit(0);
        } else {
            Stage stage = (Stage) selectLanguage.getScene().getWindow();
            stage.close();
        }
    }

}
