package br.com.educlass.view.student.grades;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.subjects.Subject;
import br.com.educlass.service.student.StudentService;
import br.com.educlass.util.CursorUtil;
import br.com.educlass.util.Language;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Grades implements Initializable {

    @FXML
    private Text titleText;

    @FXML
    private ComboBox<String> subjectSelect;

    @FXML
    private TableView<GradeTable> tableView;

    @FXML
    private TableColumn<GradeTable, String> dateColumn;

    @FXML
    private TableColumn<GradeTable, String> subjectColumn;

    @FXML
    private Text infoText;

    private String infoTextWithLanguage = null;

    Student student;

    private void setFactoryTable() {
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        this.tableView.setTableMenuButtonVisible(true);
    }

    private void setDataInTable(String date, String subject) {
        tableView.getItems().add(new GradeTable(date, subject));
    }

    @FXML
    private void handleSubjectSelect() {
        if (subjectSelect.getValue() != null) {
            tableView.getItems().clear();
                ArrayList<Subject> subjects = student.getSubjects();
                double mediaFinalNecessaria = 0;
                for (Subject subject : subjects) {
                    if (subjectSelect.getValue().split("-")[0].trim().equalsIgnoreCase(subject.getId())) {
                        for (String grade : subject.getGrades()) {
                            setDataInTable(grade.replace("-", "/"), subject.getName());
                            System.out.println(grade);
                            if(!grade.replace("-", "/").equals("Sem notas")) {
                                mediaFinalNecessaria =mediaFinalNecessaria +
                                        Integer.parseInt(grade.split("-")[0].trim());
                            }
                        }
                    }
                    mediaFinalNecessaria = (1 * ((500 - (mediaFinalNecessaria * 6)) / 4));
                    mediaFinalNecessaria = mediaFinalNecessaria >= 100 ? 100 : mediaFinalNecessaria;
                }
            infoText.setText(this.infoTextWithLanguage + ": " + mediaFinalNecessaria);
        }
    }

    private void setInitialInformations() {
        if (student.getSubjects() != null) {
            for (Subject subject : student.getSubjects()) {
                subjectSelect.getItems().add(subject.getId() + " - " + subject.getName());
                for (String grade: subject.getGrades()) {
                    setDataInTable(grade, subject.getName());
                }
            }
        }
    }

    private void setLanguage() {
        HashMap<String, String> texts = Language
                .getTexts("src/main/resources/br/com/educlass/view/student/grades/languages/");

        titleText.setText(texts.get("titleText"));
        subjectSelect.setPromptText(texts.get("subjectSelect"));
        dateColumn.setText(texts.get("dateColumn"));
        subjectColumn.setText(texts.get("subjectColumn"));

        this.infoTextWithLanguage = texts.get("infoText");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tableView.setVisible(true);
        this.student = StudentService.getStudent();
        setFactoryTable();
        setInitialInformations();
        setLanguage();
        CursorUtil.handleCursorType(Cursor.DEFAULT);
    }
}
