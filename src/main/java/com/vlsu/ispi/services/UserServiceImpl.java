package com.vlsu.ispi.services;

import com.vlsu.ispi.classes.CheckRoles;
import com.vlsu.ispi.models.Feedback;
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
    private SecurityService securityService;

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


    public User getCurrentAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return findByUsername(auth.getName());
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllBarbers() {
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
        return barbers;
    }

    public List<User> getBarberPage(int num){
        List<User> barbers = findAllBarbers();
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
            int role = 0;
            int reverse = 0;
            if (userVar.equals(admins)) role = 3;
            else if (userVar.equals(barbers)) {
                role = 2;
                reverse = 4;
            }
            else if (userVar.equals(proBarbers)) {
                role = 4;
                reverse = 2;
            }
            else if (userVar.equals(clients)) role = 1;
            for (Integer var : userVar) {
                User user = userRepository.findById((int) var);
                Set<Role> roles = user.getRoles();
                if (mode.equals("add")) {
                    if ((role==4)||(role==2)){
                        Iterator<Role> setIterator = roles.iterator();
                        while (setIterator.hasNext()) {
                            Role currentElement = setIterator.next();
                            if (currentElement.getId() == reverse) {
                                setIterator.remove();
                            }
                        }
                    }
                    roles.add(roleRepository.findById(role));
                } else if (mode.equals("del")) {
                    Iterator<Role> setIterator = roles.iterator();
                    while (setIterator.hasNext()) {
                        Role currentElement = setIterator.next();
                        if (currentElement.getId() == role) {
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

    @Override
    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        User oldUser = userRepository.findById(id);
        updatedUser.setPassword(oldUser.getPassword());
        updatedUser.setRoles(oldUser.getRoles());
        updatedUser.setUsername(oldUser.getUsername());
        userRepository.save(updatedUser);
    }

    public void updatePassword(int id, User updatedUser){
        User user = userRepository.findById(id);
        user.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
        userRepository.save(user);
    }

    public void updateUsername(int id, User updatedUser){
        User user = userRepository.findById(id);
        user.setUsername(updatedUser.getUsername());
        userRepository.save(user);
    }
}