package com.epam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reader {


    public Reader() {
    }

    public String readMailFromFile(String filename) {
        try {
            File file = new File("C:\\Users\\Iryna_Kvasnytsya\\IdeaProjects\\mentoring\\kvasnytsya-epam-mentoring\\testing\\src\\main\\resources\\" + filename + ".txt");
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