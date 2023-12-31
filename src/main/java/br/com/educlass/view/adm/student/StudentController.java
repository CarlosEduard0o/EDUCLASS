package br.com.educlass.view.adm.student;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.service.admnistrator.AdmnistratorService;
import br.com.educlass.util.ContentContainer;
import br.com.educlass.util.CursorUtil;
import br.com.educlass.util.Language;
import br.com.educlass.view.adm.student.addStudent.AddStudentController;
import br.com.educlass.view.adm.student.editStudent.EditStudentController;
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

public class StudentController implements Initializable {

    @FXML
    private ComboBox<String> comboStudentSelected;

    @FXML
    private TableView<StudentTable> tableView;

    @FXML
    private TableColumn<StudentTable, String> tableFirstColumn;

    @FXML
    private TableColumn<StudentTable, String> tableSecondColumn;

    @FXML
    private Button editButton;

    @FXML
    private Text textTitle;

    @FXML Button AddButton;

    ArrayList<Student> students;

    private void setFactoryTable() {
        tableFirstColumn.setCellValueFactory(new PropertyValueFactory<>("firstColumn"));
        tableSecondColumn.setCellValueFactory(new PropertyValueFactory<>("secondColumn"));
    }

    private void setDataInTable(String registration, String name) {
        tableView.getItems().add(new StudentTable(registration, name));
    }

    private void setInitialPropsInComponents() {
        setFactoryTable();
        for (Student student : this.students) {
            comboStudentSelected.getItems().add(student.getRegistration() + " - " + student.getName());
            setDataInTable(student.getRegistration(), student.getName());
        }
    }

    @FXML
    private void tableselected() {
        try{
            String idStudentSelected = tableView.getSelectionModel().selectedItemProperty().get().getFirstColumn();
            for (Student student : this.students) {
                if (student.getRegistration().equalsIgnoreCase(idStudentSelected)) {
                    editButton.setDisable(false);
                    InformationsStudent.setStudentSelected(student);
                }
            }
        } catch (Exception e) {}

    }

    @FXML
    private void handleStudentSelectedComboBox() {
        editButton.setDisable(false);
        String selected = comboStudentSelected.getValue();
        String idStudentSelected = selected.split("-")[0].trim();

        this.tableView.getItems().clear();
        tableView.getColumns().get(0).setText("");
        tableView.getColumns().get(1).setText("");

        for (Student student : this.students) {
            if (student.getRegistration().equalsIgnoreCase(idStudentSelected)) {
                InformationsStudent.setStudentSelected(student);

                setDataInTable("Nome", student.getName());
                setDataInTable("CPF", student.getCpf());
                setDataInTable("Endereço", student.getAddress());
                setDataInTable("Email", student.getEmail());
            }
        }
    }

    @FXML
    protected void buttonEditStudentPress() throws IOException {
        URL fxml = EditStudentController.class.getResource("editStudent.fxml");
        ContentContainer.setSceneContentContainer(fxml);
    }

    @FXML
    protected void buttonAddStudentPress() throws IOException {
        URL fxml = AddStudentController.class.getResource("addStudent.fxml");
        ContentContainer.setSceneContentContainer(fxml);
    }

    private void setLanguage() {
        HashMap<String, String> texts = Language
                .getTexts("src/main/resources/br/com/educlass/view/adm/student/languages/");

        textTitle.setText(texts.get("textTitle"));
        comboStudentSelected.setPromptText(texts.get("comboStudentSelected"));
        tableFirstColumn.setText(texts.get("tableFirstColumn"));
        tableSecondColumn.setText(texts.get("tableSecondColumn"));
        editButton.setText(texts.get("editButton"));
        AddButton.setText(texts.get("AddButton"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editButton.setDisable(true);
        this.students = AdmnistratorService.getListOfStudents();
        setInitialPropsInComponents();
        setLanguage();
        CursorUtil.handleCursorType(Cursor.DEFAULT);
    }
}
