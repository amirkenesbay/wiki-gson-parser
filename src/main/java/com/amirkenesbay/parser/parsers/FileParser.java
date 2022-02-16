package com.amirkenesbay.parser.parsers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileParser {
    private final static String FILE_SEPARATOR = File.separator;
    private final static String PATH_NAME = "D:" + FILE_SEPARATOR + "Codes" + FILE_SEPARATOR + "JavaProjects" + FILE_SEPARATOR + "JsonExample";
    private final static String FILE_NAME = "wiki.json";

    public static void createFile() {
        File filePath = new File(PATH_NAME);
        filePath.mkdir();
        File file = new File(filePath + FILE_SEPARATOR + FILE_NAME);
        try {
            if(file.exists()){
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeInfoToFile(String textInfo) {
        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            writer.write(textInfo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String readInfoFromFile(String responseInfo) throws IOException {
        return new String(Files.readAllBytes(Paths.get(FILE_NAME)));
    }
}
