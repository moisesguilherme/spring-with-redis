package com.example.redis.repository;

import com.example.redis.dto.StudentDTO;
import com.example.redis.entity.Student;
import com.example.redis.services.StudentService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Student save(Student entity);

    @Cacheable(cacheNames = Student.CACHE_STUDENT_LIST)
    List<Student> findAll();

    //@Cacheable(value = "students")
    Optional<Student> findById(String id);

    @CacheEvict(value = "students", beforeInvocation = true, key = "#id")
    void deleteById(String id);

    @Cacheable(value = "students")
    default Optional<Student> findByIdWithLogging(String id) {
        StudentService.simulateLatency();
        System.out.println("Loading data in database...");
        return findById(id);
    }


}
