package zadanie_1.group;

import zadanie_1.db_connection.ConnectionGen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {

    private static final String CREATE_GROUP_QUERY =
            "INSERT INTO user_group(name) VALUES (?)";
    private static final String READ_GROUP_QUERY =
            "SELECT * FROM user_group where id = ?";
    private static final String UPDATE_GROUP_QUERY =
            "UPDATE user_group SET name = ? where id = ?";
    private static final String DELETE_GROUP_QUERY =
            "DELETE FROM user_group WHERE id = ?";
    private static final String FIND_ALL_GROUPS_QUERY =
            "SELECT * FROM user_group";


    public Group create(Group group) {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, group.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                group.setId(resultSet.getInt(1));
            }
            return group;
        } catch (SQLException e) {
            System.out.println("Nie można utworzyć grupy.");
            return null;
        }
    }

    public Group read(int grId) {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_GROUP_QUERY);
            statement.setInt(1, grId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                return group;
            }
        } catch (SQLException e) {
            System.out.println("Nie można odczytać grupy o id: " + grId);
        }
        return null;
    }

    public void update(Group group) {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_GROUP_QUERY);
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nie można uaktualnić grupy o id: " + group.getId());
        }
    }

    public void delete(int grId) {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_GROUP_QUERY);
            statement.setInt(1, grId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nie można usunąć grupy o id: " + grId);
        }
    }

    public List<Group> allGroups() {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_GROUPS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            List<Group> groups = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Group group = new Group(id, name);
                groups.add(group);
            }
            return groups;
        } catch (SQLException e) {
            System.out.println("Nie odnaleziono grup.");
        }
        return null;
    }

}
