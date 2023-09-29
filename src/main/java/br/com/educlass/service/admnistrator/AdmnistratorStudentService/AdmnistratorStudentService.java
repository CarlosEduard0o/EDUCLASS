package br.com.educlass.service.admnistrator.AdmnistratorStudentService;

import br.com.educlass.model.institution.Institution;
import br.com.educlass.model.person.student.Student;
import java.util.ArrayList;

public class AdmnistratorStudentService {
    private static Student newStudent;
    private static Student editStudent;


    public static void setNewStudent(Student student, Institution adm) {
        ArrayList<Student> students = adm.getStudents();
        students.add(student);
        newStudent = student;
        adm.setStudents(students);
    }

    public static Student getNewStudent(){
        return newStudent;
    }

    public static Student getEditStudent() {
        return editStudent;
    }

    public static void editStudent(Student editStudentInput, Institution adm) {
        editStudent = editStudentInput;
        ArrayList<Student> listOfStudents = adm.getStudents();
        ArrayList<Student> listOfStudentsAtt = new ArrayList<>();
        for(Student student: listOfStudents) {
            if(student.getRegistration().
                    equalsIgnoreCase(editStudent.getRegistration())) {
                listOfStudentsAtt.add(editStudent);
            } else {
                listOfStudentsAtt.add(student);
            }
        }
        adm.setStudents(listOfStudentsAtt);
    }
}
