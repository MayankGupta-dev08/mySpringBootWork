package dev.mayank.emailApp.v2;

import dev.mayank.emailApp.SpellChecker;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Scope("singleton") //Default
@Scope("prototype")
@Component("basicSpellChecker")
class BasicSpellChecker implements SpellChecker {
    @Override
    public boolean checkSpelling(String msg) {
        if (msg != null && !msg.isEmpty()) {
            System.out.println("Basic spell check is passed.");
            return true;
        }
        System.out.println("Basic spell check failed.");
        return false;
    }
}