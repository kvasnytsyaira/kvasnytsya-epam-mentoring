import org.junit.jupiter.api.Test;
import task1.Message;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class MessageServiceTest {
    Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
    String path = "src\\main\\resources\\message.txt";

    @Test
    void task1()
            throws IOException, ClassNotFoundException {
        Message input = new Message(1L, "Joe", "Chandler", "Friends forever",4L, timestamp);

        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(input);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Message output = (Message) objectInputStream.readObject();
        objectInputStream.close();

        assertNull(output.getId());
        assertNull(output.getRandomNumber());
        assertEquals(output.getSender(), input.getSender());
        assertEquals(output.getReceiver(), input.getReceiver());
        assertEquals(output.getBody(), input.getBody());
        assertEquals(output.getTimestamp(), input.getTimestamp());
    }
}
