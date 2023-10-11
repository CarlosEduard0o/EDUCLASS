package br.com.educlass.view.adm.teacher;

import br.com.educlass.model.person.teacher.Teacher;

public class InformationsTeacher {
    private static Teacher teacherSelected;

    public static void setTeacherSelected(Teacher teacher) {
        teacherSelected = teacher;
    }

    public static Teacher getTeacherSelected() {
        return  teacherSelected;
    }
}
