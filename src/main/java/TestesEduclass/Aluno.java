package TestesEduclass;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.model.subjects.Subject;
import br.com.educlass.util.JsonFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Aluno {

    public static void main(String[] args) {
        Student student = new Student();
        // O que o estudante precisa?
        // -> Pessoa
        /**
         *     name; cpf; registration; email; address;
         */
        String name = "Joao  Aluno";
        String cpf = "158147159-85";
        String registration = "12085218";
        String email = "emailAluno@gmail.com";
        String address =  "Rua: x; Numero: 85; Bairro: Capital; Cidade: Santa Rita do Sapucaí";

        student.setName(name);
        student.setCpf(cpf);
        student.setRegistration(registration);
        student.setEmail(email);
        student.setAddress(address);
        // -> Estudante
        /**
         *    periodsSubjects; teamId; period; Situation; courseId;
         */
        /** -------------
         *  Subjects
         *  -------------
         */

        JSONArray subjectsFile = JsonFile.readJsonFile("src/main/java/TestesEduclass/AlunoInformations/subjects.json");

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

        JSONArray courseInformationsArray = JsonFile.readJsonFile("src/main/java/TestesEduclass/AlunoInformations/courseInformations.json");
        JSONObject courseInformations = (JSONObject) courseInformationsArray.get(0);

        student.setTeamId((String) courseInformations.get("team"));
        student.setPeriod((Long) courseInformations.get("period"));
        student.setSituation((String) courseInformations.get("situation"));
        student.setCourseId("si");

        System.out.println(student);

    }
}
