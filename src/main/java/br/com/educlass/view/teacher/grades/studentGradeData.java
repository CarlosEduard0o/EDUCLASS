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

    public studentGradeData(String name, int currentGrade, String addGrade) {
        this.studentName = name;
        this.currentGrade = currentGrade;
        this.addGrade = new TextField(addGrade);
    }

    public void setAddGrade (TextField addGrade){
        this.addGrade = addGrade;
    }

    public TextField getAddGrade(){
        return addGrade;
    }
}
