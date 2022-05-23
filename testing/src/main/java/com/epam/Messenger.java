package com.epam;

import com.epam.template.Template;
import com.epam.template.TemplateEngine;

/**
 * The type Messenger.
 */
public class Messenger {
    private MailServer mailServer;
    private TemplateEngine templateEngine;

    /**
     * Instantiates a new Messenger.
     *
     * @param mailServer     the mail server
     * @param templateEngine the template engine
     */
    public Messenger(MailServer mailServer,
                     TemplateEngine templateEngine) {
        this.mailServer = mailServer;
        this.templateEngine = templateEngine;
    }

    /**
     * Send message.
     *
     * @param client   the client
     * @param template the template
     */
    public void sendMessage(Client client, Template template) throws Exception {
        String messageContent = templateEngine.generateMessage(template, client);
        System.out.println("message generated from messenger");
        mailServer.send(client.getAddresses(), messageContent);
        System.out.println("message sent from messenger");
    }
}