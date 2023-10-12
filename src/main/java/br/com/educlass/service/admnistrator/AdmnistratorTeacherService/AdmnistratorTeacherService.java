package br.com.educlass.service.admnistrator.AdmnistratorTeacherService;

import br.com.educlass.model.institution.Institution;
import br.com.educlass.model.person.teacher.Teacher;

import java.util.ArrayList;

public class AdmnistratorTeacherService {
    private static Teacher newTeacher;
    private static Teacher editTeacher;

    public static void setNewTeacher(Teacher teacher, Institution adm) {
        ArrayList<Teacher> teachers = adm.getTeachers();
        teachers.add(teacher);
        newTeacher = teacher;
        adm.setTeachers(teachers);
    }

    public static Teacher getNewTeacher(){
        return newTeacher;
    }

    public static Teacher getEditTeacher() {
        return editTeacher;
    }

    public static void editTeacher(Teacher editTeacherInput, Institution adm) {
        editTeacher = editTeacherInput;
        ArrayList<Teacher> listOfTeachers = adm.getTeachers();
        ArrayList<Teacher> listOfTeachersAtt = new ArrayList<>();
        for(Teacher teacher: listOfTeachers) {
            if(teacher.getRegistration().
                    equalsIgnoreCase(editTeacher.getRegistration())) {
                listOfTeachersAtt.add(editTeacher);
            } else {
                listOfTeachersAtt.add(teacher);
            }
        }
        adm.setTeachers(listOfTeachersAtt);
    }
}
