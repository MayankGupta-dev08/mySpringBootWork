package dev.mayank.other.injection.setter;

import java.util.*;

public class Student {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<String> subjects;
    private List<String> marks;
    private Map<String, String> favTeams;
    private Properties favYtChannels;
    private Address address;

    /**
     * Mandatory to have default constructor for DI to work.
     */
    public Student() {
    }

    public Student(UUID id, String firstName, String lastName, String email, Set<String> subjects,
                   List<String> marks, Map<String, String> favTeams, Properties favYtChannels, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.subjects = subjects;
        this.marks = marks;
        this.favTeams = favTeams;
        this.favYtChannels = favYtChannels;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getMarks() {
        return marks;
    }

    public void setMarks(List<String> marks) {
        this.marks = marks;
    }

    public Map<String, String> getFavTeams() {
        return favTeams;
    }

    public void setFavTeams(Map<String, String> favTeams) {
        this.favTeams = favTeams;
    }

    public Properties getFavYtChannels() {
        return favYtChannels;
    }

    public void setFavYtChannels(Properties favYtChannels) {
        this.favYtChannels = favYtChannels;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{%n id=%s,%n Name=%s %s,%n email=%s,%n subjects=%s, marks=%s,%n favTeams=%s,%n favYtChannels=%s,%n address=%s%n}"
                .formatted(id, firstName, lastName, email, subjects, marks, favTeams, favYtChannels, address);
    }
}