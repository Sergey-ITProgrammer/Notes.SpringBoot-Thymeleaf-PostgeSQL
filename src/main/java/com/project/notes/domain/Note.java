package com.project.notes.domain;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "notes")
public class Note {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Getter
    private String timeOfCreation;

    @Getter
    private Boolean isModified = false;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    @Type(type = "org.hibernate.type.TextType")
    private String text;

    public Note() {}

    public Note(String title, String text) {
        this.title = title;
        this.text = text;
        this.timeOfCreation = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setModified() {
        this.isModified = true;
        this.timeOfCreation = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " (modified)";
    }

}
