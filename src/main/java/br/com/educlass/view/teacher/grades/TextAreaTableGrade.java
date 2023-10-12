package br.com.educlass.view.teacher.grades;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

public class TextAreaTableGrade extends TableCell<studentGradeData, String> {
    private final TextArea textArea;

    public TextAreaTableGrade() {
        this.textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setOnKeyReleased(event -> {
            if (event.getCode().isArrowKey() || event.getCode().isDigitKey() || event.getCode().isLetterKey()) {
                commitEdit(textArea.getText());
            } else if (event.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
            setText(null);
        } else {
            textArea.setText(item);
            setGraphic(textArea);
        }
    }
}