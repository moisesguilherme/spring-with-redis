package com.example.redis.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@Entity
@Table(name = "tb_student")
//@RedisHash(value="Student", timeToLive = 60L)
public class Student implements Serializable {

    public static final String CACHE_STUDENT_LIST = "studentList";
    public static final String CACHE_ID = "studentId";

    private static final long serialVersionUID = 1L;

    /*
         public enum Gender {
        MALE, FEMALE
    }

     */


    @Id
    private String id;
    private String name;
    private String gender;
    private int grade;

    public Student() {
    }

    public Student(String id, String name, String gender, int grade) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", grade=" + grade +
                '}';
    }
}
