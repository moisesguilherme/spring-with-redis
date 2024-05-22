package com.example.redis.services;

import com.example.redis.dto.StudentDTO;
import com.example.redis.entity.Student;
import com.example.redis.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<StudentDTO> findAll() {

        List<StudentDTO> students = new ArrayList<>();
        repository.findAll().forEach(students::add);
        return students;
    }


    public StudentDTO findById(String id) {
        return repository.findById(id).get();
    }

    public StudentDTO save(StudentDTO student) {
       return repository.save(student);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    /*
    @Autowired
    private RedisTemplate<String, StudentDTO> redisTemplate;

    public void saveStudent(StudentDTO student, long ttl, TimeUnit unit) {
        ValueOperations<String, StudentDTO> ops = redisTemplate.opsForValue();
        ops.set(student.getId(), student, ttl, unit);
    }

    public StudentDTO getStudent(String id) {
        ValueOperations<String, StudentDTO> ops = redisTemplate.opsForValue();
        return ops.get(id);
    }*/



}
