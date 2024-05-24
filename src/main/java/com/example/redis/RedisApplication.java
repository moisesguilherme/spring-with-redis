package com.example.redis;

import com.example.redis.dto.StudentDTO;
import com.example.redis.services.StudentService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class RedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(StudentService studentService){
		// Adiciona alguns students
		studentService.save(new StudentDTO("Med2024001", "Teste 1", null , 1));
		studentService.save(new StudentDTO("Med2024002", "Teste 2", null , 1));
		studentService.save(new StudentDTO("Med2024003", "Teste 3", null , 1));
		studentService.save(new StudentDTO("Med2024004", "Teste 4", null , 1));

		return args -> {
			// Busca pela primeira vez sem cache
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Id: 1: " + studentService.findById("Med2024001"));
			System.out.println("Id: 2: " + studentService.findById("Med2024002"));
			System.out.println("Id: 3: " + studentService.findById("Med2024003"));
			System.out.println("Id: 4: " + studentService.findById("Med2024004"));
			// buscar com cache
			System.out.println("Busca mais rápido: com cache");
			System.out.println("Id: 4: " + studentService.findById("Med2024004"));
			System.out.println("Id: 4: " + studentService.findById("Med2024004"));
			System.out.println("Id: 4: " + studentService.findById("Med2024004"));
		};
	}

}
