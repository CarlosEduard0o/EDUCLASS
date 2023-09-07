package br.com.educlass.util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TextFile {
    public static ArrayList<String> readTextFile(String path) {
        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            ArrayList<String> result = new ArrayList<>();

            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                result.add(linha);
            }
            return result;
        } catch (IOException e) {
            return null;
        }
    }

    public static HashMap<String, String> readTextFileMapping(String path) {
        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            HashMap<String, String> result = new HashMap<>();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineSplited = line.split(":");
                result.put(lineSplited[0], lineSplited[1]);
            }
            return result;
        } catch (IOException e) {
            return null;
        }
    }

    public static void writeTextFile(String path, String content) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path+".txt"))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}