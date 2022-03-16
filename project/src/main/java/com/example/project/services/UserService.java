package com.example.project.services;

import com.example.project.entities.Question;
import com.example.project.entities.User;
import com.example.project.entities.Vote;
import com.example.project.repositories.UserRepository;
import com.example.project.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("userService")
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private VoteRepository voteRepository;

    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    public Long saveUser(User user) throws Exception {
        if(checkDuplicate(user.getUsername())){
            throw new Exception("Username already exists");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUserEnabled(true);
        User userDB = userRepository.save(user);
        Vote newLikeVote = new Vote(true, userDB);
        this.voteRepository.save(newLikeVote);
        Vote newDislikeVote = new Vote(false, userDB);
        this.voteRepository.save(newDislikeVote);

        return userDB.getUserId();
    }

    public Long saveUser(Long id, User user) throws Exception {
        User user1 = this.userRepository.findById(id).get();
        if(user1 == null){
            throw new Exception("User" + id+" not found");
        }

        if(user1.getUsername().compareTo(user.getUsername()) != 0){
            if(checkDuplicate(user.getUsername())){
                throw new Exception("Username already exists");
            }
        }

        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());

        return userRepository.save(user1).getUserId();
    }



    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return findUserByUserName(auth.getName());

    }

    public boolean checkDuplicate(String userName){
        User user = this.userRepository.findByUsername(userName);
        if(user != null){
            return true;
        }
        return false;
    }

    public User saveEdit(Long id, User user) throws Exception {
        User userDB = this.findById(id);
        if(userDB == null){
            throw new Exception("User "+id+" not found");
        }
        userDB.setEmail(user.getEmail());
        if(user.getUserEnabled()!=null){
            userDB.setUserEnabled(user.getUserEnabled());
        }
        userDB.setUsername(user.getUsername());
        return userRepository.save(userDB);
    }

    public List<User> listAll() {
        return this.userRepository.findAll();
    }
}
