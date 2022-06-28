package com.app;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "application_files")
public class FileToShare {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String file;

    @Column(name = "local_date", columnDefinition = "DATE")
    private LocalDate localDate;

    public FileToShare() {
    }

    public FileToShare(String name, String file) {
        this.name = name;
        this.file = file;
        this.localDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    @Override
    public String toString() {
        return "FileToShare{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", file='" + file + '\'' +
                ", localDate=" + localDate +
                '}';
    }
}
