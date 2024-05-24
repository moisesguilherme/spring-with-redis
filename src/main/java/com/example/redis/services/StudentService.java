package com.example.redis.services;

import com.example.redis.dto.StudentDTO;
import com.example.redis.entity.Student;
import com.example.redis.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<StudentDTO> findAll() {

        List<Student> list =  repository.findAll();
        return list.stream().map(x -> new StudentDTO(x)).collect(Collectors.toList());
    }


    public StudentDTO findById(String id) {
        System.out.println("Finding student with ID: " + id);
        Student student = repository.findByIdWithLogging(id).orElseThrow(() -> new RuntimeException ("Estudante não encontrado"));
        return new StudentDTO(student);
    }

    @Transactional
    public StudentDTO save(StudentDTO dto) {
       Student entity = new Student();
       copyDtoToEntity(dto, entity);
       entity = repository.save(entity);
        return new StudentDTO(entity);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    private void copyDtoToEntity(StudentDTO dto, Student entity) {
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        //entity.setGender(dto.getGender());
        entity.setGrade(dto.getGrade());
    }

    public static void simulateLatency() {
        try {
            long time = 1000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
