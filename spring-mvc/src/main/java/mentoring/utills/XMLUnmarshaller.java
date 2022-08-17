package mentoring.utills;

import mentoring.model.OneDayTicket;
import mentoring.model.Ticket;
import mentoring.model.Tickets;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class XMLUnmarshaller {

    private static final String file = "C:\\Users\\Iryna_Kvasnytsya\\IdeaProjects\\mentoring\\kvasnytsya-epam-mentoring\\spring-mvc\\src\\main\\java\\mentoring\\storingData\\data.xml";
    private Unmarshaller unmarshaller;

    private OneDayTicket ticket;


    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public List<Ticket> loadTickets() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Tickets.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Tickets load = (Tickets) jaxbUnmarshaller.unmarshal(new File(file));
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.addAll(load.getTicketList());
        return tickets;
    }

}
