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

        User user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true,
                "jim");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

/*        Car[] fakeCars = new Car[] {new Car("Toyota","Corolla","2010",11000, fakeCategories[0], user), new Car("Ford","Mustang","2018",88000, fakeCategories[3], user), new Car("Volkswagen","Beatle","1966",500, fakeCategories[2], user), new Car("Erat Volutpat Company","Debra","7399",34925, fakeCategories[1], user), new Car("Back to the Future","DeLorean","2000",100000, fakeCategories[3], user)};
        carRepository.saveAll(Arrays.asList(fakeCars));*/

        user = new User("admin@admin.com", "password",
                "Admin",
                "Admin", true,
                "admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);
    }
}
