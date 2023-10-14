package br.com.educlass.view.student.grades;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.subjects.Subject;
import br.com.educlass.service.student.StudentService;
import br.com.educlass.util.CursorUtil;
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
import java.util.ResourceBundle;

public class Grades implements Initializable {

    @FXML
    private ComboBox<String> periodSelect;

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

    Student student;

    private void setFactoryTable() {
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
    }

    private void setDataInTable(String date, String subject) {
        tableView.getItems().add(new GradeTable(date, subject));
    }

    private void setPeriodSelectOptions() {
//        if (student.getPeriodsSubjects() != null) {
//            int periodSize = student.getPeriodsSubjects().size();
//            int i = 1;
//            while (i <= periodSize) {
//                periodSelect.getItems().add("Periodo: " + i);
//                i = i + 1;
//            }
//        } else {
//            periodSelect.getItems().add("Não há períodos disponíveis.");
//        }
    }

    private int getPeriodSelected() {
        int periodSelected;
        try {
            return Integer.parseInt(periodSelect.getValue().split(":")[1].trim()) - 1;
        } catch (Exception e) {
            periodSelected = -1;
            return periodSelected;
        }
    }

    @FXML
    private void handlePeriodSelect() {
        this.tableView.setVisible(true);
        setFactoryTable();
        infoText.setText("Selecione uma disciplina para obter mais informações.");
        subjectSelect.getItems().clear();
        tableView.getItems().clear();
        int periodSelected = getPeriodSelected();
        if (periodSelected != -1) {
//            ArrayList<Subject> subjects = student.getPeriodsSubjects().get(periodSelected);
            /*
             * Aqui pega-se todas as disciplinas do periodo selecionado
             */
//            for (Subject item : subjects) {
//                /*
//                 * Aqui pega-se todas as datas que houve falta daquela disciplina
//                 */
//                for (String grade : item.getGrades()) {
//                    setDataInTable(grade.replace("-", "/"), item.getName());
//                }
//                subjectSelect.getItems().add(item.getName());
//            }
        } else {
            subjectSelect.getItems().add("Não há matérias disponíveis.");
        }
    }

    @FXML
    private void handleSubjectSelect() {
        if (subjectSelect.getValue() != null) {
            tableView.getItems().clear();
            int periodSelected = getPeriodSelected();
            if (periodSelected != -1) {
//                ArrayList<Subject> subjects = student.getPeriodsSubjects().get(periodSelected);
//                for (Subject subject : subjects) {
//                    if (subjectSelect.getValue().equalsIgnoreCase(subject.getName())) {
//                        for (String grade : subject.getGrades()) {
//                            setDataInTable(grade.replace("-", "/"), subject.getName());
//                        }
//                    }
//                }
//                infoText.setText("Sua frequencia projetada pra essa disciplina é de:");
            } else {
                infoText.setText("Não há disciplinas disponíveis");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tableView.setVisible(false);
        this.student = StudentService.getStudent();

        setPeriodSelectOptions();
        CursorUtil.handleCursorType(Cursor.DEFAULT);
    }
}
