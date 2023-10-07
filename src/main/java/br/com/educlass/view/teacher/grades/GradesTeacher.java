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
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    @FXML
    public void onSendGradeButtonClick(){
        System.out.println("Teste");
    }

    private void setSubjectSelectOptions() {
        //String[] subjects = {teacher.getSubjects().toString()};
        //Neste ponto teacher.getSubjects() está retornando null. Ver depois como fazer isso pegar as subjects do JSON
        String[] elementos = {"Estatística Inferencial", "Algorítmos e Estrutura de Dados", "Introdução a Sistemas de Informações", "Tópicos Humanísticos"};
        ObservableList<String> observableList = FXCollections.observableArrayList(elementos);
        subjectSelect.setItems(observableList);
    }



    private void setFactoryTable() {
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<studentGradeData, String>("studentName"));
        addGradeColumn.setCellValueFactory(new PropertyValueFactory<studentGradeData,String>("addGrade"));
        currentGradeColumn.setCellValueFactory(new PropertyValueFactory<studentGradeData,String>("currentGrade"));
    }

    @FXML
    private void handleSubjectSelect() {
        final ObservableList<studentGradeData> dadosDosAlunos = FXCollections.observableArrayList();
        String data = null;
        try {
            data = new String(Files.readAllBytes((Paths.get("C:\\Users\\CarlosEduardodeAlmei\\Desktop\\FAITEC-EDUCLASS-master\\db\\users\\teachers\\2\\20000\\testes.json"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONArray jsonArray = new JSONArray(data);
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject object = jsonArray.getJSONObject(i);
            String str = jsonArray.get(i).toString();
            JSONObject object1 = new JSONObject(str);
            String name = object1.getString("name");
            int grade = object1.getInt("grade");
            dadosDosAlunos.add(new studentGradeData(name, grade, ""));
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
