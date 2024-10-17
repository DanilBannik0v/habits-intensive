package domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Habit {
    private String name;
    private String info;
    private String frequency;
    private LocalDate creationDate;
    private int streak;
    private ArrayList<LocalDate> markedDates = new ArrayList<>();

    public Habit(String name, String info, String frequency, LocalDate creationDate, int streak) {
        this.name = name;
        this.info = info;
        this.frequency = frequency;
        this.creationDate = creationDate;
        this.streak = streak;
    }

    public void addMarkedDate(LocalDate markedDate) {
        markedDates.add(markedDate);
    }

    public void removeMarkedDate(LocalDate markedDate) {
        markedDates.remove(markedDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public ArrayList<LocalDate> getMarkedDates() {
        return markedDates;
    }

    public void setMarkedDates(ArrayList<LocalDate> markedDates) {
        this.markedDates = markedDates;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", frequency='" + frequency + '\'' +
                ", creationDate=" + creationDate +
                ", streak=" + streak +
                ", markedDates=" + markedDates +
                '}';
    }
}