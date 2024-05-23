package dev.mayank.other.lifecycle;

class Samosa {
    private final float price;

    public Samosa(float price) {
        this.price = price;
    }

    /**
     * used as lifecycle method: init
     */
    public void setup() {
        System.out.println("initialization...");
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "~~~ [Samosa = %s/- Rs.] ~~~".formatted(price);
    }

    /**
     * used as lifecycle method: destroy
     */
    public void bye() {
        System.out.println("closing up...");
    }
}