package mentoring.model;

import java.util.Date;

public interface Event {
    /**
     * Event id. UNIQUE.
     *
     * @return Event Id
     */
    public long getId();

    public void setId(long id);

    public String getTitle();

    public void setTitle(String title);

    public Date getDate();

    public void setDate(Date date);
}
