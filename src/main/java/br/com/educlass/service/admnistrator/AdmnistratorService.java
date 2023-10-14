package br.com.educlass.service.admnistrator;

import br.com.educlass.model.course.Course;
import br.com.educlass.model.institution.Institution;
import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.model.subjects.Subject;
import br.com.educlass.service.admnistrator.AdmnistratorStudentService.AdmnistratorStudentService;
import br.com.educlass.service.admnistrator.AdmnistratorSubjectService.AdmnistratorSubjectService;
import br.com.educlass.service.admnistrator.AdmnistratorTeacherService.AdmnistratorTeacherService;
import br.com.educlass.service.student.StudentService;
import br.com.educlass.service.teacher.TeacherService;
import br.com.educlass.util.Folders;
import br.com.educlass.util.JsonFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class AdmnistratorService {

    private static Institution administrator;

    public static Institution getAdmnistrator() {
        return administrator;
    }

    public static void setAdmnistrator(Institution institution) {
        administrator = institution;
    }

    public static ArrayList<Student> getListOfStudents() {
        return administrator.getStudents();
    }

    public static ArrayList<Teacher> getListOfTeachers() {
        return administrator.getTeachers();
    }

    private static ArrayList<Student> setStudentsInformations() {
        String path = "db/users/students";
        File file = new File(path);
        String[] yearFolders = Folders.getItemsInDir(file);

        ArrayList<Student> students = new ArrayList<>();
        for (String year : yearFolders) {
            path = "db/users/students/" + year;
            file = new File(path);
            String[] semesterFolders = Folders.getItemsInDir(file);
            for (String semester : semesterFolders) {
                path = "db/users/students/" + year + "/" + semester;
                file = new File(path);
                String[] registersFolders = Folders.getItemsInDir(file);
                for (String registration : registersFolders) {
                    String pathStudent = "db/users/students/" + year + "/" + semester + "/" + registration + "/";
                    students.add(StudentService.setStudentInfoForList(pathStudent));
                }
            }
        }

        return students;
    }

    private static ArrayList<Subject> setSubjectsInformations(ArrayList jsonArray) {
        ArrayList<Subject> subjects = new ArrayList<>();
        for (Object objectOfSubjct : jsonArray) {
            Subject subject = new Subject();
            JSONObject jsonObject = (JSONObject) objectOfSubjct;
            subject.setName((String) jsonObject.get("name"));
            subject.setTime((Long) jsonObject.get("tempo"));
            subject.setFrequency((Long) jsonObject.get("faltas"));
            subject.setGrades((ArrayList<String>) jsonObject.get("notas"));
            subject.setTeachers((ArrayList<Teacher>) jsonObject.get("teachers"));
            subjects.add(subject);
        }
        return subjects;
    }

    private static ArrayList<Teacher> setTeatchersInformations() {
        String path = "db/users/teachers";
        File file = new File(path);
        String[] registrationFolders = Folders.getItemsInDir(file);

        ArrayList<Teacher> teachers = new ArrayList<>();
        for (String registration : registrationFolders) {
            String pathTeacher = "db/users/teachers/" + registration + "/";
            teachers.add(TeacherService.setTeacherInfoForList(pathTeacher));
        }
        return teachers;
    }

    public static Institution setAdmnistratorInfo() {
        JSONObject schoolInformations = (JSONObject) JsonFile.readJsonFile("db/users/_school/informations.json").get(0);

        Institution institution = new Institution();
        institution.setName((String) schoolInformations.get("name"));
        institution.setCnpj((String) schoolInformations.get("cnpj"));
        institution.setEmail((String) schoolInformations.get("email"));

        String path = "db/course";
        File file = new File(path);

        ArrayList<Course> courses = new ArrayList<>();
        for (String item : file.list()) {
            Course course = new Course();
            String filePath = path + '/' + item + '/' + "informations.json";

            JSONObject courseInformations = (JSONObject) JsonFile.readJsonFile(filePath).get(0);
            course.setId((String) courseInformations.get("id"));
            course.setNome((String) courseInformations.get("nome"));

            filePath = path + '/' + item + '/' + "subjects.json";
            JSONArray subjectsFile = JsonFile.readJsonFile(filePath);
            course.setSubjects(setSubjectsInformations(subjectsFile));

            courses.add(course);
        }

        institution.setTeachers(setTeatchersInformations());
        institution.setStudents(setStudentsInformations());

        AdmnistratorService.administrator = institution;
        AdmnistratorService.administrator.setSubjects(AdmnistratorSubjectService.getAllSubjects());
        return administrator;
    }

    /** Student view -- add **/
    public static void setNewStudent(Student student) {
        AdmnistratorStudentService.setNewStudent(student, administrator);
    }

    public static Student getNewStudent() {
        return AdmnistratorStudentService.getNewStudent();
    }

    /** Student view -- edit **/
    public static void setEditStudent(Student student) {
        AdmnistratorStudentService.editStudent(student, administrator);
    }

    public static Student getEditStudent() {
        return AdmnistratorStudentService.getEditStudent();
    }

    public static void editStudent(Student student) {
        AdmnistratorStudentService.editStudent(student, administrator);
    }

    /** Teacher view -- add **/
    public static void setNewTeacher(Teacher teacher) {
        AdmnistratorTeacherService.setNewTeacher(teacher, administrator);
    }

    public static Teacher getNewTeacher() {
        return AdmnistratorTeacherService.getNewTeacher();
    }

    /** Teacher view -- edit **/
    public static void setEditTeacher(Teacher teacher) {
        AdmnistratorTeacherService.editTeacher(teacher, administrator);
    }

    public static Teacher getEditTeacher() {
        return AdmnistratorTeacherService.getEditTeacher();
    }

    public static void editTeacher(Teacher teacher) {
        AdmnistratorTeacherService.editTeacher(teacher, administrator);
    }

}
