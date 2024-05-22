package com.example.redis.repository;

import com.example.redis.dto.StudentDTO;
import com.example.redis.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Está usando o StudentDTO para simplicar, futuramente vai ser a entity Studend no repository
@Repository
public interface StudentRepository extends CrudRepository<StudentDTO, String> {}
