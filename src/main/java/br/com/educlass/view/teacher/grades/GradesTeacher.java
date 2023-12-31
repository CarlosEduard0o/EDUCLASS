package br.com.educlass.view.teacher.grades;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.model.subjects.Subject;
import br.com.educlass.service.student.StudentService;
import br.com.educlass.service.teacher.TeacherService;
import br.com.educlass.util.CursorUtil;
import br.com.educlass.util.JsonFile;
import br.com.educlass.util.Language;
import br.com.educlass.util.UserUtil;
import br.com.educlass.view.teacher.frequency.stutendData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
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
import java.util.HashMap;
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

    @FXML
    private TextField testValue;

    @FXML
    private Label testMessage;

    @FXML
    private Text gradesTitle;

    Teacher teacher;

    ObservableList<studentGradeData> dadosDosAlunos;

    private String WarningValueGradeGreaterThanValueTest;

    private String WarningPutValueTest;
    private String AlertTitle;
    private String NotesSentSuccessfully;
    private String Success;
    private String ThereIsNotContentInTheTable;

    private void setLanguage() {
        // Teacher teacher = TeacherService.setTeacherInfo();
        HashMap<String, String> texts = Language
                .getTexts("src/main/resources/br/com/educlass/view/teacher/grades/languages/");
        subjectSelect.setPromptText(texts.get("subjectSelect"));
        // tableView.setText(texts.get("register")+": " + teacher.getRegistration());
        studentNameColumn.setText(texts.get("studentNameColumn"));
        addGradeColumn.setText(texts.get("addGradeColumn"));
        currentGradeColumn.setText(texts.get("currentGradeColumn"));
        sendGradeButton.setText(texts.get("sendGradeButton"));
        testMessage.setText(texts.get("testMessage") + ":");
        gradesTitle.setText(texts.get("gradesTitle"));
        WarningValueGradeGreaterThanValueTest = texts.get("WarningValueGradeGreaterThanValueTest");
        WarningPutValueTest = texts.get("WarningPutValueTest");
        Success = texts.get("Success");
        NotesSentSuccessfully = texts.get("NotesSentSuccessfully");
        AlertTitle = texts.get("AlertTitle");
        ThereIsNotContentInTheTable = texts.get("ThereIsNotContentInTheTable");
    }

    @FXML
    public void onSendGradeButtonClick() {
        // String data = null;
        if (!testValue.getText().isEmpty()) {
            String valorProva = testValue.getText();
            int i = 0;
            boolean validaTestValue = false;
            for (studentGradeData stutendGradeData : dadosDosAlunos) {
                String path = "db/users/students/" + stutendGradeData.getIdStudent().substring(1, 5) + "/"
                        + stutendGradeData.getIdStudent().substring(0, 1) + "/"
                        + stutendGradeData.getIdStudent().substring(5) + "/subjects.json";
                JSONArray jsonArray = JsonFile.readJsonFile(path);
                for (Object element : jsonArray) {
                    JSONObject jsonObject = (JSONObject) element;
                    if (jsonObject.get("name").equals(subjectSelect.getValue())) {
                        JSONObject elementObject = (JSONObject) element;
                        JSONArray grades = (JSONArray) elementObject.get("notas");
                        if (converterEmInt(stutendGradeData.getAddGrade()) > Integer.valueOf(valorProva)) {
                            validaTestValue = true;
                            showAlert(WarningValueGradeGreaterThanValueTest);
                        } else {
                            if (grades.get(0).equals("----------")) {
                                stutendGradeData.setFirstCurrentGrade(stutendGradeData.getAddGrade(), valorProva);
                            } else {
                                stutendGradeData.setCurrentGrade(stutendGradeData.getAddGrade(), valorProva);
                            }
                            jsonObject.put("notas", stutendGradeData.getCurrentGrade());
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
            if (!validaTestValue) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle(Success);
                successAlert.setHeaderText(null);
                successAlert.setContentText(NotesSentSuccessfully);
                successAlert.showAndWait();
            }
            setData();
            testValue.clear();
        } else {
            showAlert(WarningPutValueTest);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(AlertTitle);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void setData() {
        dadosDosAlunos = FXCollections.observableArrayList();
        ArrayList<String> arrayNomesAlunos = new ArrayList<>();
        ArrayList<String> arrayMatricula = new ArrayList<>();
        ArrayList<Subject> subjects = initData();
        for (Subject subject : subjects) {
            int cont = 0;
            if (subject.getName().equals(subjectSelect.getValue())) {
                for (Student student : subject.getEnrolledStudents()) {
                    String idAlunos = student.getRegistration();
                    arrayMatricula.add(idAlunos);
                    String pathSubject = "db/users/students/" + idAlunos.substring(1, 5) + "/"
                            + idAlunos.substring(0, 1) + "/" + idAlunos.substring(5) + "/subjects.json";
                    String pathInformations = "db/users/students/" + idAlunos.substring(1, 5) + "/"
                            + idAlunos.substring(0, 1) + "/" + idAlunos.substring(5) + "/informations.txt";
                    JSONArray jsonArray = JsonFile.readJsonFile(pathSubject);
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
                    for (Object element : jsonArray) {
                        JSONObject jsonObject = (JSONObject) element;
                        if (jsonObject.get("name").equals(subjectSelect.getValue())) {
                            JSONArray jsonArrayGrades = (JSONArray) jsonObject.get("notas");
                            studentGradeData studentInstance = new studentGradeData();
                            studentInstance.setStudentName(arrayNomesAlunos.get(cont));
                            studentInstance.setIdStudent(arrayMatricula.get(cont));
                            for (int i = 0; i < jsonArrayGrades.size(); i++) {
                                String nota = jsonArrayGrades.get(i).toString();
                                studentInstance.initializeArrayCurrentGrade(nota);
                            }
                            dadosDosAlunos.add(studentInstance);
                            cont++;
                        }
                    }

                }
            }
        }
        tableView.setPlaceholder(new Label(ThereIsNotContentInTheTable));
        this.sendGradeButton.setVisible(true);
        this.tableView.setVisible(true);
        this.testValue.setVisible(true);
        this.testMessage.setVisible(true);
        tableView.setItems(dadosDosAlunos);
    }

    private void setSubjectSelectOptions() {
        ArrayList<Subject> subjects = initData();
        ArrayList arrayElementos = new ArrayList<>();
        for (Subject subject : subjects) {
            arrayElementos.add(subject.getName());
        }
        final ObservableList<String> dadosDasDisciplinas = FXCollections.observableArrayList(arrayElementos);
        subjectSelect.setItems(dadosDasDisciplinas);
    }

    private void setFactoryTable() {
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<studentGradeData, String>("studentName"));
        addGradeColumn.setCellValueFactory(new PropertyValueFactory<studentGradeData, String>("addGrade"));
        currentGradeColumn.setCellValueFactory(new PropertyValueFactory<studentGradeData, String>("currentGrade"));
        this.tableView.setTableMenuButtonVisible(true);
    }

    private int converterEmInt(TextField grade) {
        String textGrade = grade.getText(); // Sua String de entrada
        try {
            Integer intGrade = Integer.valueOf(textGrade);
            int numero = intGrade.intValue(); // Para obter o valor primitivo int
            return numero;
        } catch (NumberFormatException e) {
            System.err.println("Erro na conversão: " + e.getMessage());
        }
        return 0;
    }

    private ArrayList initData() {
        ArrayList<Subject> arrayListSubject = new ArrayList<>();
        String path = UserUtil.getPathTeacher() + "subjects.json";
        JSONArray jsonArray = JsonFile.readJsonFile(path);
        if (jsonArray != null) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            JSONArray jsonArrayListOfSubjects = (JSONArray) jsonObject.get("list_of_subjects");
            for (Object object : jsonArrayListOfSubjects) {
                Subject subject = new Subject();
                String subjectId = (String) object;
                subject.setId(subjectId);
                String pathDiscipline = "db/subjects/" + subjectId + "/informations.json";
                JSONArray jsonArray2 = JsonFile.readJsonFile(pathDiscipline);
                JSONObject jsonObject1 = (JSONObject) jsonArray2.get(0);
                String subjectName = (String) jsonObject1.get("name");
                ArrayList<String> enrolledStudents = (ArrayList<String>) jsonObject1.get("students");
                subject.setName(subjectName);
                ArrayList<Student> students = new ArrayList<>();
                for (String student : enrolledStudents) {
                    Student atualStudent = StudentService
                            .setStudentInfoForList(UserUtil.getStudentUserPathById(student));
                    students.add(atualStudent);
                }
                subject.setEnrolledStudents(students);
                arrayListSubject.add(subject);
            }
        }
        return arrayListSubject;
    }

    @FXML
    private void handleSubjectSelect() {
        setData();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFactoryTable();
        setLanguage();
        this.sendGradeButton.setVisible(false);
        this.tableView.setVisible(false);
        this.testValue.setVisible(false);
        tableView.setPlaceholder(new Label(ThereIsNotContentInTheTable));
        this.testMessage.setVisible(false);
        this.teacher = TeacherService.getTeacher();
        setSubjectSelectOptions();
        CursorUtil.handleCursorType(Cursor.DEFAULT);
    }
}
