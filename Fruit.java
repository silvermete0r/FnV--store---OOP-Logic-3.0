public class Fruit extends Plant {
    private String flavor;
    private boolean isFleshy;
    private boolean hasSeeds;

    public Fruit(String name, String color, String region, String shape, Double price, boolean isEdible, int daysToHarvest, String growingConditions, String season, String species, String flavor, boolean isFleshy, boolean hasSeeds) {
        super(name, color, region, shape, price, isEdible, daysToHarvest, growingConditions, season, species);
        this.flavor = flavor;
        this.isFleshy = isFleshy;
        this.hasSeeds = hasSeeds;
    }

    @Override
    public void plant() {
        System.out.println("Planting fruit tree: " + name);
    }

    @Override
    public void water() {
        System.out.println("Watering fruit tree: " + name);
    }

    @Override
    public void harvest() {
        System.out.println("Harvesting fruit: " + name);
    }

    @Override
    public void sell() {
        System.out.println("Selling fruit: " + name);
    }
}