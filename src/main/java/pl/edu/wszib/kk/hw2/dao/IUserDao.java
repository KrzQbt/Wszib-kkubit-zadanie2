package pl.edu.wszib.kk.hw2.dao;

import pl.edu.wszib.kk.hw2.model.User;


public interface IUserDao {
    User getUserByLogin(String login);
    boolean persistUser(User user);
}
