import org.junit.jupiter.api.Test;
import task1.Message;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class MessageServiceTest {
    Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
    String path = "C:\\Users\\Iryna_Kvasnytsya\\IdeaProjects\\mentoring\\kvasnytsya-epam-mentoring\\io-nio\\src\\main\\resources\\message.txt";

    @Test
    public void task1()
            throws IOException, ClassNotFoundException {
        Message actual = new Message(1L, "Joe", "Chandler", "Friends forever", timestamp);

        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(actual);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Message expected = (Message) objectInputStream.readObject();
        objectInputStream.close();

        assertEquals(Message.getId(), Message.getId());
        assertEquals(expected.getSender(), actual.getSender());
        assertEquals(expected.getReceiver(), actual.getReceiver());
        assertEquals(expected.getBody(), actual.getBody());
        assertEquals(expected.getTimestamp(), actual.getTimestamp());
    }
}
