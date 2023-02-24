class ProduceFactory {
    public static Produce getProduce(String produceType) {
        if (produceType == null) {
            return null;
        }
        if (produceType.equalsIgnoreCase("apple")) {
            return new Apple();
        }
        if (produceType.equalsIgnoreCase("banana")) {
            return new Banana();
        }
        return null;
    }
}