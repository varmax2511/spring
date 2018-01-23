package com.oauth2.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oauth2.demo.model.Student;

public interface StudentRepository extends MongoRepository<Student, String> {

  public Student findByName(String name);
}
