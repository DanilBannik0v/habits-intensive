package storage;

import domain.User;

import java.util.ArrayList;
import java.util.Objects;

public class Storage {
    private ArrayList<User> users = new ArrayList<>();

    public Storage() {}

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Objects.equals(users, storage.users);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(users);
    }
}
