package com.voronov.controllers;

import com.voronov.entities.User;
import com.voronov.service.serviceInterfaces.UserService;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Setter
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @GetMapping("/user")
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("user");
		logger.info("invoked /user page");
        return modelAndView;
    }

    @PostMapping("/user/save")
    public String addUser(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String mail) {
        User user = new User(username, password, mail);
        userService.save(user);
		logger.info("saving user " + username + " " + mail);
        return "redirect:/user";
    }

    @GetMapping("/user/edit/{id}")
    public String updatePage(@PathVariable int id, Model model) {
        model.addAttribute(userService.findById(id));
        model.addAttribute("roles", userService.findAllRoles());
		logger.info("/user/edit/" + id);
        return "userEdit";
    }

    @PostMapping("user/edit/updateUser")
	public String update(@RequestParam String username,
						 @RequestParam String mail,
						 @RequestParam(required=false) Boolean active,
						 @RequestParam String role) {
		userService.update(username, mail, active, role);
		logger.info("updating user " + username + " " + mail + " user is active:" + active + " " + role);
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
		userService.save(user);
		String referer = request.getHeader("Referer");
		logger.info("performing user registration " + username + mail);
		return "redirect:/login";
	}
}
