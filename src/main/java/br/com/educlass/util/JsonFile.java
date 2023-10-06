package br.com.educlass.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonFile {

    public static JSONArray readJsonFile(String path) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(path))
        {
            Object obj = jsonParser.parse(reader);
            return (JSONArray) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param jsonObjects list of jsonObject to write jsonFile
     * @param path path of file ex.("db/user/")
     * @param nameFile name of file ex.(informations)
     */
    public static void writeJsonFile(ArrayList<JSONObject> jsonObjects, String path, String nameFile) {
        JSONArray jsonArray = new JSONArray();
        for (JSONObject jsonObject : jsonObjects) {
            jsonArray.add(jsonObject);
        }
        if(jsonArray.size() > 0) {
            try (FileWriter file = new FileWriter(path+nameFile+".json")) {
                file.write(jsonArray.toJSONString());
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("asd", "asd");
        jsonObjects.add(jsonObject);
        writeJsonFile(jsonObjects, "db/t/", "teste");
    }
}


/**
 * Write:
 *
 * import java.io.FileWriter;
 * import java.io.IOException;
 * import org.json.simple.JSONArray;
 * import org.json.simple.JSONObject;
 *
 * public class WriteJSONExample
 * {
 *     @SuppressWarnings("unchecked")
 *     public static void main( String[] args )
 *     {
 *         //First Employee
 *         JSONObject employeeDetails = new JSONObject();
 *         employeeDetails.put("firstName", "Lokesh");
 *         employeeDetails.put("lastName", "Gupta");
 *         employeeDetails.put("website", "howtodoinjava.com");
 *
 *         JSONObject employeeObject = new JSONObject();
 *         employeeObject.put("employee", employeeDetails);
 *
 *         //Second Employee
 *         JSONObject employeeDetails2 = new JSONObject();
 *         employeeDetails2.put("firstName", "Brian");
 *         employeeDetails2.put("lastName", "Schultz");
 *         employeeDetails2.put("website", "example.com");
 *
 *         JSONObject employeeObject2 = new JSONObject();
 *         employeeObject2.put("employee", employeeDetails2);
 *
 *         //Add employees to list
 *         JSONArray employeeList = new JSONArray();
 *         employeeList.add(employeeObject);
 *         employeeList.add(employeeObject2);
 *
 *         //Write JSON file
 *         try (FileWriter file = new FileWriter("employees.json")) {
 *             //We can write any JSONArray or JSONObject instance to the file
 *             file.write(employeeList.toJSONString());
 *             file.flush();
 *
 *         } catch (IOException e) {
 *             e.printStackTrace();
 *         }
 *     }
 * }
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * Read:
 * package com.howtodoinjava.demo.jsonsimple;
 *
 * import java.io.FileNotFoundException;
 * import java.io.FileReader;
 * import java.io.IOException;
 *
 * import org.json.simple.JSONArray;
 * import org.json.simple.JSONObject;
 * import org.json.simple.parser.JSONParser;
 * import org.json.simple.parser.ParseException;
 *
 * public class ReadJSONExample
 * {
 *     @SuppressWarnings("unchecked")
 *     public static void main(String[] args)
 *     {
 *         //JSON parser object to parse read file
 *         JSONParser jsonParser = new JSONParser();
 *
 *         try (FileReader reader = new FileReader("employees.json"))
 *         {
 *             //Read JSON file
 *             Object obj = jsonParser.parse(reader);
 *
 *             JSONArray employeeList = (JSONArray) obj;
 *             System.out.println(employeeList);
 *
 *             //Iterate over employee array
 *             employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
 *
 *         } catch (FileNotFoundException e) {
 *             e.printStackTrace();
 *         } catch (IOException e) {
 *             e.printStackTrace();
 *         } catch (ParseException e) {
 *             e.printStackTrace();
 *         }
 *     }
 *
 *     private static void parseEmployeeObject(JSONObject employee)
 *     {
 *         //Get employee object within list
 *         JSONObject employeeObject = (JSONObject) employee.get("employee");
 *
 *         //Get employee first name
 *         String firstName = (String) employeeObject.get("firstName");
 *         System.out.println(firstName);
 *
 *         //Get employee last name
 *         String lastName = (String) employeeObject.get("lastName");
 *         System.out.println(lastName);
 *
 *         //Get employee website name
 *         String website = (String) employeeObject.get("website");
 *         System.out.println(website);
 *     }
 * }
 */
