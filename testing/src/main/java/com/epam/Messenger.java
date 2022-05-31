package com.epam;

import com.epam.template.Template;
import com.epam.template.TemplateEngine;

/**
 * The type Messenger.
 */
public class Messenger {
    private MailServer mailServer;
    private TemplateEngine templateEngine;
    private Writer writer = new Writer();

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
        mailServer.send(client.getAddresses(), messageContent);
        writer.updateFile(client.getAddresses(), messageContent);
    }
}