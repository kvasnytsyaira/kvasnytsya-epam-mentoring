package mentoring.model;

import java.util.Date;

/**
 * Created by maksym_govorischev.
 */
public class ConcertEvent implements Event {

    long id;
    String title;
    Date date;

    /**
     * Event id. UNIQUE.
     *
     * @return Event Id
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ConcertEvent() {
    }

    public ConcertEvent(long id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                '}';
    }
}
