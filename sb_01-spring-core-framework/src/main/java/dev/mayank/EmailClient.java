package dev.mayank;

class EmailClient {
    private SpellChecker spellChecker;

    public EmailClient(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    public void sendEmail(String emailMsg) {
        if (spellChecker.checkSpelling(emailMsg))
            System.out.println("Email sent!!");
        else
            System.out.println("Failed to send the email!!");
    }
}