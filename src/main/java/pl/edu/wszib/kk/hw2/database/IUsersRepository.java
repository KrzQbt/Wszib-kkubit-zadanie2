package pl.edu.wszib.kk.hw2.database;


import pl.edu.wszib.kk.hw2.model.User;

public interface IUsersRepository {
    User authenticate(User user);
    boolean register(User user);
}
