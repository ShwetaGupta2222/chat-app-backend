package com.example.chat_app.service;
import com.example.chat_app.Model.User;
import com.example.chat_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public ResponseEntity<User> loginUser(String name, String password) {
        User user = userRepository.findById(name).orElse(null);
        if(user == null){
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if(!password.equals(user.getPassword())){
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }
    public ResponseEntity<String> registerUser(User user) {
        if (userRepository.existsById(user.getName())) {
            return new ResponseEntity<>("Email already registered",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
    public List<User> getUsersWithNameStartingWith(String str) {
        return userRepository.findByNameStartingWithIgnoreCase(str);
    }
    public ResponseEntity<String> changePassword(String newPassword,String username){
        User user = userRepository.findById(username).orElse(null);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setPassword(newPassword);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public List<User> getAllUser(){
          return userRepository.findAll();
    }
    public void deleteAllUser(){
        userRepository.deleteAll();
    }

}
