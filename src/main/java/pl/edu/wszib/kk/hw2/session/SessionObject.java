package pl.edu.wszib.kk.hw2.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.kk.hw2.model.User;
import  pl.edu.wszib.kk.hw2.model.Product;
import pl.edu.wszib.kk.hw2.model.User;

import java.util.ArrayList;
import java.util.List;
@Component
@SessionScope
public class SessionObject {
    private User loggedUser = null;
    private String info = null;


    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
    public boolean isLogged() {
        return this.loggedUser != null;
    }

    public String getInfo() {
        String temp = this.info;
        this.info = null;
        return temp;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
