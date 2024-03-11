package dev.mayank.emailApp.v2;

import dev.mayank.emailApp.SpellChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Why Field Injection is harmful - https://www.vojtechruzicka.com/field-dependency-injection-considered-harmful/
 */

@Component
class EmailClient {
    private SpellChecker spellChecker;

    @SuppressWarnings("unused")
    public EmailClient() {
    }

    @Autowired
    public EmailClient(@Qualifier("basicSpellChecker") SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    @SuppressWarnings("unused")
    public SpellChecker getSpellChecker() {
        return spellChecker;
    }

    //@Autowired
    @SuppressWarnings("unused")
    public void setSpellChecker(SpellChecker spellChecker) {
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