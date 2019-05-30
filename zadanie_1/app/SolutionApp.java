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

public class SolutionApp {
    public static void main(String[] args) {

    }

    private static void solutionOperations() {
        Scanner scanner = new Scanner(System.in);
        SolutionDao solutionDao = new SolutionDao();
        String line = "";
        Solution solution = new Solution();
        System.out.println("Wybierz jedną z opcji:\n" +
                "add – przypisywanie zadań do użytkowników,\n" +
                "view – przeglądanie rozwiązań danego użytkownika,\n" +
                "quit – zakończenie programu.");
        while (!(line = scanner.nextLine()).equals("quit")) {
            switch (line) {
                case "add":
                    solution = addSolution(scanner, solutionDao);
                    break;
                case "view":
                    viewSolutionByUserId(scanner, solution);
                    break;
                default:
                    System.out.println("Dokonałeś niepoprawnego wyboru!");
            }
            System.out.println("Wybierz jedną z opcji:\n" +
                    "add – przypisywanie zadań do użytkowników,\n" +
                    "view – przeglądanie rozwiązań danego użytkownika,\n" +
                    "quit – zakończenie programu.");
            scanner.nextLine();
        }
        scanner.close();
    }

    private static void viewSolutionByUserId(Scanner scanner, Solution solution) {
        System.out.println("Podaj id użytkowania: ");
        int userId = scanner.nextInt();
        System.out.println(solution.findAllByUserId(userId));
    }

    private static Solution addSolution(Scanner scanner, SolutionDao solutionDao) {
        Solution solution;
        UserDao userDao = new UserDao();
        userDao.allUsers();
        System.out.println("Podaj id użytkownika: ");
        int userId = scanner.nextInt();
        ExerciseDao exerciseDao = new ExerciseDao();
        exerciseDao.allExercises();
        System.out.println("Podaj id zadania: ");
        int exerciseId = scanner.nextInt();
        String created = LocalDate.now().toString();
        solution = new Solution(exerciseId, null, created);
        solutionDao.create(solution);
        return solution;
    }

    private static void addingSolutions() {
        Scanner scanner = new Scanner(System.in);
        SolutionDao solutionDao = new SolutionDao();
        String line = "";
        System.out.println("Wybierz jedną z opcji:\n" +
                "add – dodawanie rozwiązania,\n" +
                "view – przeglądanie swoich rozwiązań,\n" +
                "quit – zakończenie programu.");
        while (!(line = scanner.nextLine()).equals("quit")) {
            switch (line) {
                case "add":
                    Exercise exercise = new Exercise();
                    System.out.println(exercise.findAllExerciseWithoutSolution());
                    System.out.println("Podaj do którego zadania podajesz rozwiązanie: ");
                    int exerciseId = scanner.nextInt();
                    for (int i = 0; i < exercise.findAllExerciseWithoutSolution().size(); i++) {
                        if (exerciseId != exercise.findAllExerciseWithoutSolution().get(i).getId()) {
                            System.out.println("Numer zadania ma już rozwiązanie.");
                            break;
                        } else {
                            scanner.nextLine();
                            System.out.println("Podaj rozwiązanie: ");
                            String description = scanner.nextLine();
                            String created = LocalDate.now().toString();
                            Solution solution = new Solution(exerciseId, description, created);
                        }
                    }
                    break;
                case "view":
                    solutionDao.allSolutions();
                    break;
                default:
                    System.out.println("Wybrałeś niepoprawną opcję.");
            }
            System.out.println("Wybierz jedną z opcji:\n" +
                    "add – dodawanie rozwiązania,\n" +
                    "view – przeglądanie swoich rozwiązań,\n" +
                    "quit – zakończenie programu.");
        }
        scanner.close();
    }
}