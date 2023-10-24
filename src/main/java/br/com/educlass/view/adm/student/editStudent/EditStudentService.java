package br.com.educlass.view.adm.student.editStudent;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.service.admnistrator.AdmnistratorService;
import br.com.educlass.util.TextFile;
import br.com.educlass.util.UserUtil;
import br.com.educlass.view.adm.student.InformationsStudent;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

public class EditStudentService {
    /******/
    private static HashMap<String, String> getFolderInformations(
            String registration) {

        String year = registration.substring(1, 5);
        String semester = registration.substring(0, 1);
        String id = registration.substring(5);

        String pathUsers = "db/users/students/" + year + "/" + semester + "/" + id;
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
        String path = UserUtil.getStudentUserPathById(registration) + "photo.jpeg";
        if (file != null) {
            Path origem = file.toPath();
            Files.copy(origem,
                    (new File(path).toPath()),
                    StandardCopyOption.REPLACE_EXISTING);
        } else {
            Path profile = Path.of("src/main/resources/br/com/educlass/images/userIcon.png");
            Files.copy(profile,
                    (new File(path).toPath()),
                    StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static void editStudent(HashMap<String, String> userInformation, File imageFile) throws IOException {
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

        saveProfilePicture(imageFile, student.getRegistration());

        InformationsStudent.setStudentSelected(student);
        AdmnistratorService.editStudent(student);
    }

    public static File verifyIfUserHasProfilePicture(String registration) {
        String year = registration.substring(1, 5);
        String semester = registration.substring(0, 1);
        String id = registration.substring(5);

        String imagePath = "db/users/students/" + year + "/" + semester + "/" + id + "/photo.jpeg";
        File file = new File(imagePath);
        if (file.exists()) {
            return new File(imagePath);
        }
        return new File("src/main/resources/br/com/educlass/images/userIcon.png");
    }
}
