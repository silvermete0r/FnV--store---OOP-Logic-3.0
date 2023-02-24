import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Fruit & Vegetables Market");

        Scanner in = new Scanner(System.in);
        do {
            System.out.println("You are already registered in the System? (Y/N)");
            String ans = in.nextLine().toLowerCase();
            if (ans.equals("y")) {
                System.out.print(" Enter login: ");
                String login = in.nextLine();

                System.out.print(" Enter password: ");
                String password = Encryption.MD5(in.nextLine());

                if ("admin".equals(login) && Encryption.MD5("admin").equals(password)) {
                    System.out.println(" User successfully logged-in.. ");
                    openFnVMarket();
                } else {
                    System.out.println(" Invalid login or password. Please Try Again! ");
                }
            } else if (ans.equals("n")) {
                System.out.println("Ok, it's not a Problem. Let's register a new account for you!");

                System.out.print(" Enter login: ");
                String login = in.nextLine();

                System.out.print(" Enter password: ");
                String password = in.nextLine();

                System.out.print(" Enter fullname: ");
                String fullname = in.nextLine();

                System.out.print(" Enter email: ");
                String email = in.nextLine();

                System.out.print(" Enter phone: ");
                String phone = in.nextLine();

                System.out.print(" Enter card_code: ");
                String card_code = in.nextLine();

                dataManipulation.newUser(login, password, fullname, email, phone, card_code);

                System.out.println("Ok. Let's Login!");
            } else {
                System.out.println("Invalid command, Please Try Again!");
            }
        } while (true);
    }
    public static void openFnVMarket() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (true) {
            System.out.println("Welcome to the Fruit and Vegetable Store!");
            System.out.println("Please choose from the following options:");
            System.out.println("1. View profile");
            System.out.println("2. Edit profile");
            System.out.println("3. View fruits");
            System.out.println("4. View vegetables");
            System.out.println("5. Add new fruits");
            System.out.println("6. Add new vegetables");
            System.out.println("7. Purchase Fruit");
            System.out.println("8. Purchase Vegetable");
            System.out.println("8. Delete Account");
            System.out.println("10. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewProfile();
                    break;
                case 2:
                    editProfile();
                    break;
                case 3:
                    viewFruits();
                    break;
                case 4:
                    viewVegetables();
                    break;
                case 5:
                    newFruits();
                    break;
                case 6:
                    newVegetables();
                    break;
                case 7:
                    System.out.println("Enter fruit ID to buy: ");
                    int id = scanner.nextInt();
                    purchaseFruit(id);
                    break;
                case 8:
                    System.out.println("Enter Vegetable ID to buy: ");
                    int idx = scanner.nextInt();
                    purchaseVegetable(idx);
                    break;
                case 9:
                    System.out.println("Enter you login to confirm your actions: ");
                    String login = scanner.nextLine();
                    dataManipulation.deleteUserData(login);
                    break;
                case 10:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static String jdbcURL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=users";
    private static String username = "postgres";
    private static String password = "Titanic235";

    public static void viewProfile() {

        System.out.println("Here is your profile: ");
        try {
            Connection conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected to PostgreSQL server");
            String sql = "SELECT * FROM users WHERE login = 'admin';";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ArrayList<String> textDataList = new ArrayList<String>();
            while (resultSet.next()) {
                textDataList.add(resultSet.getString("login"));
                textDataList.add(resultSet.getString("fullname"));
                textDataList.add(resultSet.getString("email"));
                textDataList.add(resultSet.getString("phone"));
                textDataList.add(resultSet.getString("card_code"));
            }
            System.out.println("Login: " + textDataList.get(0));
            System.out.println("Fullname: " + textDataList.get(1));
            System.out.println("Email: " + textDataList.get(2));
            System.out.println("Phone: " + textDataList.get(3));
            System.out.println("Card Code: " + textDataList.get(4));
            consoleBreak();
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in connecting to PostgreSQL server!");
            e.printStackTrace();
        }
    }

    public static void editProfile() {
        System.out.println("Edit your profile: ");
        Scanner in = new Scanner(System.in);

        System.out.print(" Enter new password: ");
        String password = in.nextLine();

        System.out.print(" Enter new phone: ");
        String phone = in.nextLine();

        System.out.print(" Enter new card_code: ");
        String card_code = in.nextLine();

        dataManipulation.updateUserData(password, phone, card_code);
        consoleBreak();
    }
    public static void viewFruits() {
        System.out.println("Here are the available fruits: ");
        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password)) {
            String query = "SELECT name, color, region, shape, price, isedible, daystoharvest, growingconditions, season, species, flavor, isfleshy, hasseeds FROM fruit";

            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    String region = rs.getString("region");
                    String shape = rs.getString("shape");
                    double price = rs.getDouble("price");
                    boolean isEdible = rs.getBoolean("isedible");
                    int daysToHarvest = rs.getInt("daystoharvest");
                    String growingConditions = rs.getString("growingconditions");
                    String season = rs.getString("season");
                    String species = rs.getString("species");
                    String flavor = rs.getString("flavor");
                    boolean isFleshy = rs.getBoolean("isfleshy");
                    boolean hasSeeds = rs.getBoolean("hasseeds");

                    System.out.printf("%-10s %-10s %-10s %-10s %-10.2f %-5b %-5d %-20s %-10s %-20s %-10s %-5b %-5b%n",
                            name, color, region, shape, price, isEdible, daysToHarvest, growingConditions, season, species, flavor, isFleshy, hasSeeds);
                    consoleBreak();
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void viewVegetables() {
        System.out.println("Here are the available vegetables: ");
        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password)) {
            String query = "SELECT name, color, region, shape, price, isedible, daystoharvest, growingconditions, season, species, taste, isleafy, isroot FROM vegetable";

            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    String region = rs.getString("region");
                    String shape = rs.getString("shape");
                    double price = rs.getDouble("price");
                    boolean isEdible = rs.getBoolean("isedible");
                    int daysToHarvest = rs.getInt("daystoharvest");
                    String growingConditions = rs.getString("growingconditions");
                    String season = rs.getString("season");
                    String species = rs.getString("species");
                    String taste = rs.getString("taste");
                    boolean isleafy = rs.getBoolean("isleafy");
                    boolean isroot = rs.getBoolean("isroot");

                    System.out.printf("%-10s %-10s %-10s %-10s %-10.2f %-5b %-5d %-20s %-10s %-20s %-10s %-5b %-5b%n",
                            name, color, region, shape, price, isEdible, daysToHarvest, growingConditions, season, species, taste, isleafy, isroot);
                    consoleBreak();
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void newFruits() {
        System.out.println("Adding new fruit into DB Store: ");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the name of the fruit: ");
        String name = input.nextLine();
        System.out.print("Enter the color of the fruit: ");
        String color = input.nextLine();
        System.out.print("Enter the region where the fruit is grown: ");
        String region = input.nextLine();
        System.out.print("Enter the shape of the fruit: ");
        String shape = input.nextLine();
        System.out.print("Enter the price of the fruit: ");
        String price = input.nextLine();
        System.out.print("Is the fruit edible? (Y/N): ");
        boolean isEdible = input.nextLine().equalsIgnoreCase("Y");
        System.out.print("Enter the number of days to harvest: ");
        String daysToHarvest = input.nextLine();
        System.out.print("Enter the growing conditions of the fruit: ");
        String growingConditions = input.nextLine();
        System.out.print("Enter the season when the fruit is available: ");
        String season = input.nextLine();
        System.out.print("Enter the species of the fruit: ");
        String species = input.nextLine();
        System.out.print("Enter the flavor of the fruit: ");
        String flavor = input.nextLine();
        System.out.print("Is the fruit fleshy? (Y/N): ");
        boolean isFleshy = input.nextLine().equalsIgnoreCase("Y");
        System.out.print("Does the fruit have seeds? (Y/N): ");
        boolean hasSeeds = input.nextLine().equalsIgnoreCase("Y");
        dataManipulation.newFruit(name, color, region, shape, price, isEdible, daysToHarvest, growingConditions, season, species, flavor, isFleshy, hasSeeds);
        consoleBreak();
    }

    public static void newVegetables() {
        System.out.println("Adding new vegetable into DB Store: ");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the name of the vegetable: ");
        String name = input.nextLine();
        System.out.print("Enter the color of the vegetable: ");
        String color = input.nextLine();
        System.out.print("Enter the region where the vegetable is grown: ");
        String region = input.nextLine();
        System.out.print("Enter the shape of the vegetable: ");
        String shape = input.nextLine();
        System.out.print("Enter the price of the vegetable: ");
        String price = input.nextLine();
        System.out.print("Is the vegetable edible? (Y/N): ");
        boolean isEdible = input.nextLine().equalsIgnoreCase("Y");
        System.out.print("Enter the number of days to harvest: ");
        String daysToHarvest = input.nextLine();
        System.out.print("Enter the growing conditions of the vegetable: ");
        String growingConditions = input.nextLine();
        System.out.print("Enter the season when the vegetable is available: ");
        String season = input.nextLine();
        System.out.print("Enter the species of the vegetable: ");
        String species = input.nextLine();
        System.out.print("Enter the taste of the vegetable: ");
        String taste = input.nextLine();
        System.out.print("Is the vegetable leafy? (Y/N): ");
        boolean isLeafy = input.nextLine().equalsIgnoreCase("Y");
        System.out.print("Does the vegetable have roots? (Y/N): ");
        boolean isRoot = input.nextLine().equalsIgnoreCase("Y");
        dataManipulation.newVegetable(name, color, region, shape, price, isEdible, daysToHarvest, growingConditions, season, species, taste, isLeafy, isRoot);
        consoleBreak();
    }

    public static void purchaseFruit(int fruitId) {
        Scanner scanner = new Scanner(System.in);
        try {
            Connection conn = DriverManager.getConnection(jdbcURL, username, password);
            String query = "SELECT name, color, region, shape, price, isedible, daystoharvest, growingconditions, season, species, flavor, isfleshy, hasseeds FROM fruit WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, fruitId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String color = rs.getString("color");
                String region = rs.getString("region");
                String shape = rs.getString("shape");
                double price = rs.getDouble("price");
                boolean isEdible = rs.getBoolean("isedible");
                int daysToHarvest = rs.getInt("daystoharvest");
                String growingConditions = rs.getString("growingconditions");
                String season = rs.getString("season");
                String species = rs.getString("species");
                String flavor = rs.getString("flavor");
                boolean isFleshy = rs.getBoolean("isfleshy");
                boolean hasSeeds = rs.getBoolean("hasseeds");

                System.out.printf("Name: %s%nColor: %s%nRegion: %s%nShape: %s%nPrice: %.2f%nIs Edible: %b%nDays to Harvest: %d%nGrowing Conditions: %s%nSeason: %s%nSpecies: %s%nTaste: %s%nIs Fleshy: %b%nHas Seeds: %b%n",
                        name, color, region, shape, price, isEdible, daysToHarvest, growingConditions, season, species, flavor, isFleshy, hasSeeds);
                consoleBreak();
            } else {
                System.out.println("Fruit not found.");
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void purchaseVegetable(int vegetableId) {
        Scanner scanner = new Scanner(System.in);
        try {
            Connection conn = DriverManager.getConnection(jdbcURL, username, password);
            String query = "SELECT name, color, region, shape, price, isedible, daystoharvest, growingconditions, season, species, taste, isleafy, isroot FROM vegetable WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, vegetableId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String color = rs.getString("color");
                String region = rs.getString("region");
                String shape = rs.getString("shape");
                double price = rs.getDouble("price");
                boolean isEdible = rs.getBoolean("isedible");
                int daysToHarvest = rs.getInt("daystoharvest");
                String growingConditions = rs.getString("growingconditions");
                String season = rs.getString("season");
                String species = rs.getString("species");
                String taste = rs.getString("flavor");
                boolean isleafy = rs.getBoolean("isfleshy");
                boolean isroot = rs.getBoolean("hasseeds");

                System.out.printf("Name: %s%nColor: %s%nRegion: %s%nShape: %s%nPrice: %.2f%nIs Edible: %b%nDays to Harvest: %d%nGrowing Conditions: %s%nSeason: %s%nSpecies: %s%nTaste: %s%nIs Leafy: %b%nIs Root: %b%n",
                        name, color, region, shape, price, isEdible, daysToHarvest, growingConditions, season, species, taste, isleafy, isroot);
                consoleBreak();
            } else {
                System.out.println("Fruit not found.");
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void designPatternsFnV(){
        Scanner scanner = new Scanner(System.in);

        // create some produce items and add them to the inventory
        Produce apple = ProduceFactory.getProduce("apple");
        apple.setPrice(0.75);
        Produce banana = ProduceFactory.getProduce("banana");
        banana.setPrice(1.25);
        ProduceInventory inventory = new ProduceInventory();
        inventory.addProduce(apple);
        inventory.addProduce(banana);
        // set the pricing strategy for the inventory
        System.out.println("Enter pricing strategy: organic or non-organic");
        String pricingStrategy = scanner.nextLine();
        if (pricingStrategy.equalsIgnoreCase("organic")) {
            inventory.addObserver((ProduceInventoryObserver) new OrganicPricingStrategy());
        } else {
            inventory.addObserver((ProduceInventoryObserver) new NonOrganicPricingStrategy());
        }

        // check the inventory for low prices
        ProduceInventoryObserver checker = new ProduceInventoryChecker();
        inventory.addObserver(checker);
        inventory.notifyObservers();

        // display the produce items and their prices
        for (Produce produce : inventory.produceList) {
            produce.displayInfo();
            System.out.println("Price: $" + produce.getPrice());
        }
    }

    public static void consoleBreak() {
        System.out.println("**********");
        System.out.println();
    }
}