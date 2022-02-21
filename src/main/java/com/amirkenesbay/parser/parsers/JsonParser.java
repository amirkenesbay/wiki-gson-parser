package com.amirkenesbay.parser.parsers;

import com.amirkenesbay.parser.model.Search;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    private final static List<Search> searches = new ArrayList<>();
    private final static String FILE_NAME = "wiki.json";
    private final static String OBJECT_QUERY = "query";
    private final static String ARRAY_SEARCH = "search";
    private final static String OBJECT_SNIPPET = "snippet";
    private final static String SPAN_REGEX = "^\\[|\\]$|\\,|<.*?>";
    private final static String EMPTY_STRING = "";
    private static void parseJsonFile(){
        try (FileReader fileReader = new FileReader(FILE_NAME)) {
            JsonObject object = new Gson().fromJson(fileReader, JsonObject.class);
            JsonArray jsonArrayOfSearch = object.getAsJsonObject(OBJECT_QUERY).get(ARRAY_SEARCH).getAsJsonArray();
            for (JsonElement searchElement : jsonArrayOfSearch) {
                JsonObject searchJsonObject = searchElement.getAsJsonObject();
                String snippet = searchJsonObject.get(OBJECT_SNIPPET).getAsString();
                Search search = new Search(snippet);
                searches.add(search);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String showResult(String result){
        return searches.toString().replaceAll(SPAN_REGEX, EMPTY_STRING);
//        for (Search searchResult : searches) {
//            result = searchResult.toString().replaceAll("<.*?>", "");
//            System.out.println(result);
//        }
    }

    public static String parseResponse(String responseInfo){
        FileParser.createFile();
        FileParser.writeInfoToFile(responseInfo);
        parseJsonFile();
        return showResult(responseInfo);
    }
}
