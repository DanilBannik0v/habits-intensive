package org.example;

import domain.Habit;
import domain.User;
import storage.Storage;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import static service.Service.*;
import static org.junit.jupiter.api.Assertions.*;


public class AppTest {
    static Storage storage;
    static User user1;
    static User user2;
    private static final InputStream originalSystemIn = System.in;

    @BeforeAll
    public static void beforeAll() {
        storage = new Storage();
        user1 = new User("Jack", "email@mail.ru", "qwerty", new ArrayList<>());
        user2 = new User("Dan", "danmail@mail.ru", "12345", new ArrayList<>());
        storage.addUser(user1);
        storage.addUser(user2);
        user1.addHabit(new Habit("brush teeth", "it's important", "everyday", LocalDate.now(), 0));
        user1.addHabit(new Habit("watch movies", "it's interesting", "every week", LocalDate.now(), 0));
        user1.addHabit(new Habit("useful habit", "delete that", "every week", LocalDate.now(), 0));
        user2.addHabit(new Habit("exercises", "it's useful", "everyday", LocalDate.now(), 0));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(originalSystemIn);
    }


    public void getUsersTest() {
        getUsers(storage);
    }

    @Test
    public void getUserHabitsTest() {
        System.setIn(new ByteArrayInputStream("Jack".getBytes()));
        getUserHabits(storage);
    }

    @Test
    public void deleteUserTest() {
        System.setIn(new ByteArrayInputStream("Dan".getBytes()));
        deleteUser(storage);
    }

    @Test
    public void createHabitTest() {
        System.setIn(new ByteArrayInputStream("wash\nfor hygiene\neveryday\n".getBytes()));
        createHabit(user1);
    }

    @Test
    public void editHabitTest() {
        System.setIn(new ByteArrayInputStream("watch movies\nit's just cool\n".getBytes()));
        editHabit(user1);
    }

    @Test
    public void deleteHabitTest() {
        System.setIn(new ByteArrayInputStream("useful habit\n".getBytes()));
        deleteHabit(user1);
    }

    @Test
    public void editProfileTest() {
        System.setIn(new ByteArrayInputStream("Carl\nnew email\nnew password123\n".getBytes()));
        editProfile(user1);
    }

    @Test
    public void getHabitsByDateTest() {
        getHabitsByDate(user1);
    }

    @Test
    public void getHabitsByFrequencyTest() {
        getHabitsByFrequency(user1);
    }

    @Test
    public void markHabitTest() {
        System.setIn(new ByteArrayInputStream("brush teeth\n".getBytes()));
        markHabit(user1);
    }

    @Test
    public void getHabitStatisticsTest() {
        System.setIn(new ByteArrayInputStream("brush teeth\n13.10.2024\n15.10.2024".getBytes()));
        getHabitStatistics(user1);
    }

    @Test
    public void getCompletionPercentageTest() {
        System.setIn(new ByteArrayInputStream("13.10.2024\n15.10.2024\n".getBytes()));
        getCompletionPercentage(user1);
    }

    @Test
    public void isWithinRangeTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
        LocalDate localDateToCheck = LocalDate.parse("14.10.2024", formatter);
        LocalDate start = LocalDate.parse("14.10.2024", formatter);
        LocalDate end = LocalDate.parse("15.10.2024", formatter);
        assertTrue(isWithinRange(localDateToCheck, start, end));
    }
}