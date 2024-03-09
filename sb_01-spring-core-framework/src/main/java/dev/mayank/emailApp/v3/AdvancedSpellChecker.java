package dev.mayank.emailApp.v3;

import dev.mayank.emailApp.SpellChecker;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("advancedSpellChecker")
class AdvancedSpellChecker implements SpellChecker {
    @Override
    public boolean checkSpelling(String msg) {
        if (msg != null && !msg.isEmpty()) {
            //Logic: Determines the language, auto-fixes grammar and spelling errors
            System.out.println("Advanced spell check is passed.");
            return true;
        }
        System.out.println("Advanced spell check failed.");
        return false;
    }
}