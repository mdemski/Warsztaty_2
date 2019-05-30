package zadanie_1.app;

import zadanie_1.exercise.Exercise;
import zadanie_1.exercise.ExerciseDao;
import zadanie_1.group.Group;
import zadanie_1.group.GroupDao;
import zadanie_1.solution.Solution;
import zadanie_1.solution.SolutionDao;
import zadanie_1.user.User;
import zadanie_1.user.UserDao;

import java.time.LocalDate;
import java.util.Scanner;

public class ExerciseApp {
    public static void main(String[] args) {
        exerciseOperations();
    }

    private static void exerciseOperations() {
        Scanner scanner = new Scanner(System.in);
        ExerciseDao exerciseDao = new ExerciseDao();
        String line = "";
        while (!(line = scanner.nextLine()).equals("quit")) {
            exerciseDao.allExercises();
            System.out.println("Wybierz jedną z opcji:\n" +
                    "add – dodanie zadania,\n" +
                    "edit – edycja zadania,\n" +
                    "delete – usunięcie zadania,\n" +
                    "quit – zakończenie programu.");
            switch (line) {
                case "add":
                    addingExercise(scanner, exerciseDao);
                    break;
                case "edit":
                    editExercise(scanner, exerciseDao);
                    break;
                case "delete":
                    deleteExercise(scanner, exerciseDao);
                    break;
                default:
                    System.out.println("Dokonałeś niepoprawnego wyboru!");
            }
            System.out.println("Wybierz jedną z opcji:\n" +
                    "add – dodanie zadania,\n" +
                    "edit – edycja zadania,\n" +
                    "delete – usunięcie zadania,\n" +
                    "quit – zakończenie programu.");
            scanner.nextLine();
        }
        scanner.close();
    }

    private static void deleteExercise(Scanner scanner, ExerciseDao exerciseDao) {
        System.out.println("Podaj id zadania do usunięcia: ");
        int id = scanner.nextInt();
        exerciseDao.delete(id);
    }

    private static void editExercise(Scanner scanner, ExerciseDao exerciseDao) {
        System.out.println("Podaj title: ");
        String title = scanner.nextLine();
        System.out.println("Podaj description: ");
        String description = scanner.nextLine();
        System.out.println("Podaj id zadania do zmiany: ");
        int id = scanner.nextInt();
        Exercise exercise = new Exercise(title, description);
        exercise.setId(id);
        exerciseDao.update(exercise);
    }

    private static void addingExercise(Scanner scanner, ExerciseDao exerciseDao) {
        System.out.println("Podaj title: ");
        String title = scanner.nextLine();
        System.out.println("Podaj description: ");
        String description = scanner.nextLine();
        Exercise exercise = new Exercise(title, description);
        exerciseDao.create(exercise);
    }
}