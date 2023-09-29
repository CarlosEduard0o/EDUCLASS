package br.com.educlass.util;

import java.io.File;

public class Folders {
    /**
     *
     * @param file File file = new File(String path);
     */
    public static void createFolder(File file) {
        boolean existPath = hasFolder(file);
        if (!existPath) {
            file.mkdirs();
        }
    }

    /**
     *
     * @param file File file = new File(path);
     * @return true if exist and false if not exist
     */
    public static boolean hasFolder(File file) {
        if (file.exists()) {
            return true;
        }
        return false;
    }

    /**
     * String path = "db/users/2023/1/10000";
     * 
     * @param file = new File(path);
     * @return number of items in the path
     */
    public static int lengthItemsInDir(File file) {
        return file.list().length;
    }

    public static String[] getItemsInDir(File file) {
        return file.list();
    }

}
