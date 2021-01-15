package pl.edu.wszib.kk.hw2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.kk.hw2.model.view.RegistrationModel;
import pl.edu.wszib.kk.hw2.services.IProductService;
import pl.edu.wszib.kk.hw2.services.IUserService;
import pl.edu.wszib.kk.hw2.session.SessionObject;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class CommonController {
    @Autowired
    IProductService productService;

    @Autowired IUserService userService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("products", this.productService.getAllProducts());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        return "main";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("registrationModel", new RegistrationModel());
        model.addAttribute("info", this.sessionObject.getInfo());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute RegistrationModel registrationModel) {
        Pattern regexp = Pattern.compile("[A-Za-z0-9]{5}.*");
        Matcher loginMatcher = regexp.matcher(registrationModel.getLogin());
        Matcher passMatcher = regexp.matcher(registrationModel.getPass());
        Matcher pass2Matcher = regexp.matcher(registrationModel.getPass2());

        if(!loginMatcher.matches() || !passMatcher.matches() || !pass2Matcher.matches() || !registrationModel.getPass().equals(registrationModel.getPass2())) {
            this.sessionObject.setInfo("validation error !!");
            return "redirect:/register";
        }

        if(this.userService.register(registrationModel)) {
            return "redirect:/login";
        } else {
            this.sessionObject.setInfo("login zajęty !!");
            return "redirect:/register";
        }
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return "contact";
    }
}
