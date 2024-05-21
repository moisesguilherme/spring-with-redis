package com.example.redis.dto;

import com.example.redis.entity.Student;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("StudentDTO")
public class StudentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Gender {
        MALE, FEMALE
    }

    private String id;
    private String name;
    private Student.Gender gender;
    private int grade;


    public StudentDTO(String id, String name, Student.Gender gender, int grade) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student.Gender getGender() {
        return gender;
    }

    public void setGender(Student.Gender gender) {
        this.gender = gender;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
