package br.com.educlass.view.teacher.frequency;

import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.service.teacher.TeacherService;
import br.com.educlass.util.CursorUtil;
import br.com.educlass.util.JsonFile;
import br.com.educlass.util.UserUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FrequencyTeacher implements Initializable {

    @FXML
    private ComboBox<String> subjectSelect;
    @FXML
    private TableView<stutendData> tableView;

    @FXML
    private TableColumn<stutendData, String> studentColumn;

    @FXML
    private TableColumn<stutendData, String> skipClassColumn;

    @FXML
    private TableColumn<stutendData, String> presenceColumn;

    @FXML
    private Button sendButton;

    Teacher teacher;

    ObservableList<stutendData> dadosDosAlunos;

    @FXML
    private void onSendButtonClick() {
        String path = UserUtil.getPathTeacher() + "disciplinas.json";
        String data = null;
        int i = 0;
        JSONArray jsonArray = JsonFile.readJsonFile(path);
        for (stutendData stutendData: dadosDosAlunos) {
            JSONObject object = (JSONObject) jsonArray.get(i);
            String presenceString = String.valueOf(object.get("presenca"));
            int presence = Integer.parseInt(presenceString);
            if (stutendData.getPresenca().isSelected()) {
                presence += 2;
                try {
                    object.put("presenca", presence);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                // Escrever o arquivo uma vez após o loop
                Files.write(Paths.get(path), jsonArray.toString().getBytes(), StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        lerArquivo();
    }

    private void setSubjectSelectOptions() {
        ArrayList arrayElementos = new ArrayList<>();
        String path = UserUtil.getPathTeacher() + "subjects.json";

        JSONArray jsonArray =  JsonFile.readJsonFile(path);
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        JSONArray jsonArrayListOfSubjects = (JSONArray) jsonObject.get("list_of_subjects");
//
        for (Object object : jsonArrayListOfSubjects) {
            String subjectId = (String) object;
            String pathDiscipline = "db/discipline/" + subjectId + "/informations.json";
            JSONArray jsonArray2 = JsonFile.readJsonFile(pathDiscipline);
            JSONObject jsonObject1 = (JSONObject) jsonArray2.get(0);
            String subjectName = (String) jsonObject1.get("name");
            arrayElementos.add(subjectName);
        }

        // Neste ponto teacher.getSubjects() está retornando null. Ver depois como fazer
        // isso pegar as subjects do JSON
        // String[] elementos = {"Estatística Inferencial", "Algorítmos e Estrutura de
        // Dados", "Introdução a Sistemas de Informações", "Tópicos Humanísticos"};
        final ObservableList<String> dadosDasDisciplinas = FXCollections.observableArrayList(arrayElementos);
        subjectSelect.setItems(dadosDasDisciplinas);
    }

    private void setFactoryTable() {
        presenceColumn.setCellValueFactory(new PropertyValueFactory<stutendData, String>("presence"));
        studentColumn.setCellValueFactory(new PropertyValueFactory<stutendData, String>("studentName"));
        skipClassColumn.setCellValueFactory(new PropertyValueFactory<stutendData, String>("skipClass"));
    }

    @FXML
    private void handleSubjectSelect() {
        lerArquivo();
    }

    public void lerArquivo() {
        dadosDosAlunos = FXCollections.observableArrayList();
        String path = UserUtil.getPathTeacher() + "disciplinas.json";

        JSONArray jsonArray = JsonFile.readJsonFile(path);
        for (Object element: jsonArray) {
            JSONObject jsonObject = (JSONObject) element;
            String name = (String) jsonObject.get("name");
            String ageString = String.valueOf(jsonObject.get("presenca"));
            int age = Integer.valueOf(ageString);
            dadosDosAlunos.add(new stutendData(name, age));
        }
        tableView.getItems().clear();
        this.sendButton.setVisible(true);
        this.tableView.setVisible(true);
        tableView.setItems(dadosDosAlunos);
        subjectSelect.getValue();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFactoryTable();
        this.sendButton.setVisible(false);
        this.tableView.setVisible(false);
        this.teacher = TeacherService.getTeacher();
        setSubjectSelectOptions();
        CursorUtil.handleCursorType(Cursor.DEFAULT);
    }
}
