package br.com.educlass.service.teacher;

import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.model.subjects.Subject;
import br.com.educlass.util.JsonFile;
import br.com.educlass.util.TextFile;
import br.com.educlass.util.UserUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class TeacherService {
    private static Teacher teacher;

    public static Teacher getTeacher() {
        return teacher;
    }


    public static Teacher setTeacherInfo() {
        Teacher teacher = new Teacher();
        String pathUser = UserUtil.getPathTeacher();
        HashMap<String, String> userResult = TextFile.readTextFileMapping(pathUser+"informations.txt");

        // --Padrao--
        teacher.setName(userResult.get("nome"));
        teacher.setCpf(userResult.get("cpf"));
        teacher.setRegistration(userResult.get("matricula"));
        teacher.setEmail(userResult.get("email"));
        teacher.setAddress(userResult.get("endereço"));
        // --Fim Padrao--

//        JSONArray subjectsFile = JsonFile.readJsonFile(pathUser+"subjects.json");

//        ArrayList<ArrayList<Subject>> periods = new ArrayList<>();
//        for(Object arr: subjectsFile) {
//            JSONArray periodsFile = (JSONArray) arr;
//            ArrayList<Subject> subjects = new ArrayList<>();
//            for(Object objectOfSubjct : periodsFile) {
//                Subject subject = new Subject();
//                JSONObject jsonObject = (JSONObject) objectOfSubjct;
//                subject.setName((String) jsonObject.get("name"));
//                subject.setTime((Long) jsonObject.get("tempo"));
//                subject.setFrequency((ArrayList<String>) jsonObject.get("faltas"));
//                subject.setGrades((ArrayList<String>) jsonObject.get("notas"));
//                subject.setSituation((String) jsonObject.get("situation"));
//                subject.setTeachers((ArrayList<Teacher>) jsonObject.get("teachers"));
//                subject.setPeriod((Long) jsonObject.get("period"));
//                subjects.add(subject);
//            }
//            periods.add(subjects);
//        }

        //teacher.setPeriodsSubjects(periods);
//
//        JSONArray courseInformationsArray = JsonFile.readJsonFile(pathUser+"courseInformations.json");
//        JSONObject courseInformations = (JSONObject) courseInformationsArray.get(0);

//        teacher.setTeamId((String) courseInformations.get("team"));
//        teacher.setPeriod((Long) courseInformations.get("period"));
//        teacher.setSituation((String) courseInformations.get("situation"));
//        teacher.setCourseId("si");

        TeacherService.teacher = teacher;
        return teacher;
    }
}
