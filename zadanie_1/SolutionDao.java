package zadanie_1;

import java.sql.*;

public class SolutionDao {
    private static final String CREATE_SOLUTION_QUERY =
            "INSERT INTO solution(created, updated, description, rating) VALUES (?, ?, ?, ?)";
    private static final String READ_SOLUTION_QUERY =
            "SELECT * FROM solution where id = ?";
    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE solution SET created = ?, updated = ?, description = ?, rating where id = ?";
    private static final String DELETE_SOLUTION_QUERY =
            "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_SOLUTIONS_QUERY =
            "SELECT * FROM solution";

    public Solution create(Solution solution) {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, solution.getCreated());
            statement.setString(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setDouble(4,solution.getRating());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution read(int solId) {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, solId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUser_id(resultSet.getInt("user_id"));
                solution.setRating(resultSet.getDouble("rating"));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Solution solution) {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setString(1, solution.getCreated());
            statement.setString(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getId());
            statement.setDouble(5,solution.getRating());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int solId) {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, solId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void allSolutions() {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                int id = resultSet.getInt("id");
                String created = resultSet.getString("created");
                String updated = resultSet.getString("updated");
                String description = resultSet.getString("description");
                int exerciseId = resultSet.getInt("exercise_id");
                int usersId = resultSet.getInt("users_id");
                double rating = resultSet.getDouble("rating");
                System.out.printf("Id: %d, created: %s, updated: %s, description: %s, Exercise id: %d, User id: %d, rating: %d", id, created, updated, description, exerciseId, usersId, rating);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
