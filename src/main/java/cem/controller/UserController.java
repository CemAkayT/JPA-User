package cem.controller;

import cem.model.User;
import cem.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // vi arbejder med Rest
public class UserController {

    private IUserService iUserService; // vi skal instantiere et objekt her vi kan kalde

    public UserController(IUserService iUserService) { // og lave en konstruktor til linje 9
        this.iUserService = iUserService;
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<String> read() {
        if (!iUserService.findAll().isEmpty()) {
            return new ResponseEntity<>("Users: " + iUserService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Schema is empty!", HttpStatus.OK);
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> create(@RequestBody User user) {
        iUserService.save(user);
        return new ResponseEntity<>("User: " + user.getName() + " created.", HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<String> update(Long id, @RequestBody User user) {
        if (iUserService.existsById(id)) {
            user.setId(id);
            iUserService.save(user);
            return new ResponseEntity<>("User updated:", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found:", HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        if (!iUserService.existsById(id)) {
            return new ResponseEntity<>("User not found:", HttpStatus.OK);
        } else {
            iUserService.deleteById(id);
            return new ResponseEntity<>("User deleted:", HttpStatus.OK);
        }
    }

    @GetMapping("existsById/{id}")
    public boolean exists(@PathVariable("id") Long id) {
        boolean found = false;
        if (!iUserService.existsById(id)) {
            return found;
        } else {
            return !found;
        }
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
