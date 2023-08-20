package com.example.eldercare;

import org.litepal.crud.LitePalSupport;

// The Note class symbolizes the Notes created by the users.
// With the help of LitePalSupport, it can automatically be created in the database.
public class Note extends LitePalSupport {
    // note title
    private String title;
    // note detail/content
    private String detail;
    // note owner
    private String author_name;
    // note last edited time
    private String timestamp;

    public Note() {}

    public Note(String title, String detail, String author_name, String timestamp) {
        this.title = title;
        this.detail = detail;
        this.author_name = author_name;
        this.timestamp = timestamp;
    }

    // Getter and Setter Methods
    public String getNoteTitle() {
        return title;
    }

    public void setNoteTitle(String title) {
        this.title = title;
    }

    public String getNoteDetail() {
        return detail;
    }

    public void setNoteDetail(String detail) {
        this.detail = detail;
    }

    public String getNoteAuthor() {
        return author_name;
    }

    public void setNoteAuthor(String author_name) {
        this.author_name = author_name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
