package br.com.educlass.util;

import java.util.ArrayList;
import java.util.HashMap;

public class UserUtil {
    public static String getPathUser() {
        ArrayList<String> textResult = TextFile.readTextFile("db/cache/user.txt");

        for (String s : textResult) {
            String[] stringSplited = s.split(":");
            if (stringSplited[0].equals("username")) {
                String year = stringSplited[1].substring(1, 5);
                String semester = stringSplited[1].substring(0, 1);
                String registration = stringSplited[1].substring(5);

                String path = "db/users/students/" + year + "/" + semester + "/" + registration + "/";
                return path;
            }
        }
        return null;
    }

    /**
     *
     * @param registration
     * @return db/users/students/"+year+"/"+semester+"/"+id/;
     */
    public static String getStudentUserPathById(String registration) {
        String year = registration.substring(1,5);
        String semester = registration.substring(0,1);
        String id = registration.substring(5);

        String pathUser = "db/users/students/"+year+"/"+semester+"/"+id+"/";
        return  pathUser;
    }
}
