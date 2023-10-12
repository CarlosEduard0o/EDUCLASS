package br.com.educlass.view.teacher.grades;

import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.service.teacher.TeacherService;
import br.com.educlass.util.CursorUtil;
import br.com.educlass.util.JsonFile;
import br.com.educlass.util.UserUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ResourceBundle;

public class GradesTeacher implements Initializable {

    @FXML
    private ComboBox<String> subjectSelect;

    @FXML
    private TableView<studentGradeData> tableView;

    @FXML
    private TableColumn<studentGradeData, String> studentNameColumn;

    @FXML
    private TableColumn<studentGradeData, String> addGradeColumn;

    @FXML
    private TableColumn<studentGradeData, String> currentGradeColumn;

    @FXML
    private Button sendGradeButton;

    Teacher teacher;

    ObservableList<studentGradeData> dadosDosAlunos;

    @FXML
    public void onSendGradeButtonClick() {
        String path = UserUtil.getPathTeacher() + "disciplinas.json";
        JSONArray jsonArray = JsonFile.readJsonFile(path);
        int i = 0;
        for (studentGradeData estudante : dadosDosAlunos) {
            JSONObject object = (JSONObject) jsonArray.get(i);
            estudante.setCurrentGrade(estudante.getAddGrade());
            try {
                object.put("grade", estudante.getCurrentGrade());
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
        try {
            // Escrever o arquivo uma vez após o loop
            Files.write(Paths.get(path), jsonArray.toString().getBytes(), StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lerArquivo();
    }

    private void setSubjectSelectOptions() {
        // Neste ponto teacher.getSubjects() está retornando null. Ver depois como fazer
        // isso pegar as subjects do JSON
        String[] elementos = { "Estatística Inferencial", "Algorítmos e Estrutura de Dados",
                "Introdução a Sistemas de Informações", "Tópicos Humanísticos" };
        final ObservableList<String> dadosDasDisciplinas = FXCollections.observableArrayList(elementos);
        subjectSelect.setItems(dadosDasDisciplinas);
    }

    private void setFactoryTable() {
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<studentGradeData, String>("studentName"));
        addGradeColumn.setCellValueFactory(new PropertyValueFactory<studentGradeData, String>("addGrade"));
        currentGradeColumn.setCellValueFactory(new PropertyValueFactory<studentGradeData, String>("currentGrade"));
    }

    @FXML
    private void handleSubjectSelect() {
        lerArquivo();
    }

    public void lerArquivo() {
        dadosDosAlunos = FXCollections.observableArrayList();
        String data = UserUtil.getPathTeacher() + "disciplinas.json";
        JSONArray jsonArray = JsonFile.readJsonFile(data);

        for (Object element : jsonArray) {
            JSONObject jsonObject = (JSONObject) element;
            String name = (String) jsonObject.get("name");
            String currentGrade = String.valueOf(jsonObject.get("grade"));
            dadosDosAlunos.add(new studentGradeData(
                    name, Integer.parseInt(currentGrade)));
        }
        tableView.getItems().clear();
        this.sendGradeButton.setVisible(true);
        this.tableView.setVisible(true);
        tableView.setItems(dadosDosAlunos);
        subjectSelect.getValue();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFactoryTable();
        this.sendGradeButton.setVisible(false);
        this.tableView.setVisible(false);
        this.teacher = TeacherService.getTeacher();
        setSubjectSelectOptions();
        CursorUtil.handleCursorType(Cursor.DEFAULT);
    }
}
