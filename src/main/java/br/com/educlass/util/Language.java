package br.com.educlass.util;

import java.util.HashMap;

public class Language {

    /**
     * @param path EX: "src/main/resources/br/com/educlass/configuration/languages/"
     * @return HashMap dos textos
     */
    public static  HashMap<String, String> getTexts(String path) {
        String languageConfigurationsInDb = "db/configurations/language.txt";
        HashMap<String, String> languageSelected = TextFile.readTextFileMapping(languageConfigurationsInDb);
        String selected = languageSelected.get("selected");
        String pathLanguageSelected = path+selected+".txt";

        HashMap<String, String> allTexts = TextFile.readTextFileMapping(pathLanguageSelected);
        return  allTexts;
    }

}
