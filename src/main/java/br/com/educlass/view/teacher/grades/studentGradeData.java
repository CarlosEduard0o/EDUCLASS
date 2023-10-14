package br.com.educlass.view.teacher.grades;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class studentGradeData {
    private String studentName;
    private TextField addGrade;
    private ArrayList<String> currentGrade;
    private String idStudent;

    public studentGradeData(String name, String idStudent) {
        this.studentName = name;
        this.currentGrade = new ArrayList<>();
        this.addGrade = new TextField();
        this.idStudent = idStudent;
    }

    public void setCurrentGrade (TextField grade, String tetValue){
        if (!grade.getText().isEmpty()){
            this.currentGrade.add(grade.getText() + " de " + tetValue);
        }
    }

    public void initializeArrayCurrentGrade(String grade){
        this.currentGrade.add(grade);
    }

    public TextField getAddGrade(){
        return addGrade;
    }

    public ArrayList<String> getCurrentGrade(){
        return this.currentGrade;
    }
}
