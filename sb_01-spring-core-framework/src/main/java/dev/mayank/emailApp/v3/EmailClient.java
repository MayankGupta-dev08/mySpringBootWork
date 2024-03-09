package dev.mayank.emailApp.v3;

import dev.mayank.emailApp.SpellChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class EmailClient {
    private SpellChecker spellChecker;

    @Autowired
    public EmailClient(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }
    public void sendEmail(String emailMsg) {
        if (spellChecker.checkSpelling(emailMsg)) {
            System.out.println(emailMsg);
            System.out.println("Email sent!!");
        } else
            System.out.println("Failed to send the email!!");
    }
}