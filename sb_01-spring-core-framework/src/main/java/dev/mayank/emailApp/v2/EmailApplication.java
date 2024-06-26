package dev.mayank.emailApp.v2;

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

        emailClient.sendEmail("my third email!!");
        System.out.println("---------------------------------");
        emailClient.sendEmail("my fourth email...");

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Bean Scope
        BasicSpellChecker basicSpellChecker1 = applicationContext.getBean("basicSpellChecker", BasicSpellChecker.class);
        System.out.println(basicSpellChecker1);
        BasicSpellChecker basicSpellChecker2 = applicationContext.getBean("basicSpellChecker", BasicSpellChecker.class);
        System.out.println(basicSpellChecker2);
    }
}