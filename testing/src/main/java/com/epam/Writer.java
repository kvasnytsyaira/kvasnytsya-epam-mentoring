package com.epam;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    public void updateFile(String addresses, String email) throws IOException {
        var filePath = ("C:\\Users\\Iryna_Kvasnytsya\\IdeaProjects\\mentoring\\kvasnytsya-epam-mentoring\\testing\\src\\main\\resources\\"
                + "history.txt");

        String newEmail = addresses + "\n" + email + "\n" + "/////////////////////////////////////////////"+ "\n";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
        writer.append(newEmail);

        writer.close();
    }
}
