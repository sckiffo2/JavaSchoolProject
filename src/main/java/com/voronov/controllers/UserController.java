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

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        User user = new User(username, password, mail);
        userService.addUser(user);
        return "redirect:/user";
    }

    @GetMapping("/user/edit/{id}")
    public String updatePage(@PathVariable int id, Model model) {
        model.addAttribute(userService.findById(id));
        model.addAttribute("roles", userService.findAllRoles());
        return "userEdit";
    }

	@GetMapping("/user/editRole/{id}")
	public String updateRolePage(@PathVariable int id, Model model) {
		model.addAttribute(userService.findById(id));
		model.addAttribute("roles", userService.findAllRoles());
		return "userEditRole";
	}

    @PostMapping("user/edit/updateUser")
    public String update(@RequestParam String username,
						 @RequestParam String mail,
                         @RequestParam(required=false) Boolean active) {
        User user = userService.findByName(username);
		user.setUsername(username);
		user.setActive(active);
        userService.update(user);
        return "redirect:/user";
    }

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registrationPage(Model model) {

		return "registration";
	}

	@PostMapping("/registerUser")
	public String save(@RequestParam String username,
					   @RequestParam String password,
					   @RequestParam String mail, Model model,
					   HttpServletRequest request) {
		User user = new User(username, password, mail);
		userService.addUser(user);
		String referer = request.getHeader("Referer");
		return "redirect:/login";
	}
}
