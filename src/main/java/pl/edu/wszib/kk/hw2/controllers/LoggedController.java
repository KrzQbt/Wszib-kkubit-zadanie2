package pl.edu.wszib.kk.hw2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.kk.hw2.model.Product;
import pl.edu.wszib.kk.hw2.model.User;
import pl.edu.wszib.kk.hw2.services.IProductService;
import pl.edu.wszib.kk.hw2.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class LoggedController {

    @Autowired
    IProductService productService;

    @Resource
    SessionObject sessionObject;

    //    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    public String
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public  String addForm(Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.USER) {
            return "redirect:/login";
        }
        Product product =new Product();
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        model.addAttribute("product",product);
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute Product product){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.USER) {
            return "redirect:/main";
        }

        this.productService.addProduct(product);
        return "redirect:/main";
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable int id, Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.USER) {
            return "redirect:/login";
        }
        this.productService.deleteProductById(id);
        return "redirect:/main";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable int id, Model model) {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.USER) {
            return "redirect:/login";
        }
        Product product = this.productService.getProductById(id);
        model.addAttribute("product",product);
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        return "edit";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute Product product){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.USER) {
            return "redirect:/main";
        }
        this.productService.updateProduct(product);
        return "redirect:/main";
    }


}
