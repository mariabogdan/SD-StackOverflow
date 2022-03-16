package com.example.project.controllers;


import com.example.project.dtos.UserDTO;
import com.example.project.dtos.builders.UserBuilder;
import com.example.project.entities.Question;
import com.example.project.entities.User;
import com.example.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity Register(@RequestBody User user) throws Exception {
        try{
            Long userId = userService.saveUser(user);
            return new ResponseEntity<>(userId, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> auth(@RequestBody UserDTO loginUser) throws AuthenticationException {
        System.out.println(loginUser.getUsername());
        System.out.println(loginUser.getPassword());
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userService.getCurrentUser();
        UserDTO userDTO = UserBuilder.toUserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>("logged out", HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> userDTOs = userService.listAll().stream()
                                    .map(user -> UserBuilder.toUserDTO(user))
                                    .collect(Collectors.toList());
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity putUser(@RequestBody UserDTO userDTO, @PathVariable Long id){
        User user = UserBuilder.toEntity(userDTO);
        try{
            User editedUser = userService.saveEdit(id, user);
            return new ResponseEntity(UserBuilder.toUserDTO(editedUser), HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        try{
            userService.delete(id);
            return new ResponseEntity<>("User "+id+" deleted", HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.OK);
        }
    }


}
