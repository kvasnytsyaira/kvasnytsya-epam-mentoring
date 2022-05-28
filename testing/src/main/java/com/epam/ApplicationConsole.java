package com.epam;


import com.epam.template.Template;
import com.epam.template.TemplateEngine;

import java.util.Scanner;

import static java.lang.System.exit;

public class ApplicationConsole {
    public static void printMenu(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
        System.out.print("Choose your template option : ");
    }

    private static String[] options = {"1 - Best regards!",
            "2 - Bye bye!",
            "3 - See you soon!",
            "4 - Exit application."
    };

    public static void enterEmailDetails(String[] options) {

    }

    public static void main(String[] args) {
        int option = 1;
        int continueSendingEmails = 1;
        while (option != 5) {
            Scanner scanner = new Scanner(System.in);
            TemplateEngine templateEngine = new TemplateEngine();
            MailServer mailServer = new MailServer();
            Messenger messenger = new Messenger(mailServer, templateEngine);

            System.out.print("Please enter the sender`s email: ");
            String senderEmail = scanner.nextLine();
            System.out.print("Please enter the receiver`s email: ");
            String receiverEmail = scanner.nextLine();
            System.out.print("Please enter the subject: ");
            String subject = scanner.nextLine();
            System.out.print("Please enter the email body one line: ");
            String body = scanner.nextLine();

            Client client = new Client(senderEmail + ", " + receiverEmail);
            printMenu(options);
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        messenger.sendMessage(client, new Template("bestRegards", subject, body));
                        break;
                    case 2:
                        messenger.sendMessage(client, new Template("byeBye", subject, body));
                        break;
                    case 3:
                        messenger.sendMessage(client, new Template("seeYouSoon", subject, body));
                        break;
                    case 4:
                        exit(0);

                }
            } catch (Exception ex) {
                System.out.println("Please enter an integer value for template between 1 and " + options.length);
                scanner.next();
            }

            System.out.println("If you want to send another email, please press 1 ");
            System.out.println("If you want to exit, please press 0");

            try {
                continueSendingEmails = scanner.nextInt();
                switch (continueSendingEmails) {
                    case 1:
                        break;
                    case 0:
                        exit(0);

                }
            } catch (Exception ex) {
                System.out.println("If you want to send another email, please press 1");
                System.out.println("If you want to exit, please press 0");
                scanner.next();
            }

        }
    }

}