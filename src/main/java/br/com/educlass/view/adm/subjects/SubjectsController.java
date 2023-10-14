package br.com.educlass.view.adm.subjects;

import br.com.educlass.model.subjects.Subject;
import br.com.educlass.service.admnistrator.AdmnistratorService;
import br.com.educlass.service.admnistrator.AdmnistratorSubjectService.AdmnistratorSubjectService;
import br.com.educlass.util.ContentContainer;
import br.com.educlass.util.CursorUtil;
import br.com.educlass.view.adm.subjects.createSubjects.CreateSubjects;
import br.com.educlass.view.adm.teacher.TeacherTable;
import br.com.educlass.view.adm.teacher.editTeacher.EditTeacherController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SubjectsController implements Initializable {
    @FXML
    private ComboBox<String> comboSubjectSelected;

    @FXML
    private Button buttonCreateNewSubject;

    @FXML
    private Button buttonEditSubject;

    @FXML
    private TableView<SubjectTabel> tableView;

    @FXML
    private TableColumn<SubjectTabel, String> tableFirstColumn;

    @FXML
    private TableColumn<SubjectTabel, String> tableSecondColumn;

    @FXML
    private TableColumn<SubjectTabel, String> tableThirdColumn;

    ArrayList<Subject> subjects;

    private void setFactoryTable() {
        tableFirstColumn.setCellValueFactory(new PropertyValueFactory<>("firstColumn"));
        tableSecondColumn.setCellValueFactory(new PropertyValueFactory<>("secondColumn"));
        tableThirdColumn.setCellValueFactory(new PropertyValueFactory<>("thirdColumn"));
    }

    private void setDataInTable(String subject, String teacher, String quantity) {
        tableView.getItems().add(new SubjectTabel(subject, teacher, quantity));
    }


    private void setInitialInformations() {
        buttonEditSubject.setDisable(true);
        setFactoryTable();
        for(Subject subject: subjects) {
            comboSubjectSelected.getItems().add(subject.getId() + " - " + subject.getName());
            setDataInTable(
                    subject.getName(),
                    subject.getUniqueTeacher().getName(),
                    String.valueOf(subject.getTime())
            );
        }

    }

    @FXML
    private void tableselected() {

    }

    @FXML
    private void buttonAddSubjectPress() throws IOException{
        URL fxml = CreateSubjects.class.getResource("createSubjects.fxml");
        ContentContainer.setSceneContentContainer(fxml);
    }

    @FXML
    private void buttonEditSubjectPress() throws IOException {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        subjects = AdmnistratorService.getAdmnistrator().getSubjects();
        setInitialInformations();
        CursorUtil.handleCursorType(Cursor.DEFAULT);
    }
}
