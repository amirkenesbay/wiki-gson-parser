package com.amirkenesbay.parser.parsers;

import com.amirkenesbay.parser.model.Search;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    private final static List<Search> searches = new ArrayList<>();
    private static void parseJsonFile(){
        try (FileReader fileReader = new FileReader("wiki.json")) {
            JsonObject object = new Gson().fromJson(fileReader, JsonObject.class);
            JsonArray jsonArrayOfSearch = object.getAsJsonObject("query").get("search").getAsJsonArray();
            for (JsonElement searchElement : jsonArrayOfSearch) {
                JsonObject searchJsonObject = searchElement.getAsJsonObject();
                String snippet = searchJsonObject.get("snippet").getAsString();
                Search search = new Search(snippet);
                searches.add(search);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String showResult(String result){
        return searches.toString().replaceAll("<.*?>", "");
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
