package br.com.educlass.view.adm.teacher;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.service.admnistrator.AdmnistratorService;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {

    @FXML
    private ComboBox<String> comboStudentSelected;

    @FXML
    private Button buttonCreateNewStudent;

    @FXML
    private Button buttonEditStudent;

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
        setFactoryTable();
        if (teachers != null) {
            for (Teacher teacher : this.teachers) {
                comboStudentSelected.getItems().add(teacher.getRegistration() + " - " + teacher.getName());
                setDataInTable(teacher.getRegistration(), teacher.getName());
            }
        }
        //
    }

    /**
     * OnChanged COMBOBOX
     */

    @FXML
    protected void buttonEditStudentPress() throws IOException {
        URL fxml = EditTeacherController.class.getResource("editTeacher.fxml");
        ContentContainer.setSceneContentContainer(fxml);
        System.out.println("add");
    }

    @FXML
    protected void buttonAddStudentPress() throws IOException {
        URL fxml = AddTeacherController.class.getResource("addTeacher.fxml");
        ContentContainer.setSceneContentContainer(fxml);
        System.out.println("add");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.teachers = AdmnistratorService.getListOfTeachers();
        setInitialPropsInComponents();
        CursorUtil.handleCursorType(Cursor.DEFAULT);
    }
}
