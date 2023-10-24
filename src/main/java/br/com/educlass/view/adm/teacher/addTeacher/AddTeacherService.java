package br.com.educlass.view.adm.teacher.addTeacher;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.service.admnistrator.AdmnistratorService;
import br.com.educlass.util.Folders;
import br.com.educlass.util.TextFile;
import br.com.educlass.util.UserUtil;
import br.com.educlass.view.adm.student.StudentSituationEnum;
import br.com.educlass.view.adm.teacher.TeacherSituationEnum;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddTeacherService {
    private static HashMap<String, String> createNecessaryFoldersAndGetInformations() {
        String pathUsers = "db/users/teachers/";
        File filePathUsers = new File(pathUsers);
        Folders.createFolder(filePathUsers);

        int lengthOfUsers = Folders.lengthItemsInDir(filePathUsers);

        int numberOfNewUser = lengthOfUsers + 1;
        String stringNumberOfUser = String.valueOf(numberOfNewUser);

        while (stringNumberOfUser.length() < 4) {
            stringNumberOfUser = "0" + stringNumberOfUser;
        }

        /**
         * Create folder user
         */
        pathUsers = "db/users/teachers/" + stringNumberOfUser;
        filePathUsers = new File(pathUsers);
        Folders.createFolder(filePathUsers);

        String registration = "T"+ stringNumberOfUser;

        HashMap<String, String> response = new HashMap<>();
        response.put("registration", registration);
        response.put("path", pathUsers);

        return response;
    }

    private static void createFiles(
            String path,
            HashMap<String, String> userInformation) {

        String informationsText = "nome:" + userInformation.get("name") + "\n" +
                "cpf:" + userInformation.get("cpf") + "\n" +
                "endereço:" + userInformation.get("address") + "\n" +
                "email:" + userInformation.get("email") + "\n" +
                "matricula:" + userInformation.get("registration") + "\n" +
                "situation:" + TeacherSituationEnum.ativo;
        TextFile.writeTextFile(
                path + "/informations",
                informationsText);

        String loginInformations = "username:" + userInformation.get("registration") + "\n" +
                "password:" + userInformation.get("password");

        TextFile.writeTextFile(
                path + "/login",
                loginInformations);
    }

    private static void saveProfilePicture(File file, String registration) throws IOException {
        String path = UserUtil.getTeacherUserPathById(registration)+"photo.jpeg";
        if(file != null) {
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

    public static void addTeacher(HashMap<String, String> userInformation, File profilePicture) throws IOException {
        HashMap<String, String> informations = createNecessaryFoldersAndGetInformations();
        userInformation.put("registration", informations.get("registration"));
        createFiles(informations.get("path"), userInformation);
        Teacher teacher = new Teacher();

        teacher.setName(userInformation.get("name"));
        teacher.setCpf(userInformation.get("cpf"));
        teacher.setRegistration(userInformation.get("registration"));
        teacher.setEmail(userInformation.get("email"));
        teacher.setAddress(userInformation.get("address"));
        teacher.setSituation(String.valueOf(TeacherSituationEnum.ativo));

        saveProfilePicture(profilePicture, teacher.getRegistration());

        AdmnistratorService.setNewTeacher(teacher);
    }

}
