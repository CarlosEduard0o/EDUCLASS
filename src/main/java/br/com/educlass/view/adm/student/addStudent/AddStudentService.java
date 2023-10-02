package br.com.educlass.view.adm.student.addStudent;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.service.admnistrator.AdmnistratorService;
import br.com.educlass.util.Folders;
import br.com.educlass.util.TextFile;
import br.com.educlass.util.UserUtil;
import br.com.educlass.view.adm.student.StudentSituationEnum;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddStudentService {
    private static HashMap<String, String> createNecessaryFoldersAndGetInformations() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String year = timeStamp.substring(0, 4);
        int semester = Integer.parseInt(timeStamp.substring(4, 6));

        String extra = "1";
        if (semester >= 6) {
            extra = "2";
        }

        /**
         * Create folder year
         */
        String pathUsers = "db/users/students/" + year;
        File filePathUsers = new File(pathUsers);
        Folders.createFolder(filePathUsers);

        /**
         * Create folder Semester
         */
        pathUsers = "db/users/students/" + year + "/" + extra;
        filePathUsers = new File(pathUsers);
        Folders.createFolder(filePathUsers);

        int lengthOfUsers = Folders.lengthItemsInDir(filePathUsers);

        int numberOfNewUser = lengthOfUsers + 1;
        String stringNumberOfUser = String.valueOf(numberOfNewUser);

        while (stringNumberOfUser.length() < 5) {
            stringNumberOfUser = "0" + stringNumberOfUser;
        }

        /**
         * Create folder user
         */
        pathUsers = "db/users/students/" + year + "/" + extra + "/" + stringNumberOfUser;
        filePathUsers = new File(pathUsers);
        Folders.createFolder(filePathUsers);

        String registration = extra + year + stringNumberOfUser;

        HashMap<String, String> response = new HashMap<>();
        response.put("registration", registration);
        response.put("path", pathUsers);

        return response;
    }

    private static void createFiles(
            String path,
            HashMap<String, String> userInformation) {

        String informationsText = "nome:" + userInformation.get("name") + "\n" +
                "curso:SI" + "\n" +
                "cpf:" + userInformation.get("cpf") + "\n" +
                "endereço:" + userInformation.get("address") + "\n" +
                "email:" + userInformation.get("email") + "\n" +
                "matricula:" + userInformation.get("registration");
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
        if(file != null) {
            Path path = Path.of(UserUtil.getStudentUserPathById(registration));
            Files.copy(file.toPath(), path, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static void addStudent(HashMap<String, String> userInformation, File profilePicture) throws IOException {
        HashMap<String, String> informations = createNecessaryFoldersAndGetInformations();
        userInformation.put("registration", informations.get("registration"));
        createFiles(informations.get("path"), userInformation);
        Student student = new Student();

        student.setName(userInformation.get("name"));
        student.setCpf(userInformation.get("cpf"));
        student.setRegistration(userInformation.get("registration"));
        student.setEmail(userInformation.get("email"));
        student.setAddress(userInformation.get("address"));
        student.setSituation(String.valueOf(StudentSituationEnum.matriculado));

        saveProfilePicture(profilePicture, student.getRegistration());

        AdmnistratorService.setNewStudent(student);
    }

}
