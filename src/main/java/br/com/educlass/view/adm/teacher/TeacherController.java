package br.com.educlass.view.adm.teacher;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.service.admnistrator.AdmnistratorService;
import br.com.educlass.service.student.StudentService;
import br.com.educlass.util.Language;
import br.com.educlass.view.adm.teacher.addTeacher.AddTeacherController;
import br.com.educlass.view.adm.teacher.editTeacher.EditTeacherController;
import br.com.educlass.util.ContentContainer;
import br.com.educlass.util.CursorUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {

    @FXML
    private ComboBox<String> comboTeacherSelected;

    @FXML
    private Button buttonCreateNewTeacher;

    @FXML
    private Text textTitle;

    @FXML
    private Button buttonEditTeacher;

    @FXML
    private TableView<TeacherTable> tableView;

    @FXML
    private TableColumn<TeacherTable, String> tableFirstColumn;

    @FXML
    private TableColumn<TeacherTable, String> tableSecondColumn;

    ArrayList<Teacher> teachers;

    private void setFactoryTable() {
        tableFirstColumn.setCellValueFactory(new PropertyValueFactory<>("firstColumn"));
        tableSecondColumn.setCellValueFactory(new PropertyValueFactory<>("secondColumn"));
    }

    private void setDataInTable(String registration, String name) {
        tableView.getItems().add(new TeacherTable(registration, name));
    }

    private void setInitialPropsInComponents() {
        buttonEditTeacher.setDisable(true);
        setFactoryTable();
        if (teachers != null) {
            for (Teacher teacher : this.teachers) {
                comboTeacherSelected.getItems().add(teacher.getRegistration() + " - " + teacher.getName());
                setDataInTable(teacher.getRegistration(), teacher.getName());
            }
        }
    }

    @FXML
    private void tableselected() {
        try {
            String idStudentSelected = tableView.getSelectionModel().selectedItemProperty().get().getFirstColumn();
            for (Teacher teacher : this.teachers) {
                if (teacher.getRegistration().equalsIgnoreCase(idStudentSelected)) {
                    buttonEditTeacher.setDisable(false);
                    InformationsTeacher.setTeacherSelected(teacher);
                }
            }
        } catch (Exception e) {}
    }

    /**
     * OnChanged COMBOBOX
     */

    @FXML
    protected void buttonEditTeacherPress() throws IOException {
        URL fxml = EditTeacherController.class.getResource("editTeacher.fxml");
        ContentContainer.setSceneContentContainer(fxml);
    }

    @FXML
    protected void buttonAddTeacherPress() throws IOException {
        URL fxml = AddTeacherController.class.getResource("addTeacher.fxml");
        ContentContainer.setSceneContentContainer(fxml);
    }

    private void setLanguage() {
        HashMap<String, String> texts = Language
                .getTexts("src/main/resources/br/com/educlass/view/adm/teacher/languages/");

        textTitle.setText(texts.get("textTitle"));
        comboTeacherSelected.setPromptText(texts.get("comboTeacherSelected"));
        tableFirstColumn.setText(texts.get("tableFirstColumn"));
        tableSecondColumn.setText(texts.get("tableSecondColumn"));
        buttonEditTeacher.setText(texts.get("buttonEditTeacher"));
        buttonCreateNewTeacher.setText(texts.get("buttonCreateNewTeacher"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.teachers = AdmnistratorService.getListOfTeachers();
        setInitialPropsInComponents();
        setLanguage();
        CursorUtil.handleCursorType(Cursor.DEFAULT);
    }
}
