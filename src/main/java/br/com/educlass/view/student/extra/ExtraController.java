package br.com.educlass.view.student.extra;

import br.com.educlass.util.CursorUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ExtraController implements Initializable {

    private void openTerminal() {
        try {
            String comandOpenCmd = "cmd /c ";
            String queryComand = "\"cd db/extra/Astro && python main.py\"";

            String finalComand = comandOpenCmd + queryComand;
            Runtime.getRuntime().exec(finalComand);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void astroGameButtonHasPressed() {
        openTerminal();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        CursorUtil.handleCursorType(Cursor.DEFAULT);
    }
}
