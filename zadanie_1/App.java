package zadanie_1;


import com.sun.deploy.util.ArrayUtil;

import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Solution solution = new Solution("2019-04-01", "2019-04-04", "asf");

        GroupDao groupDao = new GroupDao();

    }

    private static void userOperations() {
        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDao();
        String line = "";
        while (!(line = scanner.nextLine()).equals("quit")) {
            userDao.allUsers();
            System.out.println("Wybierz jedną z opcji:\n" +
                    "add – dodanie użytkownika,\n" +
                    "edit – edycja użytkownika,\n" +
                    "delete – usunięcie użytkownika,\n" +
                    "quit – zakończenie programu.");
            if (line.equals("add")) {
                System.out.println("Podaj username: ");
                String userName = scanner.nextLine();
                System.out.println("Podaj email: ");
                String email = scanner.nextLine();
                System.out.println("Podaj password: ");
                String password = scanner.nextLine();
                User user = new User(userName, email, password);
                userDao.create(user);
            } else if (line.equals("edit")) {
                System.out.println("Podaj username: ");
                String userName = scanner.nextLine();
                System.out.println("Podaj email: ");
                String email = scanner.nextLine();
                System.out.println("Podaj password: ");
                String password = scanner.nextLine();
                System.out.println("Podaj id użytkownika do zmiany: ");
                int id = scanner.nextInt();
                User user = new User(userName, email, password);
                user.setId(id);
                userDao.update(user);
            } else if (line.equals("delete")) {
                System.out.println("Podaj id użytkownika do usunięcia: ");
                int id = scanner.nextInt();
                userDao.delete(id);
            } else {
                System.out.println("Dokonałeś niepoprawnego wyboru!");
            }
            System.out.println("Wybierz jedną z opcji:\n" +
                    "add – dodanie użytkownika,\n" +
                    "edit – edycja użytkownika,\n" +
                    "delete – usunięcie użytkownika,\n" +
                    "quit – zakończenie programu.");
            scanner.nextLine();
        }
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
            if (line.equals("add")) {
                System.out.println("Podaj title: ");
                String title = scanner.nextLine();
                System.out.println("Podaj description: ");
                String description = scanner.nextLine();
                Exercise exercise = new Exercise(title, description);
                exerciseDao.create(exercise);
            } else if (line.equals("edit")) {
                System.out.println("Podaj title: ");
                String title = scanner.nextLine();
                System.out.println("Podaj description: ");
                String description = scanner.nextLine();
                System.out.println("Podaj id zadania do zmiany: ");
                int id = scanner.nextInt();
                Exercise exercise = new Exercise(title, description);
                exercise.setId(id);
                exerciseDao.update(exercise);
            } else if (line.equals("delete")) {
                System.out.println("Podaj id zadania do usunięcia: ");
                int id = scanner.nextInt();
                exerciseDao.delete(id);
            } else {
                System.out.println("Dokonałeś niepoprawnego wyboru!");
            }
            System.out.println("Wybierz jedną z opcji:\n" +
                    "add – dodanie zadania,\n" +
                    "edit – edycja zadania,\n" +
                    "delete – usunięcie zadania,\n" +
                    "quit – zakończenie programu.");
            scanner.nextLine();
        }
    }

    private static void groupOperations() {
        Scanner scanner = new Scanner(System.in);
        GroupDao groupDao = new GroupDao();
        String line = "";
        while (!(line = scanner.nextLine()).equals("quit")) {
            groupDao.allGroups();
            System.out.println("Wybierz jedną z opcji:\n" +
                    "add – dodanie grupy,\n" +
                    "edit – edycja grupy,\n" +
                    "delete – usunięcie grupy,\n" +
                    "quit – zakończenie programu.");
            line = scanner.nextLine();
            if (line.equals("add")) {
                System.out.println("Podaj name: ");
                String name = scanner.nextLine();
                Group group = new Group(name);
                groupDao.create(group);
            } else if (line.equals("edit")) {
                System.out.println("Podaj name: ");
                String name = scanner.nextLine();
                System.out.println("Podaj id grupy do zmiany: ");
                int id = scanner.nextInt();
                Group group = new Group(name);
                group.setId(id);
                groupDao.update(group);
            } else if (line.equals("delete")) {
                System.out.println("Podaj id grupy do usunięcia: ");
                int id = scanner.nextInt();
                groupDao.delete(id);
            } else {
                System.out.println("Dokonałeś niepoprawnego wyboru!");
            }
            System.out.println("Wybierz jedną z opcji:\n" +
                    "add – dodanie grupy,\n" +
                    "edit – edycja grupy,\n" +
                    "delete – usunięcie grupy,\n" +
                    "quit – zakończenie programu.");
            scanner.nextLine();
        }
    }

    private static void solutionOperations() {
        Scanner scanner = new Scanner(System.in);
        SolutionDao solutionDao = new SolutionDao();
        String line = "";
        System.out.println("Wybierz jedną z opcji:\n" +
                "add – przypisywanie zadań do użytkowników,\n" +
                "view – przeglądanie rozwiązań danego użytkownika,\n" +
                "quit – zakończenie programu.");
        while (!(line = scanner.nextLine()).equals("quit")) {
            if (line.equals("add")) {
                UserDao userDao = new UserDao();
                userDao.allUsers();
                System.out.println("Podaj id użytkownika: ");
                int userId = scanner.nextInt();
                ExerciseDao exerciseDao = new ExerciseDao();
                exerciseDao.allExercises();
                System.out.println("Podaj id zadania: ");
                int exerciseId = scanner.nextInt();
                String created = LocalDate.now().toString();
                Solution solution = new Solution(created, null, null);
                solutionDao.create(solution);
            } else if (line.equals("view")) {
                System.out.println("Podaj id użytkowania: ");
                int userId = scanner.nextInt();
                Solution solution = new Solution();
                System.out.println(solution.findAllByUserId(userId));
            } else {
                System.out.println("Dokonałeś niepoprawnego wyboru!");
            }
            System.out.println("Wybierz jedną z opcji:\n" +
                    "add – przypisywanie zadań do użytkowników,\n" +
                    "view – przeglądanie rozwiązań danego użytkownika,\n" +
                    "quit – zakończenie programu.");
            scanner.nextLine();
        }
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
            if (line.equals("add")) {
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
            } else if (line.equals("view")) {
                solutionDao.allSolutions();
            } else {
                System.out.println("Wybrałeś niepoprawną opcję.");
            }
            System.out.println("Wybierz jedną z opcji:\n" +
                    "add – dodawanie rozwiązania,\n" +
                    "view – przeglądanie swoich rozwiązań,\n" +
                    "quit – zakończenie programu.");
        }
    }
}