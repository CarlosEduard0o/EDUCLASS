package br.com.educlass.view.adm.student.editStudent;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.service.admnistrator.AdmnistratorService;
import br.com.educlass.util.TextFile;
import java.util.HashMap;

public class EditStudentService {

    private static HashMap<String,String> getFolderInformations(
            String registration) {

        String year = registration.substring(1,5);
        String semester = registration.substring(0,1);
        String id = registration.substring(5);

        String pathUsers = "db/users/students/"+year+"/"+semester+"/"+id;
        HashMap<String,String> response = new HashMap<>();
        response.put("path", pathUsers);

        return response;
    }


    private static void createFiles(
            String path,
            HashMap<String,String> userInformation) {

        String informationsText = "nome:"+userInformation.get("name")+"\n"+
                "curso:SI"+"\n" +
                "cpf:"+userInformation.get("cpf")+"\n" +
                "endereço:"+userInformation.get("address")+"\n"+
                "email:"+userInformation.get("email")+"\n"+
                "matricula:"+userInformation.get("registration")+"\n"+
                "situation:"+userInformation.get("situation");
        TextFile.writeTextFile(
                path+"/informations",
                informationsText);


        String loginInformations = "username:"+userInformation.get("registration")+"\n"+
                "password:"+userInformation.get("password");

        System.out.println(path);

        TextFile.writeTextFile(
                path+"/login",
                loginInformations);
    }

    public static void editStudent(HashMap<String, String> userInformation){
        String registration = userInformation.get("registration");
        HashMap<String, String> informations = getFolderInformations(registration);
        createFiles(informations.get("path"), userInformation);
        Student student = new Student();

        student.setName(userInformation.get("name"));
        student.setCpf(userInformation.get("cpf"));
        student.setRegistration(registration);
        student.setEmail(userInformation.get("email"));
        student.setAddress(userInformation.get("address"));
        student.setSituation(userInformation.get("situation"));

        AdmnistratorService.editStudent(student);
    }
}
