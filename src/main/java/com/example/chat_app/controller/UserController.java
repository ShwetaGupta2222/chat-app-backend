package com.example.chat_app.controller;
import com.example.chat_app.Model.LoginDetails;
import com.example.chat_app.Model.User;
import com.example.chat_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginDetails loginDetails){;
        return userService.loginUser(loginDetails.getName(),loginDetails.getPassword());
    }
    @GetMapping("/search")
    public ResponseEntity<List<User>> getUsersWithNameStartingWith(@RequestParam String str){
        List<User> users = userService.getUsersWithNameStartingWith(str);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestParam String newPassword,String username){
        return userService.changePassword(newPassword,username  );
    }

}
