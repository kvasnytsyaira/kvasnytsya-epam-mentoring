package com.epam.template;

import com.epam.Client;


/**
 * The type Template engine.
 */
public class TemplateEngine {
    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) throws Exception {
        System.out.println("generate message in templateEngine");
        return processMessage(template).getMessage();
    }

    private Template processMessage(Template template) throws Exception {
        String message = template.getMessage();
        message = message.replace("#{subject}", template.getSubject());
        message = message.replace("#{body}", template.getBody());
        template.setMessage(message);



        if (template.getSubject().isEmpty() ||
                template.getBody().isEmpty()) {
            throw new Exception("Please provide all placeholders");
        }
        return template;
    }
}
