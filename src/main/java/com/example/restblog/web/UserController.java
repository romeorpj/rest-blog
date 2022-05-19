package com.example.restblog.web;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UserController {
    ArrayList<User> newUsers = new ArrayList<>();

    @GetMapping
    public ArrayList<User> getAll(){
        if (newUsers.size() <= 3) {
            newUsers.add(new User(1, "sasuke", "user@aol.com", "apassword"));
            newUsers.add(new User(2, "kotaro", "tonosaman.com", "MrLivesAlone"));
        }
        return newUsers;
    }


    @GetMapping("/username")
    public User getByUsername(@RequestParam String username){
        for (User user: getAll()) {
            if (Objects.equals(user.getUsername(), username)){
                return user;
            }
        }
        return new User();
    }

    @GetMapping("/email")
    public User getByEmail(@RequestParam String email){
        for (User user: getAll()) {
            if (Objects.equals(user.getEmail(), email)){
                return user;
            }
        }
        return new User();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable long id){
        for (User user: getAll()) {
            if (Objects.equals(user.getId(), id)){
                return user;
            }
        }
        return new User();
    }

    @PostMapping
    public void create(@RequestBody User user){
        newUsers.add(user);
        System.out.println(user);
    }

    @PutMapping("{id}")
    public void  update(@PathVariable long id,  @RequestBody User user){
        for (User oldUser: getAll()) {
            if (Objects.equals(oldUser.getId(), id)){
                System.out.println(oldUser);
                user.setId(id);
                oldUser.setUsername(user.getUsername());
                oldUser.setEmail(user.getEmail());
                System.out.println(user);
            }
        }
    }
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id){
        System.out.println("Deleting user "+ id);
    }
    @PutMapping("{id}/updatePassword")
    public void updatePassword(   @PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword){
        User userToUpdate = getById(id);
        userToUpdate.setPassword(newPassword);
        System.out.println(userToUpdate.getPassword());
    }

}