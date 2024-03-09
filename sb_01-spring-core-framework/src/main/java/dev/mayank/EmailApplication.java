package dev.mayank;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring IoC Container:
 * - Create and Manage objects and their lifecycle.
 * - Inject Dependencies to our class/es.
 * - BeanFactory, ApplicationContext, ClassPathXmlApplicationContext, AnnotationConfigApplicationContext
 */
class EmailApplication {
    public static void main(String... args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        EmailClient emailClient = applicationContext.getBean("emailClient", EmailClient.class);
        sendDummyEmails(emailClient);
    }

    @SuppressWarnings("unused")
    private static void depInjectionUsingClassPathXmlAppContext() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
        EmailClient emailClient = appContext.getBean("emailClient", EmailClient.class);
        sendDummyEmails(emailClient);
    }

    private static void sendDummyEmails(EmailClient emailClient) {
        emailClient.sendEmail("my first email!!");
        System.out.println("---------------------------------");
        emailClient.sendEmail("my second email...");
    }

    @SuppressWarnings("unused")
    @Deprecated
    private static void withoutSpringFramework() {
        //restriction --> manually injecting dependency
        EmailClient legacyEmailClient = new EmailClient(new BasicSpellChecker());
        sendDummyEmails(legacyEmailClient);
    }
}