package control;

import java.util.Date;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.Data;
 
/**
 * @author zeja
 */
public class JavaMailThread extends Thread{
    
    String subject;
    String text;
	
	public JavaMailThread(String subject, String text) {
    	this.subject=subject;
    	this.text=text;
    }
	
	public void sendMail(String  smtpHost,String  username,String  password,String  senderAddress,String  recipientsAddress,String  subject,String  text ){
        MailAuthenticator auth = new MailAuthenticator(username, password);
 
        Properties  properties = new Properties ();
 
        // Den Properties wird die ServerAdresse hinzugefügt
        properties.put("mail.smtp.host", smtpHost);
 
        // !!Wichtig!! Falls der SMTP-Server eine Authentifizierung
        // verlangt
        // muss an dieser Stelle die Property auf "true" gesetzt
        // werden
        properties.put("mail.smtp.auth", "true");
 
        // Hier wird mit den Properties und dem implements Contructor
        // erzeugten
        // MailAuthenticator eine Session erzeugt
        Session session = Session.getDefaultInstance(properties, auth);
 
        try {
            // Eine neue Message erzeugen
            Message msg = new MimeMessage(session);
 
            // Hier werden die Absender- und Empfängeradressen gesetzt
            msg.setFrom(new InternetAddress(senderAddress));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
                    recipientsAddress, false));
 
            // Der Betreff und Body der Message werden gesetzt
            msg.setSubject(subject);
            msg.setText(text);
 
            // Hier lassen sich HEADER-Informationen hinzufügen
            msg.setHeader("Test", "Test");
            msg.setSentDate(new Date ( ));
 
            // Zum Schluss wird die Mail natürlich noch verschickt
            Transport.send(msg);
 
        }
        catch (Exception  e) {
            e.printStackTrace( );
        }
    }
    
    class MailAuthenticator extends Authenticator  {
 
        /**
         * Ein String, der den Usernamen nach der Erzeugung eines
         * Objektes<br>
         * dieser Klasse enthalten wird.
         */
        private final String  user;
 
        /**
         * Ein String, der das Passwort nach der Erzeugung eines
         * Objektes<br>
         * dieser Klasse enthalten wird.
         */
        private final String  password;
 
        /**
         * Der Konstruktor erzeugt ein MailAuthenticator Objekt<br>
         * aus den beiden Parametern user und passwort.
         * 
         * @param user
         *            String, der Username fuer den Mailaccount.
         * @param password
         *            String, das Passwort fuer den Mailaccount.
         */
        public MailAuthenticator(String  user, String  password) {
            this.user = user;
            this.password = password;
        }
 
        /**
         * Diese Methode gibt ein neues PasswortAuthentication
         * Objekt zurueck.
         * 
         * @see javax.mail.Authenticator#getPasswordAuthentication()
         */
        protected PasswordAuthentication  getPasswordAuthentication() {
            return new PasswordAuthentication (this.user, this.password);
        }
    }
    
    @SuppressWarnings("deprecation")
	public void run(){
    	new Data();
    	Data.logger.info("Sending E-Mail to "+Data.mailrecipientadress+" ...");
    	sendMail(Data.mailsmtphost, Data.mailusername, Data.mailpassword, Data.mailsenderadress, Data.mailrecipientadress, subject, text);
    	Data.logger.info("E-Mail sent!");
    	stop();
    }
}