public class Vegetable extends Plant {
    private String taste;
    private boolean isLeafy;
    private boolean isRoot;

    public Vegetable(String name, String color, String region, String shape, Double price, boolean isEdible, int daysToHarvest, String growingConditions, String season, String species, String taste, boolean isLeafy, boolean isRoot) {
        super(name, color, region, shape, price, isEdible, daysToHarvest, growingConditions, season, species);
        this.taste = taste;
        this.isLeafy = isLeafy;
        this.isRoot = isRoot;
    }

    @Override
    public void plant() {
        System.out.println("Planting vegetable seed: " + name);
    }

    @Override
    public void water() {
        System.out.println("Watering vegetable: " + name);
    }

    @Override
    public void harvest() {
        System.out.println("Harvesting vegetable: " + name);
    }

    @Override
    public void sell() {
        System.out.println("Selling vegetable: " + name);
    }
}