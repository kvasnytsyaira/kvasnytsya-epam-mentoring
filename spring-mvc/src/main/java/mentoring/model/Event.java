package mentoring.model;

import java.time.LocalDate;

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

    public LocalDate getDate();

    public void setDate(LocalDate date);
}
