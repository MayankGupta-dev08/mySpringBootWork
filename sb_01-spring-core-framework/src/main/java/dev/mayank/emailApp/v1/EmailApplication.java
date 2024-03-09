package dev.mayank.emailApp.v1;

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
        ApplicationContext applicationContext =
                getClassPathXmlApplicationContext();
                // getAnnotationConfigApplicationContext();
        EmailClient emailClient = applicationContext.getBean("emailClient", EmailClient.class);
        sendDummyEmails(emailClient);
    }

    @SuppressWarnings("unused")
    private static AnnotationConfigApplicationContext getAnnotationConfigApplicationContext() {
        return new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @SuppressWarnings("unused")
    private static ClassPathXmlApplicationContext getClassPathXmlApplicationContext() {
        return new ClassPathXmlApplicationContext("beans.xml");
    }

    private static void sendDummyEmails(EmailClient emailClient) {
        emailClient.sendEmail("my first email!!");
        System.out.println("---------------------------------");
        emailClient.sendEmail("my second email...");
    }

    @Deprecated
    @SuppressWarnings("unused")
    private static void withoutSpringFramework() {
        //restriction --> manually injecting dependency
        EmailClient legacyEmailClient = new EmailClient();
        legacyEmailClient.setSpellChecker(new BasicSpellChecker());
        sendDummyEmails(legacyEmailClient);
    }
}