package zadanie_1.user;

import zadanie_1.db_connection.ConnectionGen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY =
            "SELECT * FROM users where id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET username = ?, email = ?, password = ? where id = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY =
            "SELECT * FROM users";

    public User create(User user) {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Nie można utworzyć użytkownika.");
            return null;
        }
    }

    public User read(int userId) {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Nie można odczytać użytkownika o id: " + userId);
        }
        return null;
    }

    public void update(User user) {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nie można uaktualnić użytkownika o id: " + user.getId());
        }
    }

    public void delete(int userId) {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nie można usunąć użytkownika o id: " + userId);
        }
    }

    public List<User> allUsers() {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("username");
                String email = resultSet.getString("email");
                user.setPassword(resultSet.getString("password"));
                user.setId(id);
                user.setUserName(userName);
                user.setEmail(email);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println("Nie odnaleziono użytkoników.");
        }
        return null;
    }


}