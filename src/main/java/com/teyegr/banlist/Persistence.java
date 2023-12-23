package com.teyegr.banlist;

import java.io.*;

public class Persistence {
    private static final File ressourceFolder = new File("src\\main\\resources");
    private static final File defaultFile = new File(ressourceFolder, "banlist.json");

    public static String readFile(File file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }
            reader.close();
            return builder.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readDefaultFile() {
        return readFile(defaultFile);
    }
    public static void writeFile(String data, File file)  {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeDefaultFile(String data) {
        writeFile(data, Persistence.defaultFile);
    }
}
