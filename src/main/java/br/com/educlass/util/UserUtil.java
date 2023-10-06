package br.com.educlass.util;

import java.util.ArrayList;

public class UserUtil {
    public static String getPathUser() {
        ArrayList<String> textResult = TextFile.readTextFile( "db/cache/user.txt");

        for (String s : textResult) {
            String[] stringSplited = s.split(":");
            if(stringSplited[0].equals("username")) {
                String year = stringSplited[1].substring(1,5);
                String semester = stringSplited[1].substring(0,1);
                String registration = stringSplited[1].substring(5);

                String path = "db/users/"+year+"/"+semester+"/"+registration+"/";
                return path;
            }
        }
        return null;
    }

    public static String getPathTeacher() {
        ArrayList<String> textResult = TextFile.readTextFile( "db/cache/user.txt");

        for (String s : textResult) {
            String[] stringSplited = s.split(":");
            if(stringSplited[0].equals("username")) {

                String path = "db/users/teachers/2/20000/";
                return path;
            }
        }
        return null;
    }
}
