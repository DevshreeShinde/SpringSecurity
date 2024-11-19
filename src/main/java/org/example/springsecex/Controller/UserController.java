package org.example.springsecex.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.springsecex.model.User;
import org.example.springsecex.repository.UserRepository;
import org.example.springsecex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String Welcome(HttpServletRequest request) {
       return "Hello World!" + request.getSession().getId(); //get sessionId
    }

    @GetMapping("/csrfToken")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName()); //getCsrfToken
    }

    @PostMapping("/register")
    public User login(@RequestBody User user) {
       return userService.saveUser(user);
    }
    @GetMapping("getUsers")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
}
