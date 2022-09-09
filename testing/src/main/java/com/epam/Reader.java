package com.epam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class Reader {


    public Reader() {
    }

    public String readMailFromFile(String filename) {
        try {
            Path of = Path.of("src\\main\\resources\\" + filename + ".txt");
            Path absolutePath = of.toAbsolutePath();
            System.out.println(absolutePath);
            File file = new File(String.valueOf(absolutePath));
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            fr.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
