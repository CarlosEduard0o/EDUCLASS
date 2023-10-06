package br.com.educlass.view.teacher.frequency;
import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.service.teacher.TeacherService;
import br.com.educlass.util.CursorUtil;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

    @FXML
    private void onSendButtonClick(){
        int actualAge;
        String path = "D:\\FAITEC-EDUCLASS-master\\db\\users\\teachers\\2\\20000\\testes.json";
        final ObservableList<stutendData> dadosDosAlunos = FXCollections.observableArrayList();
        String data = null;
        try {
            data = new String(Files.readAllBytes((Paths.get(path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONArray jsonArray = new JSONArray(data);
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject object = jsonArray.getJSONObject(i);
            String str = jsonArray.get(i).toString();
            JSONObject object1 = new JSONObject(str);
            String name = object1.getString("name");
            int age = object1.getInt("presenca");
            stutendData estudante = new stutendData(name, age);
            dadosDosAlunos.add(estudante);
            estudante.checkBoxAnalysis();
            if (estudante.getSelect().isSelected()){
                actualAge = age - 1;
                try {
                    object.put("presenca", actualAge);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            // Escrever o arquivo uma vez após o loop
            Files.write(Paths.get(path), jsonArray.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setSubjectSelectOptions() {
        //Neste ponto teacher.getSubjects() está retornando null. Ver depois como fazer isso pegar as subjects do JSON
        String[] elementos = {"Estatística Inferencial", "Algorítmos e Estrutura de Dados", "Introdução a Sistemas de Informações", "Tópicos Humanísticos"};
        final ObservableList<String> dadosDasDisciplinas = FXCollections.observableArrayList(elementos);
        subjectSelect.setItems(dadosDasDisciplinas);
    }


    private void setFactoryTable() {
        presenceColumn.setCellValueFactory(new PropertyValueFactory<stutendData, String>("presence"));
        studentColumn.setCellValueFactory(new PropertyValueFactory<stutendData,String>("studentName"));
        skipClassColumn.setCellValueFactory(new PropertyValueFactory<stutendData,String>("skipClass"));
    }

    @FXML
    private void handleSubjectSelect() {
        final ObservableList<stutendData> dadosDosAlunos = FXCollections.observableArrayList();
        String data = null;
            try {
                data = new String(Files.readAllBytes((Paths.get("D:\\FAITEC-EDUCLASS-master\\db\\users\\teachers\\2\\20000\\testes.json"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            JSONArray jsonArray = new JSONArray(data);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                String str = jsonArray.get(i).toString();
                JSONObject object1 = new JSONObject(str);
                String name = object1.getString("name");
                int age = object1.getInt("presenca");
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
