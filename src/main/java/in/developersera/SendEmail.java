package in.developersera;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    public static void main(String[] args) {

        final String USERNAME = "your_email@outlook.com:;
        final String PASS = "PASSWORD";
        
        final String EMAIL_TO = "email_to@gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASS);
                    }
                });
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO));   // like something@gmail.com
            message.setSubject("Test");
            message.setText("Hi email sent successfully using outlook");

            Transport.send(message);

            System.out.println("Sent Email");

        } catch (MessagingException e) {
            System.out.println("Error --> "+e.toString());
            throw new RuntimeException(e);
        }
    }
}
