class OrganicPricingStrategy implements PricingStrategy {
    public double calculatePrice(Produce produce) {
        double basePrice = produce.getPrice();
        return basePrice * 1.5; // add 50% markup for organic produce
    }
}