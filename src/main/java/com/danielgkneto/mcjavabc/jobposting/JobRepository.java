package com.danielgkneto.mcjavabc.jobposting;

import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface JobRepository extends CrudRepository<Job, Long> {
    ArrayList<Job> findByTitleContainingIgnoreCaseOrCompanyContainingIgnoreCase(String title, String author);
    ArrayList<Job> findAllByUser(User user);
}
