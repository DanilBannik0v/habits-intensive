package comparator;

import domain.Habit;

import java.util.Comparator;

public class CreationDateComparator implements Comparator<Habit> {
    public int compare(Habit h1, Habit h2) {
        return h1.getCreationDate().compareTo(h2.getCreationDate());
    }
}
