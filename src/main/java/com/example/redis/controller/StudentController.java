package com.example.redis.controller;

import com.example.redis.dto.StudentDTO;
import com.example.redis.entity.Student;
import com.example.redis.repository.StudentRepository;
import com.example.redis.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAll() {
        List<StudentDTO> students = studentService.findAll();
        return ResponseEntity.ok().body(students);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable String id) {
        StudentDTO retrivedStudent = studentService.findById(id);
        return ResponseEntity.ok().body(retrivedStudent);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> insert(@RequestBody StudentDTO dto){

        StudentDTO student  = new StudentDTO(dto.getId(),
                dto.getName(), dto.getGender(), dto.getGrade());

        student.setTtl(3600L); // TTL de 1 hora
        studentService.save(student);

        //long ttl = 3600;
        //studentService.saveStudent(student, ttl, TimeUnit.SECONDS);

        return ResponseEntity.ok().body(student);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable String id, @RequestBody StudentDTO dto){
        StudentDTO retrivedStudent = studentService.findById(id);

        retrivedStudent.setName(dto.getName());
        retrivedStudent.setTtl(dto.getTtl());

        studentService.save(retrivedStudent);
        return ResponseEntity.ok().body(retrivedStudent);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<StudentDTO> delete(@PathVariable String id) {
        StudentDTO retrivedStudent = studentService.findById(id);
        System.out.println("Delete: " +  retrivedStudent.getId());
        studentService.deleteById(retrivedStudent.getId());
        return ResponseEntity.ok().body(retrivedStudent);
    }


}
