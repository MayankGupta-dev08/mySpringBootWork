package dev.mayank.emailApp.v3;

import dev.mayank.emailApp.SpellChecker;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Primary
@Component("advancedSpellChecker")
@PropertySource(value = "classpath:/application.properties")
class AdvancedSpellChecker implements SpellChecker, InitializingBean, DisposableBean {
    @Value("${app.database.uri}")
    private String databaseUri;

    @Override
    public boolean checkSpelling(String msg) {
        if (msg != null && !msg.isEmpty()) {
            //Logic: Determines the language, auto-fixes grammar and spelling errors
            System.out.println("Advanced spell check is passed.");
            System.out.println("Database uri: " + databaseUri);
            return true;
        }
        System.out.println("Advanced spell check failed.");
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Setting the properties of the bean...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Destroying properties of the bean...");
    }
}