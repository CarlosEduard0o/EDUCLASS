package br.com.educlass.service.admnistrator.AdmnistratorSubjectService;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.model.subjects.Subject;
import br.com.educlass.service.student.StudentService;
import br.com.educlass.service.teacher.TeacherService;
import br.com.educlass.util.Folders;
import br.com.educlass.util.JsonFile;
import br.com.educlass.util.UserUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class AdmnistratorSubjectService {

    private static ArrayList<Student> getStudentsById(JSONArray studentsId) {
        ArrayList<Student> students = new ArrayList<>();

        for (Object studentId: studentsId) {
            String pathUser = UserUtil.getStudentUserPathById(String.valueOf(studentId));
            students.add(StudentService.setStudentInfoForList(pathUser));
        }

        return students;
    }

    public static ArrayList<Subject> getAllSubjects() {
        String path = "db/subjects";
        String[] disciplinesIds = Folders.getItemsInDir( new File(path));

        ArrayList<Subject> subjects = new ArrayList<>();

        for (String disciplineId: disciplinesIds) {
            Subject subject = new Subject();
            String pathWithId = path+"/"+disciplineId+"/informations.json";
            JSONObject informationsObject = (JSONObject) JsonFile.readJsonFile(pathWithId).get(0);

            subject.setId(disciplineId);

            // Students
            JSONArray studentsJson = (JSONArray) informationsObject.get("students");
            ArrayList<Student> students = getStudentsById(studentsJson);
            subject.setEnrolledStudents(students);

            // Teacher
            String teacherId = (String) informationsObject.get("teacher");
            String pathUser = UserUtil.getTeacherUserPathById(teacherId);
            Teacher teacher = TeacherService.setTeacherInfoForList(pathUser);
            subject.setUniqueTeacher(teacher);

            String name =  (String) informationsObject.get("name");
            subject.setName(name);
            Long quantidadeDeAulas = (Long) informationsObject.get("quantidade_de_aulas");
            subject.setTime(quantidadeDeAulas);

            subjects.add(subject);
        }

        return subjects;
    }
}