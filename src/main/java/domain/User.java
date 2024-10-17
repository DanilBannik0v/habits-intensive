package domain;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class User {
    private static final AtomicLong globalID = new AtomicLong();
    private int id;
    private String name;
    private String email;
    private String password;
    private ArrayList<Habit> habits;

    public User(String name, String email, String password, ArrayList<Habit> habits) {
        this.id = (int) globalID.incrementAndGet();
        this.name = name;
        this.email = email;
        this.password = password;
        this.habits = habits;
    }

    public Habit getHabitByName(String name) {
        for (Habit habit : habits) {
            if (habit.getName().equals(name)) {
                return habit;
            }
        }
        return null;
    }

    public void addHabit(Habit habit) {
        habits.add(habit);
    }

    public void removeHabit(Habit habit) {
        habits.remove(habit);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Habit> getHabits() {
        return habits;
    }

    public void setHabits(ArrayList<Habit> habits) {
        this.habits = habits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(habits, user.habits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, habits);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + "********" + '\'' +
                ", habits=" + habits +
                '}';
    }
}