package com.epam.template;

import com.epam.Reader;

/**
 * The type Template.
 */
public class Template {
    private String stringTemplate;
    private String subject;
    private String body;

    Reader reader = new Reader();


    public Template(String fileName, String subject, String body) {
        this.stringTemplate = reader.readMailFromFile(fileName);
        this.subject = subject;
        this.body = body;
    }

    public String getMessage() {
        return stringTemplate;
    }

    public void setMessage(String message) {
        this.stringTemplate = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}