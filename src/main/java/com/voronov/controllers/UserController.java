package com.voronov.controllers;

import com.voronov.entities.User;
import com.voronov.service.serviceInterfaces.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Setter
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @GetMapping("/user")
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @PostMapping("/user/save")
    public String addUser(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String mail) {
        User user = new User(username, password, mail, new Long(1), true);
        userService.addUser(user);
        return "redirect:/user";
    }

    @GetMapping("/user/edit/{id}")
    public String updatePage(@PathVariable int id, Model model) {
        model.addAttribute(userService.findById(id));
        return "userEdit";
    }

    @PostMapping("user/edit/updateUser")
    public String update(@RequestParam String id,
                         @RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String mail,
                         @RequestParam(required=false) Boolean active) {
        User user = userService.findById(Integer.parseInt(id));
        user.setUsername(username);
        user.setPassword(password);
        user.setMail(mail);
        if (active == null) {
            active = false;
        }
        user.setActive(active);
        userService.update(user);
        return "redirect:/user";
    }

    @GetMapping("/user/delete/{id}")
    public String delete(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/user";
    }
}
