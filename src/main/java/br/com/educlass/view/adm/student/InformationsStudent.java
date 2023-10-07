package br.com.educlass.view.adm.student;

import br.com.educlass.model.person.student.Student;

public class InformationsStudent {
    private static Student studentSelected;

    public static void setStudentSelected(Student student) {
        studentSelected = student;
    }

    public static Student getStudentSelected() {
        return  studentSelected;
    }
}
