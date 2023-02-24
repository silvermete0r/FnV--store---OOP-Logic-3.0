class Banana implements Produce {
    private double price;

    public void displayInfo() {
        System.out.println("This is a banana.");
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}