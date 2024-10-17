package comparator;

import domain.Habit;

import java.util.Comparator;

public class FrequencyComparator implements Comparator<Habit> {
    public int compare(Habit h1, Habit h2) {
        return h1.getFrequency().compareTo(h2.getFrequency());
    }
}