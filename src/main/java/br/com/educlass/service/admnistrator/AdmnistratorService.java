package br.com.educlass.service.admnistrator;

import br.com.educlass.model.course.Course;
import br.com.educlass.model.institution.Institution;
import br.com.educlass.util.JsonFile;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;


public class AdmnistratorService {

    private static Institution administrator;

    public static Institution getStudent() {
        return administrator;
    }

    public static Institution setAdmnistratorInfo() {
        JSONObject schoolInformations = (JSONObject) JsonFile.readJsonFile("db/users/school/informations.json").get(0);

        Institution institution = new Institution();
        institution.setName((String) schoolInformations.get("name"));
        institution.setCnpj((String) schoolInformations.get("cnpj"));
        institution.setEmail((String) schoolInformations.get("email"));

        String path = "db/course";
        File file = new File(path);

        ArrayList<Course> courses = new ArrayList<>();
        for(String item: file.list()) {
            Course course = new Course();
            String filePath = path+'/'+item+'/'+"informations.json";

            JSONObject courseInformations = (JSONObject) JsonFile.readJsonFile(filePath).get(0);
            course.setId((String) courseInformations.get("id"));
            course.setNome((String) courseInformations.get("nome"));

            filePath = path+'/'+item+'/'+"subjects.json";

        }
        System.out.println(file.list()[0]);

        AdmnistratorService.administrator = institution;
        return administrator;
    }
}
