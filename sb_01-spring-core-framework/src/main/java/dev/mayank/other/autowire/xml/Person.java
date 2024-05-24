package dev.mayank.other.autowire.xml;

class Person {
    private int age;
    private Address address;

    public Person() {
    }

    public Person(int age, Address address) {
        this.age = age;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{age=%d, address=%s}".formatted(age, address);
    }
}