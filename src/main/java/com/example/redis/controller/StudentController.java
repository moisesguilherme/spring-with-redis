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
@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAll() {
        List<StudentDTO> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return ResponseEntity.ok().body(students);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable String id) {
        StudentDTO retrivedStudent = studentRepository.findById(id).get();
        return ResponseEntity.ok().body(retrivedStudent);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> insert(@RequestBody StudentDTO dto){

        StudentDTO student  = new StudentDTO(dto.getId(),
                dto.getName(), dto.getGender(), dto.getGrade());

        student.setTtl(3600L); // TTL de 1 hora

        studentRepository.save(student);

        return ResponseEntity.ok().body(student);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable String id, @RequestBody StudentDTO dto){
        StudentDTO retrivedStudent = studentRepository.findById(id).get();

        retrivedStudent.setName(dto.getName());
        retrivedStudent.setTtl(dto.getTtl());

        studentRepository.save(retrivedStudent);
        return ResponseEntity.ok().body(retrivedStudent);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<StudentDTO> delete(@PathVariable String id) {
        StudentDTO retrivedStudent = studentRepository.findById(id).get();
        System.out.println("Delete: " +  retrivedStudent.getId());
        studentRepository.deleteById(retrivedStudent.getId());
        return ResponseEntity.ok().body(retrivedStudent);
    }


}
