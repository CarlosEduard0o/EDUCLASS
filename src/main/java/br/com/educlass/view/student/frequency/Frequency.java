package br.com.educlass.view.student.frequency;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.subjects.Subject;
import br.com.educlass.service.student.StudentService;
import br.com.educlass.util.CursorUtil;
import br.com.educlass.util.JsonFile;
import br.com.educlass.util.Language;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Frequency implements Initializable {

    @FXML
    private Text periodText;

    @FXML
    private Text titleText;

    // @FXML
    // private Text

//    @FXML
//    private ComboBox<String> periodSelect;

    @FXML
    private ComboBox<String> subjectSelect;

    @FXML
    private TableView<FrequencyTable> tableView;

    @FXML
    private TableColumn<FrequencyTable, String> dateColumn;

    @FXML
    private TableColumn<FrequencyTable, String> subjectColumn;

    @FXML
    private Text infoText;

    @FXML
    private Text percentText;

    Student student;

    private String percentTextWithLanguage = null;

    private void setFactoryTable() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
    }

    private void setDataInTable(String date, String subject) {
        tableView.getItems().add(new FrequencyTable(date, subject));
    }

    private void setSubjectOptions() {
        if (student.getSubjects() != null) {
            for(Subject subject : student.getSubjects()) {
                subjectSelect.getItems().add(subject.getId() + " - " + subject.getName());
            }
        } else {
            subjectSelect.getItems().add("Não há períodos disponíveis.");
        }
    }


    @FXML
    private void handleSubjectSelect() {
        infoText.setText(percentTextWithLanguage + ": ");
        if (student.getSubjects() != null) {
            String quantidadeSting = "";
            for (Subject subject : student.getSubjects()) {
                if(subject.getId().equals(subjectSelect.getValue().split("-")[0].trim())) {
                    String path  = "db/subjects/"+subject.getId()+"/";
                    System.out.println(path);
                    JSONArray jsonArray = JsonFile.readJsonFile(path+"informations.json");
                    JSONObject jsonObject = (JSONObject) jsonArray.get(0);

                    Long quantity = (Long) jsonObject.get("quantidade_de_aulas");
                    quantidadeSting = String.valueOf(100 - (subject.getFrequency()*100/quantity));
                }
            }
            percentText.setText(quantidadeSting+"%");
        }
    }

    private void setInitialProps() {
        setFactoryTable();
        if (student.getSubjects() != null) {
            for (Subject subject : student.getSubjects()) {
                setDataInTable(subject.getFrequency().toString(), subject.getName());
            }
        }
        setSubjectOptions();
    }

    private void setLanguage() {
        HashMap<String, String> texts = Language
                .getTexts("src/main/resources/br/com/educlass/view/student/frequency/languages/");

        titleText.setText(texts.get("titleText"));
        subjectSelect.setPromptText(texts.get("subjectSelect"));
        dateColumn.setText(texts.get("dateColumn"));
        subjectColumn.setText(texts.get("subjectColumn"));

        this.percentTextWithLanguage = texts.get("percentText");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.student = StudentService.getStudent();
        setInitialProps();
        setLanguage();
        CursorUtil.handleCursorType(Cursor.DEFAULT);
    }
}