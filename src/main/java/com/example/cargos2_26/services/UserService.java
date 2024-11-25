package com.example.cargos2_26.services;

import com.example.cargos2_26.entity.Role;
import com.example.cargos2_26.entity.Users;
import com.example.cargos2_26.repos.RoleRepository;
import com.example.cargos2_26.repos.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository repoUser;
    @Autowired
    RoleRepository repoRole;
    PasswordEncoder passwordEncoder;

    //    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repoUser.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        return user;
    }

    public Users findUserById(Long userId) {
        Optional<Users> userFromDb = repoUser.findById(userId);
        return userFromDb.orElse(new Users());
    }

    public List<Users> allUsers() {
        return repoUser.findAll();
    }

    public boolean saveUser(Users user) {
        Users userFromDB = repoUser.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repoUser.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (repoUser.findById(userId).isPresent()) {
            repoUser.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<Users> userMinId(Long idMin) {
        return repoUser.usergetList(idMin);
    }
}
