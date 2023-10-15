package br.com.educlass.view.teacher.frequency;
import javafx.scene.control.CheckBox;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class stutendData {
    private String studentName;
    private int skipClass;
    private CheckBox presence;
    private String idStudent;

    public stutendData(String name, int faltas, String idStudent) {
        this.studentName = name;
        this.skipClass = faltas;
        this.presence = new CheckBox();
        this.idStudent = idStudent;
    }
//    public void checkBoxAnalysis(){
//        System.out.println(this.presence.isSelected());
//    }
    public CheckBox getPresenca() {
        return presence;
    }
//    public void setPresenca(CheckBox presence){
//        this.presence = presence;
//    }
}
