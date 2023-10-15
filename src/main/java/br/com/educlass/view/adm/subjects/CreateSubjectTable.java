package br.com.educlass.view.adm.subjects;

import javafx.scene.control.CheckBox;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubjectTable {
    private CheckBox selectColumn;
    private String idColumn;
    private String nameColumn;

    public CreateSubjectTable(String name, String id) {
        this.selectColumn = new CheckBox();
        this.idColumn = id;
        this.nameColumn = name;
    }
    public CheckBox getSelected() {
        return selectColumn;
    }
}
