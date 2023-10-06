package br.com.educlass.view.student.frequency;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.subjects.Subject;
import br.com.educlass.service.student.StudentService;
import br.com.educlass.util.CursorUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class Frequency implements Initializable {

    @FXML
    private Text periodText;

//    @FXML
//    private Text

    @FXML
    private ComboBox<String> periodSelect;

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

    private void setFactoryTable() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
    }

    private void setDataInTable(String date, String subject) {
        tableView.getItems().add(new FrequencyTable(date, subject));
    }

    private void setPeriodSelectOptions() {
        if(student.getPeriodsSubjects() != null) {
            int periodSize = student.getPeriodsSubjects().size();
            int i = 1;
            while(i <= periodSize){
                periodSelect.getItems().add("Periodo: " + i);
                i = i+1;
            }
        } else {
            periodSelect.getItems().add("Não há períodos disponíveis.");
        }
    }

    private int getPeriodSelected() {
        int periodSelected;
        try {
            periodSelected =  Integer.parseInt(periodSelect.getValue().split(":")[1].trim()) - 1;
        } catch (Exception e) {
            periodSelected = -1;
        }
        return periodSelected;
    }

    private int getPercentPresenceInSubject(int quantityOfabscence, long totalTime) {
        int abscenceInHours = (quantityOfabscence*50)/60;
        return (int)(100 - (abscenceInHours*100)/totalTime);
    }

    @FXML
    private void handlePeriodSelect() {
        this.tableView.setVisible(true);
        setFactoryTable();
        infoText.setText("Selecione uma disciplina para obter mais informações.");
        percentText.setText("");
        subjectSelect.getItems().clear();
        tableView.getItems().clear();
        int periodSelected = getPeriodSelected();
        if(periodSelected != -1) {
            ArrayList<Subject> subjects = student.getPeriodsSubjects().get(periodSelected);
            /**
             * Aqui pega-se todas as disciplinas do periodo selecionado
             */
            for(Subject item: subjects){
                /**
                 * Aqui pega-se todas as datas que houve falta daquela disciplina
                 */
                for(String frequency: item.getFrequency()) {
                    setDataInTable(frequency ,item.getName());
                }
                subjectSelect.getItems().add(item.getName());
            }
        } else {
            subjectSelect.getItems().add("Não há matérias disponíveis.");
        }
    }

    @FXML
    private void handleSubjectSelect() {
        if(subjectSelect.getValue() != null) {
            tableView.getItems().clear();
            int periodSelected = getPeriodSelected();
            if(periodSelected != -1) {
                ArrayList<Subject> subjects = student.getPeriodsSubjects().get(periodSelected);
                String percentPrescenceSubject = "";
                for(Subject subject:subjects) {
                    if(subjectSelect.getValue().equalsIgnoreCase(subject.getName())) {
                        for(String frequency: subject.getFrequency()) {
                            setDataInTable(frequency ,subject.getName());
                        }
                        percentPrescenceSubject = String.valueOf(getPercentPresenceInSubject(subject.getFrequency().size(), subject.getTime()));
                    }
                }
                infoText.setText("Sua frequencia projetada pra essa disciplina é de:");
                percentText.setText(percentPrescenceSubject+"%");
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
