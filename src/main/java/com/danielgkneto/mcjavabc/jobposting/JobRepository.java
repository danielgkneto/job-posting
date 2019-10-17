package com.danielgkneto.mcjavabc.jobposting;

import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;

public interface JobRepository extends CrudRepository<Job, Long> {
    ArrayList<Job> findByTitleContainingIgnoreCaseOrCompanyContainingIgnoreCase(String title, String author);
}
