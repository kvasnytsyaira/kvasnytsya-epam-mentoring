package task1;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Message implements Serializable {
    private transient Long id;
    private String sender;
    private String receiver;
    private String body;
    private transient Long randomNumber;
    private Timestamp timestamp;

}
