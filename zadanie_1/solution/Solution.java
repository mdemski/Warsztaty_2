package zadanie_1.solution;

import zadanie_1.db_connection.ConnectionGen;

import java.sql.*;
import java.util.*;


public class Solution {
    private int id;
    private String created;
    private String updated;
    private String description;
    private int exercise_id;
    private int user_id;
    private double rating;


    public Solution() {

    }

    Solution(String created, String updated, String description) {
        this.created = created;
        this.updated = updated;
        this.description = description;
    }

    public Solution(int id, String description, int user_id) {
        this.id = id;
        this.description = description;
        this.user_id = user_id;
    }

    public Solution(int id, int exercise_id, String description, String created) {
        this.id = id;
        this.exercise_id = exercise_id;
        this.description = description;
        this.created = created;
    }

    public Solution(int exercise_id, String description, String created) {
        this.exercise_id = exercise_id;
        this.description = description;
        this.created = created;
    }

    public Solution(double rating) {
        this.rating = rating;
    }

    public Solution(int id, String created, String updated, String description, int exercise_id, int user_id, double rating) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exercise_id = exercise_id;
        this.user_id = user_id;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public static final String USERS_SOLUTION = "SELECT solution.id, solution.description, solution.users_id FROM solution\n" +
            "left JOIN users ON solution.users_id = users.id\n" +
            "where users.id = ?";
    public static final String ALL_SOLUTIONS = "SELECT solution.id, solution.exercise_id, solution.description, solution.created FROM solution where exercise_id = ? order by created asc;";

    public static final String AVERAGE_RATING_BY_USER = "SELECT AVG(rating) FROM solution where users_id = ?;";

    public static final String AVERAGE_RATING_BY_EXERCISE = "SELECT AVG(rating) FROM solution where exercise_id = ?;";

    public static List<Solution> findAllByUserId(int userId) {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(USERS_SOLUTION);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Solution> solutionList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                int userIdS = resultSet.getInt("users_id");
                Solution solution = new Solution(id, description, userIdS);
                solutionList.add(solution);
            }
            return solutionList;
        } catch (SQLException e) {
            System.out.println("Nie można znaleść rozwiązań.");
        }
        return null;
    }

    public static List<Solution> findAllByExerciseId(int exerciseId) {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(ALL_SOLUTIONS);
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            List<Solution> solutions = new ArrayList<>();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setDescription(resultSet.getString("description"));
                solution.setCreated(resultSet.getString("created"));
                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) {
            System.out.println("Nie można znaleść rozwiązań.");
        }
        return null;
    }

    public static void averageRatingByUser(int userId) {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(AVERAGE_RATING_BY_USER);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                double averageByUser = resultSet.getDouble("AVG(rating)");
                System.out.println("Średnia ocena rozwiązań użytkownika o ID: " + userId + " równa się: " + averageByUser);
            }
        } catch (SQLException e) {
            System.out.println("Nie można znaleść rozwiązań.");
        }
    }

    public static void averageRatingByExercise(int exerciseId) {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(AVERAGE_RATING_BY_EXERCISE);
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                double averageByExercise = resultSet.getDouble("AVG(rating)");
                System.out.println("Średnia ocena rozwiązań dla zadania z ID: " + exerciseId + " równa się: " + averageByExercise);
            }
        } catch (SQLException e) {
            System.out.println("Nie można znaleść rozwiązań.");
        }
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
