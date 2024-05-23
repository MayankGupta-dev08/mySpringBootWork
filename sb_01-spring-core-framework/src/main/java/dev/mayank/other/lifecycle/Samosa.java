package dev.mayank.other.lifecycle;

class Samosa {
    private final float price;

    public Samosa(float price) {
        this.price = price;
    }

    public void init() {

    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "~~~ [Samosa = %s/- Rs.] ~~~".formatted(price);
    }

    public void destroy() {

    }
}