package com.example.redis.controller;

import com.example.redis.dto.StudentDTO;
import com.example.redis.entity.Student;
import com.example.redis.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    /*@GetMapping
    public ResponseEntity<Student> getStudent() {
        Student retrivedStudent = studentRepository.findById("Eng").get();
        System.out.println(">>> Recupera: " + retrivedStudent);
        return ResponseEntity.ok().body(retrivedStudent);
    }*/

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudent() {
        List<StudentDTO> students = new ArrayList<>();

        studentRepository.findAll().forEach(students::add);

        return ResponseEntity.ok().body(students);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(){
        Student engStudent  = new Student("Eng2015001", "Eric", Student.Gender.MALE, 1);
        Student medStudent  = new Student("Med2015001", "Marcus", Student.Gender.MALE, 2);

        studentRepository.save(engStudent);
        studentRepository.save(medStudent);

        System.out.println("Chamou post");
        return ResponseEntity.ok().body(medStudent);
    }

    @PutMapping
    public ResponseEntity<Student> changeStudent(){
        Student retrivedStudent = studentRepository.findById("Eng").get();
        retrivedStudent.setName("Richard Watson");

        studentRepository.save(retrivedStudent);
        return ResponseEntity.ok().body(retrivedStudent);
    }

    @DeleteMapping
    public ResponseEntity<Student> deleteStudent() {
        Student retrivedStudent = studentRepository.findById("Eng").get();
        System.out.println("Delete: " +  retrivedStudent.getId());
        studentRepository.deleteById(retrivedStudent.getId());
        return ResponseEntity.ok().body(retrivedStudent);
    }


}
