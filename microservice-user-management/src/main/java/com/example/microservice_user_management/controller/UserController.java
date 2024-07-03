package com.example.microservice_user_management.controller;

import com.example.microservice_user_management.model.Role;
import com.example.microservice_user_management.model.User;
import com.example.microservice_user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/service/registration")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            //status Code : 403
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setRole(Role.USER);
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/service/login")
    public ResponseEntity<?> getUser(Principal principal) {
        // Principal principal = httpServletRequest.getUserPrincipal();
        if (principal == null || principal.getName() == null) {
            //This means; logout will be successful, login?logout
            return new ResponseEntity<>(HttpStatus.OK);
        }
        // username = principal.getName();
        return ResponseEntity.ok(userService.findByUsername(principal.getName()));
    }

    @GetMapping("/service/names")
    public ResponseEntity<?> getNameOfUsers(@RequestBody List<Long> idList) {
        return ResponseEntity.ok(userService.findByIdList(idList));
    }

    @GetMapping("/service/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("It is working...");
    }


}
