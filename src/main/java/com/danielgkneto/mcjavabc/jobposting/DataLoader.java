package com.danielgkneto.mcjavabc.jobposting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Executable;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    JobRepository jobRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... strings) throws Exception {

        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("daniel@email.com", "password", "Daniel", "Neto", true,
                "danielgkneto");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        jobRepository.save(new Job("Java Developer", "A Java developer is responsible for many duties throughout the development lifecycle of applications, from concept and design right through to testing.", "Microsoft", "(111) 111-1111", user));
        jobRepository.save(new Job("Network Technician", "A Network Technician is responsible for many duties throughout the development lifecycle of applications, from concept and design right through to testing.", "Microsoft", "(111) 111-1111", user));
        jobRepository.save(new Job("C# Developer", "A C# developer is responsible for many duties throughout the development lifecycle of applications, from concept and design right through to testing.", "IBM", "(333) 333-3333", user));
        jobRepository.save(new Job("Project Manager", "A Project Manager is responsible for many duties throughout the development lifecycle of applications, from concept and design right through to testing.", "IBM", "(333) 333-3333", user));

        user = new User("jim@example.com", "password", "Jim", "Jong", true,
                "jim");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        jobRepository.save(new Job("Database Administrator", "A Database Administrator is responsible for many duties throughout the development lifecycle of applications, from concept and design right through to testing.", "Amazon", "(888) 888-8888", user));
        jobRepository.save(new Job("Game Developer", "A Game Developer is responsible for many duties throughout the development lifecycle of applications, from concept and design right through to testing.", "Bethesda", "(999) 999-9999", user));

        user = new User("admin@admin.com", "password",
                "Admin",
                "Admin", true,
                "admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);
    }
}
