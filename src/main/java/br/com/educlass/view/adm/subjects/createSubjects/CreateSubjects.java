package br.com.educlass.view.adm.subjects.createSubjects;

import br.com.educlass.model.institution.Institution;
import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.service.admnistrator.AdmnistratorService;
import br.com.educlass.service.admnistrator.AdmnistratorStudentService.AdmnistratorStudentService;
import br.com.educlass.service.admnistrator.AdmnistratorSubjectService.AdmnistratorSubjectService;
import br.com.educlass.service.student.StudentService;
import br.com.educlass.util.*;
import br.com.educlass.view.adm.subjects.CreateSubjectTable;
import br.com.educlass.view.adm.subjects.SubjectTabel;
import br.com.educlass.view.adm.subjects.SubjectsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.spec.RSAOtherPrimeInfo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CreateSubjects implements Initializable {
    @FXML
    Text textTitle;
    @FXML
    Text textSubjectName;
    @FXML
    Text textQuantityLessions;
    @FXML
    Text textInserrtStudent;
    @FXML
    TextField textFieldNameSubject;
    @FXML
    TextField textFieldQuantityLessions;
    @FXML
    ComboBox<String> comboBoxSelectTeacher;
    @FXML
    TableView<CreateSubjectTable> tableView;
    @FXML
    TableColumn<CreateSubjectTable, String>  selectColumn;
    @FXML
    TableColumn<CreateSubjectTable, String>  idColumn;
    @FXML
    TableColumn<CreateSubjectTable, String>  nameColumn;
    @FXML
    Button buttonCancel;
    @FXML
    Button buttonConfirm;

    ObservableList<CreateSubjectTable> subjectsData;

    String disciplineID;

    private void setFactoryTable() {
        selectColumn.setCellValueFactory( new PropertyValueFactory<CreateSubjectTable, String>("selectColumn"));
        idColumn.setCellValueFactory(new PropertyValueFactory<CreateSubjectTable, String>("idColumn"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<CreateSubjectTable, String>("nameColumn"));
    }

    private void populateTeachersInCombobox() {
        ArrayList<Teacher> teachers = AdmnistratorService.getAdmnistrator().getTeachers();
        for (Teacher teacher: teachers) {
            comboBoxSelectTeacher.getItems().add(teacher.getRegistration() + " - " +teacher.getName());
        }
    }

    private void populateStudentsInTable() {
        subjectsData = FXCollections.observableArrayList();

        ArrayList<Student> students =  AdmnistratorService.getListOfStudents();
        for(Student student: students) {
            String studentName = student.getName();
            String studentRegistration = student.getRegistration();
            subjectsData.add(
                    new CreateSubjectTable(
                            studentName,
                            studentRegistration
                    )
            );
        }
        tableView.getItems().clear();
        tableView.setItems(subjectsData);
    }

    private void setInitializeInformations() {
        populateTeachersInCombobox();
        populateStudentsInTable();
    }

    @FXML
    private void verifyNumber() {
        String entrada = textFieldQuantityLessions.getText().replaceAll("[^0-9]", "");
        textFieldQuantityLessions.setPromptText(entrada);
    }

    @FXML
    private void verifyInformationsSetteds() {
        String nameInput = textFieldNameSubject.getText();
        Long quantityInput = Long.parseLong(textFieldQuantityLessions.getText());
        String comboboxTeacher = comboBoxSelectTeacher.getValue();

        if(nameInput != null && nameInput != "" &&
                quantityInput != null && quantityInput >= 0 &&
                comboboxTeacher != null
        ) {
            buttonConfirm.setDisable(false);
        } else {
            buttonConfirm.setDisable(true);
        }
    }

    private void createFolderInSubject() {
        String nameInput = textFieldNameSubject.getText();
        Long quantityInput = Long.parseLong(textFieldQuantityLessions.getText());
        String comboboxTeacher = comboBoxSelectTeacher.getValue();

        ArrayList<String> studentsIds = new ArrayList<>();
        for (CreateSubjectTable createSubjectTable: subjectsData) {
            if(createSubjectTable.getSelectColumn().isSelected()) {
                studentsIds.add(createSubjectTable.getIdColumn());
            }
        }

        LocalDate date = LocalDate.now();
        String year = String.valueOf(date.getYear());
        String semester = date.getMonth().getValue() >= 6 ? "2" : "1";

        String path = "db/subjects/";
        int itemsLength = Folders.lengthItemsInDir(new File(path));

        String itemsLengthString = String.valueOf(itemsLength);
        while (itemsLengthString.length() < 4) {
            itemsLengthString = "0" + itemsLengthString;
        }

        path = "db/subjects/"+year+semester+itemsLengthString+"/";
        Folders.createFolder(new File(path));

        this.disciplineID = (year+semester+itemsLengthString);

        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", nameInput);
        jsonObject.put("id", disciplineID);
        jsonObject.put("teacher", comboboxTeacher.split("-")[0].trim());
        jsonObject.put("students", studentsIds);
        jsonObject.put("quantidade_de_aulas", quantityInput);
        jsonObjects.add(jsonObject);
        JsonFile.writeJsonFile(jsonObjects, path, "informations");
        Institution adm = AdmnistratorService.getAdmnistrator();
        adm.setSubjects(AdmnistratorSubjectService.getAllSubjects());
        AdmnistratorService.setAdmnistrator(adm);
    }

    private void createSubjectInStudent() {
        String nameInput = textFieldNameSubject.getText();
        String comboboxTeacher = comboBoxSelectTeacher.getValue();

        ArrayList<String> gradesNotFound = new ArrayList<>();
        gradesNotFound.add("Sem notas");

        for (CreateSubjectTable createSubjectTable: subjectsData) {
            if(createSubjectTable.getSelectColumn().isSelected()) {
                String studentId = createSubjectTable.getIdColumn();
                String year = studentId.substring(1, 5);
                String semester = studentId.substring(0, 1);
                String registration = studentId.substring(5);

                String path = "db/subjects/";
                int itemsLength = Folders.lengthItemsInDir(new File(path));
                String itemsLengthString = String.valueOf(itemsLength);
                while (itemsLengthString.length() < 4) {
                    itemsLengthString = "0" + itemsLengthString;
                }

                path = "db/users/students/" + year + "/" + semester + "/" + registration + "/";
                try {
                    ArrayList<JSONObject> subjects = new ArrayList<>();
                    JSONArray jsonArray = JsonFile.readJsonFile(path+"subjects.json");
                    for(Object json: jsonArray) {
                        JSONObject jsonObject = new JSONObject();
                        JSONObject itemInMemory = (JSONObject) json;
                        jsonObject.put("name", itemInMemory.get("name"));
                        jsonObject.put("id", itemInMemory.get("id"));
                        jsonObject.put("teacher", itemInMemory.get("teacher"));
                        jsonObject.put("situation", itemInMemory.get("situation"));
                        jsonObject.put("tempo", itemInMemory.get("tempo"));
                        jsonObject.put("faltas", itemInMemory.get("faltas"));
                        jsonObject.put("notas", itemInMemory.get("notas"));
                        jsonObject.put("period", itemInMemory.get("period"));
                        subjects.add(jsonObject);
                    }
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", nameInput);
                    jsonObject.put("id", disciplineID);
                    jsonObject.put("teacher", comboboxTeacher.split("-")[1].trim());
                    jsonObject.put("situation", "Cursando");
                    jsonObject.put("tempo", 0);
                    jsonObject.put("faltas", 0);
                    jsonObject.put("notas", gradesNotFound);
                    jsonObject.put("period", semester);
                    subjects.add(jsonObject);

                    JsonFile.writeJsonFile(subjects, path, "subjects");
                } catch (Exception e) {
                    ArrayList<JSONObject> jsonObjects = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", nameInput);
                    jsonObject.put("id", disciplineID);
                    jsonObject.put("teacher", comboboxTeacher.split("-")[1].trim());
                    jsonObject.put("situation", "Cursando");
                    jsonObject.put("tempo", 0);
                    jsonObject.put("faltas", 0);
                    jsonObject.put("notas", gradesNotFound);
                    jsonObject.put("period", semester);
                    jsonObjects.add(jsonObject);
                    JsonFile.writeJsonFile(jsonObjects, path, "subjects");
                }
            }
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void createSubjectInTeacher() {
        LocalDate date = LocalDate.now();
        String year = String.valueOf(date.getYear());
        String semester = date.getMonth().getValue() >= 6 ? "2" : "1";

        String path = "db/subjects/";
        int itemsLength = Folders.lengthItemsInDir(new File(path));

        String itemsLengthString = String.valueOf(itemsLength);
        while (itemsLengthString.length() < 4) {
            itemsLengthString = "0" + itemsLengthString;
        }

        String comboboxTeacher = comboBoxSelectTeacher.getValue().split("-")[0].trim();

        String teacherId = comboboxTeacher.substring(1);
        path = "db/users/teachers/" + teacherId + "/";
        try {
            ArrayList<String> listOfSubjects = new ArrayList<>();
            JSONObject jsonObject = (JSONObject) JsonFile.readJsonFile(path+"subjects.json").get(0);
            JSONArray jsonArrayListOFSubjects = (JSONArray) jsonObject.get("list_of_subjects");
            for (Object o : jsonArrayListOFSubjects) {
                String s = (String) o;
                listOfSubjects.add(s);
            }
            listOfSubjects.add(disciplineID);
            JSONObject writeJson = new JSONObject();
            writeJson.put("list_of_subjects", listOfSubjects);
            ArrayList<JSONObject> jsonObjects = new ArrayList<>();
            jsonObjects.add(writeJson);
            JsonFile.writeJsonFile(jsonObjects, path, "subjects");

        } catch (Exception e) {
            ArrayList<JSONObject> jsonObjects = new ArrayList<>();
            JSONObject jsonObject = new JSONObject();
            ArrayList<String> subjects = new ArrayList<>();
            subjects.add(disciplineID);
            jsonObject.put("list_of_subjects", subjects);
            jsonObjects.add(jsonObject);
            JsonFile.writeJsonFile(jsonObjects, path, "subjects");
        }

    }

    @FXML
    private void buttonAddSubjectPress() throws IOException {
        createFolderInSubject();
        createSubjectInStudent();
        createSubjectInTeacher();

        URL fxml = SubjectsController.class.getResource("subjects.fxml");
        ContentContainer.setSceneContentContainer(fxml);
    }

    @FXML
    private  void buttonCancelPress()  throws IOException{
        URL fxml = SubjectsController.class.getResource("subjects.fxml");
        ContentContainer.setSceneContentContainer(fxml);
    }

    private void setLanguage() {
        HashMap<String, String> texts = Language
                .getTexts("src/main/resources/br/com/educlass/view/adm/subjects/createSubjects/languages/");

        textTitle.setText(texts.get("textTitle"));
        textSubjectName.setText(texts.get("textSubjectName"));
        textQuantityLessions.setText(texts.get("textQuantityLessions"));
        comboBoxSelectTeacher.setPromptText(texts.get("comboBoxSelectTeacher"));
        textInserrtStudent.setText(texts.get("textInserrtStudent"));
        selectColumn.setText(texts.get("selectColumn"));
        idColumn.setText(texts.get("idColumn"));
        nameColumn.setText(texts.get("nameColumn"));
        buttonCancel.setText(texts.get("buttonCancel"));
        buttonConfirm.setText(texts.get("buttonConfirm"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFactoryTable();
        buttonConfirm.setDisable(true);
        setInitializeInformations();
        setLanguage();
    }
}
