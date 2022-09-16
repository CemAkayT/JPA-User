package cem.controller;


import cem.model.User;
import cem.service.IUserService;
import cem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Set;

@RestController // vi arbejder med Rest
public class UserController {

    private IUserService iUserService; // vi skal instantiere et objekt her vi kan kalde

    public UserController(IUserService iUserService) { // og lave en konstruktor til linje 9
        this.iUserService = iUserService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<Set<User>> getUsers() {
        return new ResponseEntity<>(iUserService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> create(@RequestBody User user) {
        iUserService.save(user);
        return new ResponseEntity<>("User: " + user.getName() + " created.", HttpStatus.OK);
    }


    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<Set<User>> deleteUserById(@PathVariable("id") Long id) {
        iUserService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete") // bruger den ikke
    public void deleteUser(@RequestBody User user) {
        iUserService.delete(user);
    }

    @GetMapping("existsById/{id}")
    public boolean exists(@PathVariable("id") Long id) {
        boolean found = false;
        if (!iUserService.existsById(id)){
            return found;
        } else {
            return !found;
        }

    }

    @PutMapping("/update")
    public ResponseEntity<Set<User>> update(Long id, @RequestBody User user) {
        iUserService.findById(id);
        iUserService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

   /* @PutMapping("/update/{id}/{newName}/{newAge}")
    public ResponseEntity<Set<User>> update(@PathVariable("id") Long id, @PathVariable("newName") String newName, @PathVariable("newAge") int newAge, User user) {
        userService.findById(id);
        user.setName(newName);
        user.setAge(newAge);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/
}
