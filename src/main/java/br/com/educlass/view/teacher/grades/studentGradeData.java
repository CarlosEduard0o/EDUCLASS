package br.com.educlass.view.teacher.grades;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class studentGradeData {
    private String studentName;
    private TextField addGrade;
    private int currentGrade;

    public studentGradeData(String name, int currentGrade) {
        this.studentName = name;
        this.currentGrade = currentGrade;
        this.addGrade = new TextField();
    }

    public void setCurrentGrade (TextField grade){
        if (!grade.getText().isEmpty()){
            this.currentGrade += Integer.parseInt(grade.getText());
        }
    }

    public TextField getAddGrade(){
        return addGrade;
    }

    public int getCurrentGrade(){
        return this.currentGrade;
    }
}
