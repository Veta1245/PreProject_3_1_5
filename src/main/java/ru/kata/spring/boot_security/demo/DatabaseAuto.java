package ru.kata.spring.boot_security.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.HashSet;
import java.util.List;

@Component
public class DatabaseAuto implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DatabaseAuto(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {


        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        this.roleRepository.save(adminRole);
        this.roleRepository.save(userRole);

        User admin = new User("Admin1", "Admin", "Adminovich", 12, "admin@mail.ru", "root");
        admin.setRoleList(new HashSet<>(List.of(adminRole, userRole)));

        User user = new User("User1", "User", "Userovich", 11, "user@mail.ru", "1234");
        user.setRoleList(new HashSet<>(List.of(userRole)));

        this.userRepository.save(admin);
        this.userRepository.save(user);
    }
}
