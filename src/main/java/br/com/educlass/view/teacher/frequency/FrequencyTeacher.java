package br.com.educlass.view.teacher.frequency;

import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.model.subjects.Subject;
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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
       // String data = null;
        int i = 0;
        for (stutendData stutendData : dadosDosAlunos) {
            String path = "db/users/students/" + stutendData.getIdStudent().substring(1, 5) + "/" + stutendData.getIdStudent().substring(0, 1) + "/" + stutendData.getIdStudent().substring(5) + "/subjects.json";
            JSONArray jsonArray = JsonFile.readJsonFile(path);
            for (Object element : jsonArray) {
                JSONObject jsonObject = (JSONObject) element;
                if (jsonObject.get("name").equals(subjectSelect.getValue())) {
                    String presenceString = String.valueOf(jsonObject.get("faltas"));
                    int faltas = Integer.parseInt(presenceString);
                    if (stutendData.getPresenca().isSelected()) {
                        faltas += 2;
                        try {
                            jsonObject.put("faltas", faltas);
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
            }
        }
        refreshTable();
    }

    private void refreshTable(){
        dadosDosAlunos = FXCollections.observableArrayList();
        ArrayList<String> arrayFaltas = new ArrayList<>();
        ArrayList<String> arrayNomesAlunos = new ArrayList<>();
        ArrayList<String> arrayMatricula = new ArrayList<>();
        ArrayList<Subject> subjects = initData();
        for (Subject subject : subjects) {
            if (subject.getName().equals(subjectSelect.getValue())) {
                for (String idAlunos : subject.getEnrolledStudents()) {
                    arrayMatricula.add(idAlunos);
                    String pathSubject = "db/users/students/" + idAlunos.substring(1, 5) + "/" + idAlunos.substring(0, 1) + "/" + idAlunos.substring(5) + "/subjects.json";
                    String pathInformations = "db/users/students/" + idAlunos.substring(1, 5) + "/" + idAlunos.substring(0, 1) + "/" + idAlunos.substring(5) + "/informations.txt";
                    JSONArray jsonArray = JsonFile.readJsonFile(pathSubject);
                    for (Object element: jsonArray) {
                        JSONObject jsonObject = (JSONObject) element;
                        if(jsonObject.get("name").equals(subjectSelect.getValue())){
                            arrayFaltas.add(jsonObject.get("faltas").toString());
                        }
                    }
                    try {
                        FileReader fileReader = new FileReader(pathInformations);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String nome = null;
                        String linha;
                        while ((linha = bufferedReader.readLine()) != null) {
                            nome = linha.substring(5);
                            arrayNomesAlunos.add(nome);
                            break;
                        }
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        for (int i = 0; i < arrayFaltas.size(); i++){
            dadosDosAlunos.add(new stutendData(arrayNomesAlunos.get(i), Integer.parseInt(arrayFaltas.get(i)), arrayMatricula.get(i)));
        }
        tableView.getItems().clear();
        this.sendButton.setVisible(true);
        this.tableView.setVisible(true);
        tableView.setItems(dadosDosAlunos);
    }

    private void setSubjectSelectOptions() {
        ArrayList<Subject> subjects = initData();
        ArrayList arrayElementos = new ArrayList<>();
        for (Subject subject : subjects){
            arrayElementos.add(subject.getName());
        }
        final ObservableList<String> dadosDasDisciplinas = FXCollections.observableArrayList(arrayElementos);
        subjectSelect.setItems(dadosDasDisciplinas);
    }

    private ArrayList initData() {
        ArrayList<Subject> arrayListSubject = new ArrayList<>();
        String path = UserUtil.getPathTeacher() + "subjects.json";
        JSONArray jsonArray =  JsonFile.readJsonFile(path);
        if (jsonArray != null) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            JSONArray jsonArrayListOfSubjects = (JSONArray) jsonObject.get("list_of_subjects");
            for (Object object : jsonArrayListOfSubjects) {
                Subject subject = new Subject();
                String subjectId = (String) object;
                subject.setId(subjectId);
                String pathDiscipline = "db/discipline/" + subjectId + "/informations.json";
                JSONArray jsonArray2 = JsonFile.readJsonFile(pathDiscipline);
                JSONObject jsonObject1 = (JSONObject) jsonArray2.get(0);
                String subjectName = (String) jsonObject1.get("name");
                ArrayList<String> enrolledStudents = (ArrayList<String>) jsonObject1.get("students");
                subject.setName(subjectName);
                subject.setEnrolledStudents(enrolledStudents);
                arrayListSubject.add(subject);
            }
        }
        return arrayListSubject;
    }

    private void setFactoryTable() {
        presenceColumn.setCellValueFactory(new PropertyValueFactory<stutendData, String>("presence"));
        studentColumn.setCellValueFactory(new PropertyValueFactory<stutendData, String>("studentName"));
        skipClassColumn.setCellValueFactory(new PropertyValueFactory<stutendData, String>("skipClass"));
        this.tableView.setTableMenuButtonVisible(true);
    }

    @FXML
    private void handleSubjectSelect() {
        dadosDosAlunos = FXCollections.observableArrayList();
        ArrayList<String> arrayFaltas = new ArrayList<>();
        ArrayList<String> arrayNomesAlunos = new ArrayList<>();
        ArrayList<String> arrayMatricula = new ArrayList<>();
        ArrayList<Subject> subjects = initData();
        for (Subject subject : subjects) {
            if (subject.getName().equals(subjectSelect.getValue())) {
                for (String idAlunos : subject.getEnrolledStudents()) {
                    arrayMatricula.add(idAlunos);
                    String pathSubject = "db/users/students/" + idAlunos.substring(1, 5) + "/" + idAlunos.substring(0, 1) + "/" + idAlunos.substring(5) + "/subjects.json";
                    String pathInformations = "db/users/students/" + idAlunos.substring(1, 5) + "/" + idAlunos.substring(0, 1) + "/" + idAlunos.substring(5) + "/informations.txt";
                    JSONArray jsonArray = JsonFile.readJsonFile(pathSubject);
                    for (Object element: jsonArray) {
                        JSONObject jsonObject = (JSONObject) element;
                        if(jsonObject.get("name").equals(subjectSelect.getValue())){
                            arrayFaltas.add(jsonObject.get("faltas").toString());
                        }
                    }
                    try {
                        FileReader fileReader = new FileReader(pathInformations);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String nome = null;
                        String linha;
                        while ((linha = bufferedReader.readLine()) != null) {
                            nome = linha.substring(5);
                            arrayNomesAlunos.add(nome);
                            break;
                        }
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        for (int i = 0; i < arrayFaltas.size(); i++){
            dadosDosAlunos.add(new stutendData(arrayNomesAlunos.get(i), Integer.parseInt(arrayFaltas.get(i)), arrayMatricula.get(i)));
        }
        tableView.getItems().clear();
        this.sendButton.setVisible(true);
        this.tableView.setVisible(true);
        tableView.setItems(dadosDosAlunos);
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
