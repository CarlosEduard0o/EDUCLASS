package br.com.educlass.view.adm.teacher.editTeacher;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.service.admnistrator.AdmnistratorService;
import br.com.educlass.util.TextFile;
import br.com.educlass.util.UserUtil;
import br.com.educlass.view.adm.student.InformationsStudent;
import br.com.educlass.view.adm.teacher.InformationsTeacher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

public class EditTeacherService {
    /******/
    private static HashMap<String, String> getFolderInformations(
            String registration) {
            String id = registration.substring(1);

        String pathUsers = "db/users/teachers/" + id +"/";
        HashMap<String, String> response = new HashMap<>();
        response.put("path", pathUsers);

        return response;
    }

    /******/
    private static void createFiles(
            String path,
            HashMap<String, String> userInformation) {

        String informationsText = "nome:" + userInformation.get("name") + "\n" +
                "curso:SI" + "\n" +
                "cpf:" + userInformation.get("cpf") + "\n" +
                "endereço:" + userInformation.get("address") + "\n" +
                "email:" + userInformation.get("email") + "\n" +
                "matricula:" + userInformation.get("registration") + "\n" +
                "situation:" + userInformation.get("situation");
        TextFile.writeTextFile(
                path + "/informations",
                informationsText);

        String loginInformations = "username:" + userInformation.get("registration") + "\n" +
                "password:" + userInformation.get("password");

        TextFile.writeTextFile(
                path + "/login",
                loginInformations);
    }

    /*****/
    private static void saveProfilePicture(File file, String registration) throws IOException {
        if (file != null) {
            String path = UserUtil.getTeacherUserPathById(registration) + "photo.jpeg";
            Path origem = file.toPath();
            Files.copy(origem,
                    (new File(path).toPath()),
                    StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static void editTeacher(HashMap<String, String> userInformation, File imageFile) throws IOException {
        String registration = userInformation.get("registration");
        HashMap<String, String> informations = getFolderInformations(registration);
        createFiles(informations.get("path"), userInformation);
        Teacher teacher = new Teacher();

        teacher.setName(userInformation.get("name"));
        teacher.setCpf(userInformation.get("cpf"));
        teacher.setRegistration(registration);
        teacher.setEmail(userInformation.get("email"));
        teacher.setAddress(userInformation.get("address"));
        teacher.setSituation(userInformation.get("situation"));

        saveProfilePicture(imageFile, teacher.getRegistration());

        InformationsTeacher.setTeacherSelected(teacher);
        AdmnistratorService.editTeacher(teacher);
    }

    public static File verifyIfUserHasProfilePicture(String registration) {
        String id = registration.substring(1);

        String imagePath = "db/users/teachers/" + id + "/photo.jpeg";
        File file = new File(imagePath);
        if (file.exists()) {
            return new File(imagePath);
        }
        return new File("src/main/resources/br/com/educlass/images/userIcon.png");
    }
}
