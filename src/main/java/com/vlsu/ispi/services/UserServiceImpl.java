package com.vlsu.ispi.services;

import com.vlsu.ispi.models.Role;
import com.vlsu.ispi.models.User;
import com.vlsu.ispi.repositories.RoleRepository;
import com.vlsu.ispi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public User findByUsername(String username) {
        return null;
    }
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1));
        user.setRoles(roles);
        userRepository.save(user);
    }
//
//    public User getCurrentAuthUser() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return findByUsername(auth.getName());
//    }
//
//    public String modifyRoles(CheckRoles checkRoles){
//        String lists = "";
//        List<Integer> admins = checkRoles.getAdmRoles();
//        List<Integer> teachers = checkRoles.getTeachRoles();
//        List<Integer> students = checkRoles.getStuRoles();
//        List<List<Integer>> users = new ArrayList<>();
//        users.add(admins);
//        users.add(teachers);
//        users.add(students);
//        int number=0;
//        for (List<Integer> userVar: users){
//
////            if (userVar.equals(admins)) number = 3; else
////            if (userVar.equals(students)) number = 1; else
////            if (userVar.equals(teachers)) number = 2;
//
//            if (number == 1) userVar = students; else
//            if (number == 2) userVar = teachers; else
//            if (number == 3) userVar = admins;
//            lists+= "</br>" + number + " ";
//
//            for (Integer var: userVar){
//                boolean check = false;
//                User user = userRepository.findById((int)var);
//                Set<Role> roles = user.getRoles();
//                Iterator<Role> setIterator = roles.iterator();
//                while (setIterator.hasNext()) {
//                    Role currentElement = setIterator.next();
//                    if (currentElement.getName().equals(roleRepository.findById(number).getName())) {
//                        check = true;
//                        setIterator.remove();
//                        lists += "removed " + var + "</br>";
//                    }
//                }
//                if (!check) {
//                    roles.add(roleRepository.findById(number));
//                    lists += "added " + var + "</br>";
//                }
//                user.setRoles(roles);
//                userRepository.save(user);
//            }
//        }
//        return lists;
//    }
//
//    public void setRoles(User user, String role){
//        Set<Role> roles = user.getRoles();
//        roles.add(roleRepository.findRoleByName(role));
//        user.setRoles(roles);
//        userRepository.save(user);
//    }
//
//    public void setRoles(int user, int role){
//        User user1 = userRepository.findById(user);
//        Set<Role> roles = user1.getRoles();
//        roles.add(roleRepository.findById(role));
//        user1.setRoles(roles);
//        userRepository.save(user1);
//    }
//
//    public void deleteRoles(int user, int role){
//        User user1 = userRepository.findById(user);
//        Set<Role> roles =  user1.getRoles();
//        Iterator<Role> setIterator = roles.iterator();
//        while (setIterator.hasNext()) {
//            Role currentElement = setIterator.next();
//            if (currentElement.getName().equals(roleRepository.findById(role).getName())) {
//                setIterator.remove();
//            }
//        }
//        user1.setRoles(roles);
//        userRepository.save(user1);
//    }
//
//    @Override
//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//
//    public List<User> findAll() {
//        return userRepository.findAll();
//    }
//
//    public User findOne(int id) {
//        User foundUser = userRepository.findById(id);
//        return foundUser;
//    }
//
    @Override
    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        User oldUser = userRepository.findById(id);
        updatedUser.setPassword(oldUser.getPassword());
        updatedUser.setUsername(oldUser.getUsername());
        updatedUser.setRoles(oldUser.getRoles());
        userRepository.save(updatedUser);
    }
//
//    public void updateUsername(int id, User updatedUser){
//        User user = userRepository.findById(id);
//        user.setUsername(updatedUser.getUsername());
//        userRepository.save(user);
//    }
//
//    public void updatePassword(int id, User updatedUser){
//        User user = userRepository.findById(id);
//        user.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
//        userRepository.save(user);
//    }
}