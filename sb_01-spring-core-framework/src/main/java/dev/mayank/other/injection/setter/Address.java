package dev.mayank.other.injection.setter;

class Address {
    private String mainLine;
    private String city;
    private String state;
    private Integer pin;
    private String country;

    public Address() {
    }

    public Address(String mainLine, String city, String state, Integer pin, String country) {
        this.mainLine = mainLine;
        this.city = city;
        this.state = state;
        this.pin = pin;
        this.country = country;
    }

    public String getMainLine() {
        return mainLine;
    }

    public void setMainLine(String mainLine) {
        this.mainLine = mainLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "[%s, %s, %s, %d, %s]".formatted(mainLine, city, state, pin, country);
    }
}