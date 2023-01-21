package com.vlsu.ispi.services;

import com.vlsu.ispi.models.ArrayCheckRoles;
import com.vlsu.ispi.models.CheckRoles;
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
public class UserServiceImpl implements UserService {

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

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    //
//    public User getCurrentAuthUser() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return findByUsername(auth.getName());
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
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllBarbers(int num) {
        List<User> users = findAll();
        List<User> barbers = new ArrayList<>();
        for (User user : users) {
            Set<Role> roles = user.getRoles();
            for (Role role : roles) {
                if ((role.getId() == 2) || (role.getId() == 4)) {
                    barbers.add(user);
                }
            }
        }
        return barbers.stream().skip(num * 9).limit(9).toList();
    }

    public void modifyRoles(CheckRoles checkRoles, String mode) {
        List<Integer> admins = checkRoles.getAdmRoles();
        List<Integer> barbers = checkRoles.getBarRoles();
        List<Integer> proBarbers = checkRoles.getProbarRoles();
        List<Integer> clients = checkRoles.getClRoles();
        List<List<Integer>> users = new ArrayList<>();

        users.add(admins);
        users.add(barbers);
        users.add(proBarbers);
        users.add(clients);

        for (List<Integer> userVar : users) {
            int k = 0;
            if (userVar.equals(admins)) k = 3;
            else if (userVar.equals(barbers)) k = 2;
            else if (userVar.equals(proBarbers)) k = 4;
            else if (userVar.equals(clients)) k = 1;
            for (Integer var : userVar) {
                User user = userRepository.findById((int) var);
                Set<Role> roles = user.getRoles();
                if (mode.equals("add")) {
                    roles.add(roleRepository.findById(k));
                } else if (mode.equals("del")) {
                    Iterator<Role> setIterator = roles.iterator();
                    while (setIterator.hasNext()) {
                        Role currentElement = setIterator.next();
                        if (currentElement.getId() == k) {
                            setIterator.remove();
                        }
                    }
                }
                user.setRoles(roles);
                userRepository.save(user);
            }
        }
    }


    public User findOne(int id) {
        return userRepository.findById(id);
    }
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

    public void updatePassword(int id, User updatedUser){
        User user = userRepository.findById(id);
        user.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
        userRepository.save(user);
    }
}