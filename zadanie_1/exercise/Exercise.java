package zadanie_1.exercise;

import zadanie_1.db_connection.ConnectionGen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Exercise {
    private int id;
    private String title;
    private String description;

    public Exercise(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Exercise() {

    }

    public Exercise(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static final String EXERCISES_WITHOUT_SOLUTIONS = "SELECT exercise.id, exercise.title, exercise.description, solution.exercise_id from exercise left JOIN solution on solution.exercise_id = exercise.id where solution.exercise_id is null;";

    public static List<Exercise> findAllExerciseWithoutSolution() {
        try (Connection conn = ConnectionGen.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(EXERCISES_WITHOUT_SOLUTIONS);
            ResultSet resultSet = statement.executeQuery();
            List<Exercise> exercises = new ArrayList<>();
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                exercises.add(exercise);
            }
            return exercises;
        } catch (SQLException e) {
            System.out.println("Nie znaleziono zadań bez rozwiązań");;
        }
        return null;
    }

    @Override
    public String toString() {
        return "id= " + id + ", title= " + title + ", description= " + description + "\n";
    }
}
