package dev.mayank.infinityschoolhouse.model;

import java.text.MessageFormat;

public class ContactDetail {
    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;

    public ContactDetail(String name, String mobileNum, String email, String subject, String message) {
        this.name = name;
        this.mobileNum = mobileNum;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return MessageFormat.format(
                "ContactDetail'{'name=''{0}'', mobileNum=''{1}'', email=''{2}'', subject=''{3}'', message=''{4}'''}'",
                name, mobileNum, email, subject, message);
    }
}
