package org.example.springsecex.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/")
    public String Welcome(HttpServletRequest request) {
       return "Hello World!" + request.getSession().getId(); //get sessionId
    }

    @GetMapping("/csrfToken")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName()); //getCsrfToken
    }

}
