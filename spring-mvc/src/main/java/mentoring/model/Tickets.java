package mentoring.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tickets {
    @XmlElement(name = "ticket")
    private List<OneDayTicket> ticketList = null;

    public List<OneDayTicket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<OneDayTicket> ticketList) {
        this.ticketList = ticketList;
    }
}
