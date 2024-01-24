package ru.kata.spring.boot_security.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.List;

@Component
public class DatabaseAuto implements CommandLineRunner {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public DatabaseAuto(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {


        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        this.roleRepository.save(adminRole);
        this.roleRepository.save(userRole);

        User admin = new User("admin", "Admin", "Adminovich", 30, "admin@mail.ru", "admin");
        admin.setRoles(new HashSet<>(List.of(adminRole, userRole)));

        User user = new User("user", "User", "Userovich", 18, "user@mail.ru", "user");
        user.setRoles(new HashSet<>(List.of(userRole)));

        userService.addUser(admin);
        userService.addUser(user);
    }
}
