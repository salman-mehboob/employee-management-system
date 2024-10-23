package com.econception.employemanagement.service;

import com.econception.employemanagement.domain.Role;
import com.econception.employemanagement.domain.User;
import com.econception.employemanagement.repository.RoleRepository;
import com.econception.employemanagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private RoleRepository roleRepository;


    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    SecurityService securityService;

    @Autowired
    private UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }


    public User save(User user) {
        log.debug("Request to save user : {}", user);
        return userRepository.save(user);
    }

    public List<User> findAll() {
        log.debug("Request to get all user");
        return userRepository.findAll();

    }
//    public User findUserByEmail(String email) {
//        return userRepository.findByWorkEmail(email);
//    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    public User findUserByCurrentUserName() {
        String currentUserName = securityService.getUsername();
        if(currentUserName != null)
            return userRepository.findByUsername(currentUserName);
        return null;
    }
    public User saveUser(User user) {


        return userRepository.save(user);
    }

    public void delete(Long id) {
        log.debug("Request to delete user : {}", id);
        userRepository.deleteById(id);
    }

    public void deleteAll() {
        int id = 1;
        userRepository.deleteById((long) id);
    }
}
