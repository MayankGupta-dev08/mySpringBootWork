package dev.mayank.emailApp.v3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring IoC Container:
 * - Create and Manage objects and their lifecycle.
 * - Inject Dependencies to our class/es.
 * - ApplicationContext, AnnotationConfigApplicationContext
 * ComponentScan, Autowiring
 */
class EmailApplication {
    public static void main(String... args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        EmailClient emailClient = applicationContext.getBean("emailClient", EmailClient.class);

        emailClient.sendEmail("my fifth email!!");
        System.out.println("---------------------------------");
        emailClient.sendEmail("my sixth email...");

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Bean Scope
        AdvancedSpellChecker advancedSpellChecker1 = applicationContext.getBean("advancedSpellChecker", AdvancedSpellChecker.class);
        System.out.println(advancedSpellChecker1);
        AdvancedSpellChecker advancedSpellChecker2 = applicationContext.getBean("advancedSpellChecker", AdvancedSpellChecker.class);
        System.out.println(advancedSpellChecker2);

        ((AnnotationConfigApplicationContext) applicationContext).registerShutdownHook();
    }
}