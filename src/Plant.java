public abstract class Plant {
    protected String name;
    protected String color;
    protected String region;
    protected String shape;
    protected Double price;
    protected boolean isEdible;
    protected int daysToHarvest;
    protected String growingConditions;
    protected String season;
    protected String species;

    public Plant(String name, String color, String region, String shape, Double price, boolean isEdible, int daysToHarvest, String growingConditions, String season, String species) {
        this.name = name;
        this.color = color;
        this.region = region;
        this.shape = shape;
        this.price = price;
        this.isEdible = isEdible;
        this.daysToHarvest = daysToHarvest;
        this.growingConditions = growingConditions;
        this.season = season;
        this.species = species;
    }

    public abstract void plant();
    public abstract void water();
    public abstract void harvest();
    public abstract void sell();
}