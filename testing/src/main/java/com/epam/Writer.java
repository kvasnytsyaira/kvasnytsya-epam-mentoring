package com.epam;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Writer {

    public void updateFile(String addresses, String email) throws IOException {
        Path of = Path.of("src\\main\\resources\\history.txt");
        Path absolutePath = of.toAbsolutePath();

        String newEmail = addresses + "\n" + email + "\n" + "/////////////////////////////////////////////"+ "\n";
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(absolutePath), true));
        writer.append(newEmail);

        writer.close();
    }
}
