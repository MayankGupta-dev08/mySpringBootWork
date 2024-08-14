package dev.mayank.registerbeansmanually.beans;

/**
 * Vehicle class which will be used as a bean in the Spring Container, and will be registered manually.
 */
public class Vehicle {
    private String name;

    public Vehicle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHello() {
        System.out.println("Printing Hello from Component Vehicle Bean");
    }
}