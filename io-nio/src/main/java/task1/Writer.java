package task1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    public void updateFile(String message) throws IOException {
        var filePath = ("src\\main\\resources\\message.txt");

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
        writer.append(message);

        writer.close();
    }
}
