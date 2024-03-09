package dev.mayank;

import org.springframework.context.annotation.Bean;

class AppConfig {
    @Bean(name = "emailClient")
    public EmailClient createEmailClient() {
        return new EmailClient(createBasicSpellChecker());
    }

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