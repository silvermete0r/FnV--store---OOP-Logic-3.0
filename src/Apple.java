class Apple implements Produce {
    private double price;

    public void displayInfo() {
        System.out.println("This is an apple.");
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}