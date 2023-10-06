package br.com.educlass.service.student;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.model.subjects.Subject;
import br.com.educlass.util.JsonFile;
import br.com.educlass.util.TextFile;
import br.com.educlass.util.UserUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentService {
    private static Student student;

    public static Student getStudent() {
        return student;
    }


    public static Student setStudentInfo() {
        Student student = new Student();
        String pathUser = UserUtil.getPathUser();
        HashMap<String, String> userResult = TextFile.readTextFileMapping(pathUser+"informations.txt");

        // --Padrao--
        student.setName(userResult.get("nome"));
        student.setCpf(userResult.get("cpf"));
        student.setRegistration(userResult.get("matricula"));
        student.setEmail(userResult.get("email"));
        student.setAddress(userResult.get("endereço"));
        // --Fim Padrao--


        File fileExist = new File(pathUser+"subjects.json");
        if(fileExist.exists()) {
            JSONArray subjectsFile = JsonFile.readJsonFile(pathUser+"subjects.json");

            ArrayList<ArrayList<Subject>> periods = new ArrayList<>();

            for(Object arr: subjectsFile) {
                JSONArray periodsFile = (JSONArray) arr;
                ArrayList<Subject> subjects = new ArrayList<>();
                for(Object objectOfSubjct : periodsFile) {
                    Subject subject = new Subject();
                    JSONObject jsonObject = (JSONObject) objectOfSubjct;
                    subject.setName((String) jsonObject.get("name"));
                    subject.setTime((Long) jsonObject.get("tempo"));
                    subject.setFrequency((ArrayList<String>) jsonObject.get("faltas"));
                    subject.setGrades((ArrayList<String>) jsonObject.get("notas"));
                    subject.setSituation((String) jsonObject.get("situation"));
                    subject.setTeachers((ArrayList<Teacher>) jsonObject.get("teachers"));
                    subject.setPeriod((Long) jsonObject.get("period"));
                    subjects.add(subject);
                }
                periods.add(subjects);
            }

            student.setPeriodsSubjects(periods);
        }


//        JSONArray courseInformationsArray = JsonFile.readJsonFile(pathUser+"courseInformations.json");
//        JSONObject courseInformations = (JSONObject) courseInformationsArray.get(0);
//
//        student.setTeamId((String) courseInformations.get("team"));
//        student.setPeriod((Long) courseInformations.get("period"));
//        student.setSituation((String) courseInformations.get("situation"));
        student.setCourseId("si");

        StudentService.student = student;
        return student;
    }

    public static Student setStudentInfoForList(String pathUser) {
        Student student = new Student();
        HashMap<String, String> userResult = TextFile.readTextFileMapping(pathUser+"informations.txt");

        // --Padrao--
        student.setName(userResult.get("nome"));
        student.setCpf(userResult.get("cpf"));
        student.setRegistration(userResult.get("matricula"));
        student.setEmail(userResult.get("email"));
        student.setAddress(userResult.get("endereço"));
        student.setSituation(userResult.get("situation"));
        // --Fim Padrao--

        JSONArray subjectsFile = JsonFile.readJsonFile(pathUser+"subjects.json");

        ArrayList<ArrayList<Subject>> periods = new ArrayList<>();
        if(subjectsFile != null) {
            for(Object arr: subjectsFile) {
                JSONArray periodsFile = (JSONArray) arr;
                ArrayList<Subject> subjects = new ArrayList<>();
                for(Object objectOfSubjct : periodsFile) {
                    Subject subject = new Subject();
                    JSONObject jsonObject = (JSONObject) objectOfSubjct;
                    subject.setName((String) jsonObject.get("name"));
                    subject.setTime((Long) jsonObject.get("tempo"));
                    subject.setFrequency((ArrayList<String>) jsonObject.get("faltas"));
                    subject.setGrades((ArrayList<String>) jsonObject.get("notas"));
                    subject.setSituation((String) jsonObject.get("situation"));
                    subject.setTeachers((ArrayList<Teacher>) jsonObject.get("teachers"));
                    subject.setPeriod((Long) jsonObject.get("period"));
                    subjects.add(subject);
                }
                periods.add(subjects);
            }
        }


        student.setPeriodsSubjects(periods);

//        JSONArray courseInformationsArray = JsonFile.readJsonFile(pathUser+"courseInformations.json");
//        if(courseInformationsArray != null) {
//            JSONObject courseInformations = (JSONObject) courseInformationsArray.get(0);
//            student.setTeamId((String) courseInformations.get("team"));
//            student.setPeriod((Long) courseInformations.get("period"));
//            student.setSituation((String) courseInformations.get("situation"));
//        }
        student.setCourseId("si");

        return student;
    }
}
