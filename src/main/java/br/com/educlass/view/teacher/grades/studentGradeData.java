package br.com.educlass.view.teacher.grades;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class studentGradeData {
    private String studentName;
    private TextField addGrade;
    private ArrayList<String> currentGrade;
    private String idStudent;

    public studentGradeData() {
        this.currentGrade = new ArrayList<>();
        this.addGrade = new TextField();
    }

    public void setCurrentGrade (TextField grade, String testValue){
        if (!grade.getText().isEmpty()){
            this.currentGrade.add(grade.getText() + "-" + testValue);
        }
    }

    public void setFirstCurrentGrade (TextField grade, String testValue){
        if (!grade.getText().isEmpty()){
            this.currentGrade.remove(0);
            this.currentGrade.add(0,  grade.getText() + "-" + testValue);
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
