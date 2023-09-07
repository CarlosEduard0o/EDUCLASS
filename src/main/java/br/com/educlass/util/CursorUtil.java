package br.com.educlass.util;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class CursorUtil {
    private static Pane mainPane;

    /**
     * Handle cursor in all window.
     * @param cursor cursor needed.
     */
    public static void handleCursorType(Cursor cursor) {
        Scene scene = mainPane.getScene();
        scene.setCursor(cursor);
    }

    public static void setMainPane(Pane pane) {
        mainPane = pane;
    }

}
