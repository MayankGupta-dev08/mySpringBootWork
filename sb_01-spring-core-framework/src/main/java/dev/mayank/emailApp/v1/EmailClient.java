package dev.mayank.emailApp.v1;

import dev.mayank.emailApp.SpellChecker;

class EmailClient {
    private SpellChecker spellChecker;

    public EmailClient() {
    }

    public EmailClient(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    public SpellChecker getSpellChecker() {
        return spellChecker;
    }

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