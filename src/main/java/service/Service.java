package service;


import comparator.CreationDateComparator;
import comparator.FrequencyComparator;
import domain.Habit;
import domain.User;
import storage.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Service {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);

    public Service() {
    }

    public static void init(Storage storage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("login or registration");
        String action = scanner.nextLine();

        switch (action) {
            case "login":
                login(storage);
                break;
            case "registration":
                registration(storage);
                init(storage);
                break;
        }
    }

    public static void login(Storage storage) {
        Scanner scanner = new Scanner(System.in);
        User currentUser = null;
        System.out.println("Enter email");
        String email = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();

        for (User user : storage.getUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                currentUser = user;
            }
        }

        if (Objects.equals(email, "admin") && Objects.equals(password, "admin")) {
            System.out.println("Admin is working");
            listenToAdmin(storage);
        } else if (currentUser != null) {
            System.out.println(currentUser);
            listenToUser(currentUser, storage);
        } else {
            System.out.println("User not found");
            init(storage);
        }
    }

    public static void registration(Storage storage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username");
        String name = scanner.nextLine();
        System.out.println("Enter email");
        String email = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        storage.addUser(new User(name, email, password, new ArrayList<>()));
        System.out.println("User added");
    }

    public static void listenToAdmin(Storage storage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What to do?");
        System.out.println("----------------");
        System.out.println("\"get users\"\n" +
                "\"get user habits\"\n" +
                "\"delete user\"\n" +
                "\"exit\"\n");

        String action = scanner.nextLine();
        switch (action) {
            case "get users":
                getUsers(storage);
                listenToAdmin(storage);
                break;
            case "get user habits":
                getUserHabits(storage);
                listenToAdmin(storage);
                break;
            case "delete user":
                deleteUser(storage);
                listenToAdmin(storage);
                break;
            case "exit":
                init(storage);
                break;
        }
    }

    public static void getUsers(Storage storage) {
        storage.getUsers().forEach(System.out::println);
    }

    public static void getUserHabits(Storage storage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username");
        String name = scanner.nextLine();
        for (User user : storage.getUsers()) {
            if (Objects.equals(user.getName(), name)) {
                user.getHabits().forEach(System.out::println);
            }
        }
    }

    public static void deleteUser(Storage storage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username");
        String name = scanner.nextLine();
        storage.getUsers().stream()
                .filter(user -> Objects.equals(user.getName(), name))
                .findFirst()
                .ifPresent(storage::removeUser);
        System.out.println("user deleted");
    }

    public static void listenToUser(User user, Storage storage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What to do?");
        System.out.println("----------------");
        System.out.println("\"create a habit\"\n" +
                "\"edit a habit\"\n" +
                "\"delete a habit\"\n" +
                "\"get habits by creation date\"\n" +
                "\"get habits by frequency\"\n" +
                "\"mark a habit\"\n" +
                "\"get habit statistics\"\n" +
                "\"get completion percentage\"\n" +
                "\"edit profile\"\n" +
                "\"delete profile\"\n" +
                "\"exit\"\n");

        String action = scanner.nextLine();
        switch (action) {
            case "create a habit":
                createHabit(user);
                listenToUser(user, storage);
                break;
            case "edit a habit":
                editHabit(user);
                listenToUser(user, storage);
                break;
            case "delete a habit":
                deleteHabit(user);
                listenToUser(user, storage);
                break;
            case "get habits by creation date":
                getHabitsByDate(user);
                listenToUser(user, storage);
                break;
            case "get habits by frequency":
                getHabitsByFrequency(user);
                listenToUser(user, storage);
                break;
            case "mark a habit":
                markHabit(user);
                listenToUser(user, storage);
                break;
            case "get habit statistics":
                getHabitStatistics(user);
                listenToUser(user, storage);
                break;
            case "get completion percentage":
                getCompletionPercentage(user);
                listenToUser(user, storage);
                break;
            case "edit profile":
                editProfile(user);
                listenToUser(user, storage);
                break;
            case "delete profile":
                storage.removeUser(user);
                init(storage);
                break;
            case "exit":
                init(storage);
                break;
        }
    }

    public static void createHabit(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter description");
        String info = scanner.nextLine();
        System.out.println("Enter frequency");
        String frequency = scanner.nextLine();
        user.addHabit(new Habit(name, info, frequency, LocalDate.now(), 0));
        System.out.println("habit added");
    }

    public static void editHabit(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter description");
        String info = scanner.nextLine();
        for (Habit habit : user.getHabits()) {
            if (habit.getName().equals(name)) {
                habit.setInfo(info);
                System.out.println("habit edited");
            }
        }
    }

    public static void deleteHabit(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name");
        String name = scanner.nextLine();
        if (user.getHabits().removeIf(habit -> habit.getName().equals(name))) {
            System.out.println("habit deleted");
        }
    }

    public static void editProfile(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter email");
        String email = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        System.out.println("profile edited");
    }

    public static void getHabitsByDate(User user) {
        CreationDateComparator creationDateComparator = new CreationDateComparator();
        user.getHabits().sort(creationDateComparator);
        for (Habit habit : user.getHabits()) {
            System.out.println(habit);
        }
    }

    public static void getHabitsByFrequency(User user) {
        FrequencyComparator frequencyComparator = new FrequencyComparator();
        user.getHabits().sort(frequencyComparator);
        for (Habit habit : user.getHabits()) {
            System.out.println(habit);
        }
    }

    public static void markHabit(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name");
        String name = scanner.nextLine();
        for (Habit habit : user.getHabits()) {
            if (habit.getName().equals(name)) {
                habit.setStreak(habit.getStreak()+1);
                habit.addMarkedDate(LocalDate.now());
            }
        }
    }

    public static void getHabitStatistics(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter start date");
        String date1 = scanner.nextLine();
        LocalDate start = LocalDate.parse(date1, FORMATTER);
        System.out.println("Enter end date");
        String date2 = scanner.nextLine();
        LocalDate end = LocalDate.parse(date2, FORMATTER);

        int counter = 0;
        for (LocalDate markedDate: user.getHabitByName(name).getMarkedDates()) {
            if (isWithinRange(markedDate, start, end)) {
                counter++;
            }
        }
        System.out.printf("During this period the habit was performed %s times\n", counter);
    }

    public static void getCompletionPercentage(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter start date");
        String date1 = scanner.nextLine();
        LocalDate start = LocalDate.parse(date1, FORMATTER);
        System.out.println("Enter end date");
        String date2 = scanner.nextLine();
        LocalDate end = LocalDate.parse(date2, FORMATTER);

        int numberOfHabits = user.getHabits().size();
        int days = (int) start.until(end, ChronoUnit.DAYS);
        int maximumNumberOfCompletedHabits = numberOfHabits * days;
        int completedHabits = 0;
        for (Habit habit: user.getHabits()) {
            for (LocalDate markedDate: habit.getMarkedDates()) {
                if (isWithinRange(markedDate, start, end)) {
                    completedHabits++;
                }
            }
        }
        long completionPercentage = completedHabits * 100L / maximumNumberOfCompletedHabits;
        System.out.printf("The percentage of successful implementation of habits over a certain period is %s\n", completionPercentage);
    }

    public static boolean isWithinRange(LocalDate testDate, LocalDate start, LocalDate end) {
        return !(testDate.isBefore(start) || testDate.isAfter(end));
    }
}
