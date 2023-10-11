package br.com.educlass.view.teacher.grades;

import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.service.teacher.TeacherService;
import br.com.educlass.util.CursorUtil;
import br.com.educlass.view.teacher.frequency.stutendData;
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
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    public void onSendGradeButtonClick(){
        String path = "db/users/teachers/2/20000/disciplinas.json";
        String data = null;
        int i = 0;
        try {
            data = new String(Files.readAllBytes((Paths.get(path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONArray jsonArray = new JSONArray(data);
        for (studentGradeData estudante : dadosDosAlunos) {
            JSONObject object = jsonArray.getJSONObject(i);
            estudante.setCurrentGrade(estudante.getAddGrade());
            try {
                object.put("grade", estudante.getCurrentGrade());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            i++;
        }
        try {
            // Escrever o arquivo uma vez após o loop
            Files.write(Paths.get(path), jsonArray.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lerArquivo();
    }

    private void setSubjectSelectOptions() {
        //Neste ponto teacher.getSubjects() está retornando null. Ver depois como fazer isso pegar as subjects do JSON
        String[] elementos = {"Estatística Inferencial", "Algorítmos e Estrutura de Dados", "Introdução a Sistemas de Informações", "Tópicos Humanísticos"};
        final ObservableList<String> dadosDasDisciplinas = FXCollections.observableArrayList(elementos);
        subjectSelect.setItems(dadosDasDisciplinas);
    }

    private void setFactoryTable() {
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<studentGradeData, String>("studentName"));
        addGradeColumn.setCellValueFactory(new PropertyValueFactory<studentGradeData,String>("addGrade"));
        currentGradeColumn.setCellValueFactory(new PropertyValueFactory<studentGradeData,String>("currentGrade"));
    }

    @FXML
    private void handleSubjectSelect() {
        lerArquivo();
    }

    public void lerArquivo(){
        dadosDosAlunos = FXCollections.observableArrayList();
        String data = null;
        try {
            data = new String(Files.readAllBytes((Paths.get("db/users/teachers/2/20000/disciplinas.json"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONArray jsonArray = new JSONArray(data);
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject object = jsonArray.getJSONObject(i);
            String str = jsonArray.get(i).toString();
            JSONObject object1 = new JSONObject(str);
            String name = object1.getString("name");
            int currentGrade = object1.getInt("grade");
            dadosDosAlunos.add(new studentGradeData(name, currentGrade));
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
