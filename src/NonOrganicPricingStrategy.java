class NonOrganicPricingStrategy implements PricingStrategy {
    public double calculatePrice(Produce produce) {
        return produce.getPrice(); // no markup for non-organic produce
    }
}
