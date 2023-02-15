import java.sql.*;
import java.util.ArrayList;

public class dataManipulation {
    private static String jdbcURL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=users";
    private static String username = "postgres";
    private static String password = "Titanic235";
    public static void newUser(String login, String passwordx, String fullname, String email, String phone, String card_code){
        try {
            Connection conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected to PostgreSQL server");
            String sql = "INSERT INTO users (login, password, fullname, email, phone, card_code) VALUES(?,?,?,?,?,?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, Encryption.MD5(passwordx));
            statement.setString(3, fullname);
            statement.setString(4, email);
            statement.setString(5, phone);
            statement.setString(6, card_code);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New user has been registered Successfully!");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in connecting to PostgreSQL server!");
            e.printStackTrace();
        }
    }

    public static void updateUserData(String passwordx, String phone, String card_code){
        try {
            Connection conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected to PostgreSQL server");
            String sql = "UPDATE users SET password = ?, phone = ?, card_code = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, Encryption.MD5(passwordx));
            statement.setString(2, phone);
            statement.setString(3, card_code);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User data has been updated Successfully!");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in connecting to PostgreSQL server!");
            e.printStackTrace();
        }
    }

    public static void deleteUserData(String login){
        try {
            Connection conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected to PostgreSQL server");
            String sql = "DELETE FROM users WHERE login = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, login);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User data has been removed Successfully!");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in connecting to PostgreSQL server!");
            e.printStackTrace();
        }
    }

    public static void getUserData(String login) {
        try {
            Connection conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected to PostgreSQL server");
            String sql = "SELECT * FROM users WHERE login = " + login + ";";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ArrayList<String> textDataList = new ArrayList<String>();
            while (resultSet.next()) {
                textDataList.add(resultSet.getString("login"));
                textDataList.add(resultSet.getString("password"));
                textDataList.add(resultSet.getString("fullname"));
                textDataList.add(resultSet.getString("email"));
                textDataList.add(resultSet.getString("phone"));
                textDataList.add(resultSet.getString("card_code"));
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in connecting to PostgreSQL server!");
            e.printStackTrace();
        }
    }

    public static void newFruit(String name, String color, String region, String shape, String price, boolean isEdible, String daysToHarvest, String growingConditions, String season, String species, String flavor, boolean isFleshy, boolean hasSeeds){
        try {
            Connection conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected to PostgreSQL server");
            String sql = "INSERT INTO fruit (name, color, region, shape, price, isedible, daystoharvest, growingconditions, season, species, flavor, isfleshy, hasseeds) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, color);
            statement.setString(3, region);
            statement.setString(4, shape);
            statement.setString(5, price);
            statement.setBoolean(6, isEdible);
            statement.setString(7, daysToHarvest);
            statement.setString(8, growingConditions);
            statement.setString(9, season);
            statement.setString(10, species);
            statement.setString(11, flavor);
            statement.setBoolean(12, isFleshy);
            statement.setBoolean(13, hasSeeds);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New fruit has been added Successfully!");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in connecting to PostgreSQL server!");
            e.printStackTrace();
        }
    }

    public static void newVegetable(String name, String color, String region, String shape, String price, boolean isEdible, String daysToHarvest, String growingConditions, String season, String species, String taste, boolean isLeafy, boolean isRoot){
        try {
            Connection conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected to PostgreSQL server");
            String sql = "INSERT INTO vegetable (name, color, region, shape, price, isedible, daystoharvest, growingconditions, season, species, taste, isleafy, isroot) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, color);
            statement.setString(3, region);
            statement.setString(4, shape);
            statement.setString(5, price);
            statement.setBoolean(6, isEdible);
            statement.setString(7, daysToHarvest);
            statement.setString(8, growingConditions);
            statement.setString(9, season);
            statement.setString(10, species);
            statement.setString(11, taste);
            statement.setBoolean(12, isLeafy);
            statement.setBoolean(13, isRoot);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New vegetable has been added Successfully!");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in connecting to PostgreSQL server!");
            e.printStackTrace();
        }
    }
}
