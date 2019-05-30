package zadanie_1.app;

import zadanie_1.group.Group;
import zadanie_1.group.GroupDao;

import java.util.Scanner;

public class GroupApp {
    public static void main(String[] args) {


        GroupDao groupDao = new GroupDao();

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
            switch (line) {
                case "add":
                    addingGroup(scanner, groupDao);
                    break;
                case "edit":
                    editGroup(scanner, groupDao);
                    break;
                case "delete":
                    deleteGroup(scanner, groupDao);
                    break;
                default:
                    System.out.println("Dokonałeś niepoprawnego wyboru!");
            }
            System.out.println("Wybierz jedną z opcji:\n" +
                    "add – dodanie grupy,\n" +
                    "edit – edycja grupy,\n" +
                    "delete – usunięcie grupy,\n" +
                    "quit – zakończenie programu.");
            scanner.nextLine();
        }
        scanner.close();
    }

    private static void deleteGroup(Scanner scanner, GroupDao groupDao) {
        System.out.println("Podaj id grupy do usunięcia: ");
        int id = scanner.nextInt();
        groupDao.delete(id);
    }

    private static void editGroup(Scanner scanner, GroupDao groupDao) {
        System.out.println("Podaj name: ");
        String name = scanner.nextLine();
        System.out.println("Podaj id grupy do zmiany: ");
        int id = scanner.nextInt();
        Group group = new Group(name);
        group.setId(id);
        groupDao.update(group);
    }

    private static void addingGroup(Scanner scanner, GroupDao groupDao) {
        System.out.println("Podaj name: ");
        String name = scanner.nextLine();
        Group group = new Group(name);
        groupDao.create(group);
    }
}