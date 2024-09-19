package dev.mayank.ishproxyservice.model;

import lombok.Data;

@Data
public class ContactDetail {

    private int contactId;
    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;
    private String status;

}