package dev.mayank.emailApp.v1;

import org.springframework.context.annotation.Bean;

class AppConfig {
    @Bean(name = "emailClient")
    public EmailClient createEmailClient() {
        EmailClient emailClient = new EmailClient();
        emailClient.setSpellChecker(createBasicSpellChecker());
        return emailClient;
    }

    @SuppressWarnings("unused")
    @Bean(name = "basicSpellChecker")
    public SpellChecker createBasicSpellChecker() {
        return new BasicSpellChecker();
    }

    @SuppressWarnings("unused")
    @Bean(name = "advancedSpellChecker")
    public SpellChecker createAdvancedSpellChecker() {
        return new AdvancedSpellChecker();
    }
}